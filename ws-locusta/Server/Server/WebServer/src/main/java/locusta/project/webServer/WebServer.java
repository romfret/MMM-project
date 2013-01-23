package locusta.project.webServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import locusta.project.entities.IEntity;
import locusta.project.services.Services;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
	private static final String DEFAULT_INITIAL_CONTEXT_FACTORY = "org.ow2.easybeans.component.smartclient.spi.SmartContextFactory";

	private Services services;

	public WebServer() {

		// Lookup the Remote Bean interface through JNDI
		Context initialContext = null;
		try {
			initialContext = getInitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(0);
		}

		services = null;
		try {
			services = (Services) initialContext.lookup("locusta");
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(0);
		}

		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(80), 0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		server.createContext("/locusta/services", new LocustaEventHandler());

		System.out.println("Starting...");
		server.setExecutor(null); // creates a default executor
		server.start();
		System.out.println("Started.");

	}

	private class LocustaEventHandler implements HttpHandler {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void handle(HttpExchange t) throws IOException {
			String who = t.getRemoteAddress().toString();
			System.out.println("Receiving client request : "
					+ who);
			// Initialisation
			JsonFactory jsonFactory = new JsonFactory();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(t.getRequestBody()));
			String requestBody = "";
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				requestBody += line + "\n";
			}
			JsonParser jp = jsonFactory.createJsonParser(requestBody);
			if (jp == null) {
				System.err.println(who + ": Broken request: " + requestBody);
				return;
			}
			System.out.print(who + ": Request body:" + requestBody);
			System.out.println(who + ": Reading...");
			// Lecture du nom de la m�thode � appeler
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(jp);
			if (rootNode == null) {
				System.err.println(who + ": This user don't know what he wants!");
				return;
			}
			JsonNode methodNameNode = rootNode.get("methodName");
			if (methodNameNode == null) {
				System.err.println(who + ": This user don't know what he wants!");
				return;
			}
			String methodName = rootNode.path("methodName").asText();
			Method method = null;
			System.out.println(who + ": ...want to call : " + methodName + "(...)");

			// Recherche de la m�thode sur le serveur et attribution
			for (Method m : Services.class.getMethods()) {
				if (m.getName().equals(methodName))
					method = m;
			}
			if (method == null) {
				System.err
						.println(who + ": You try to call a unknown function on the server: "
								+ methodName);
				System.err.println("No actions possible");
				return;
			}

			// Composition des param�tres
			List<Object> parameters = new ArrayList<Object>();
			JsonNode paramsNode = rootNode.path("parameters");
			if (paramsNode == null) {
				System.err.println(who + ": parameters field missing: " + requestBody);
				return;
			}
		
			Iterator<JsonNode> paramsIterable = paramsNode.getElements();
			for (Class param : method.getParameterTypes()) {
				if (paramsIterable.hasNext() == false) {
					System.err
							.println(who + ": Not enough arguments to call function!");
					return;
				}

				parameters.add(mapper.readValue(paramsIterable.next(), param));
			}
			
		
			Object result = null;
			try {
				result = method.invoke(services, parameters.toArray());
			
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return;
			}

			// Conversions...
			String response = "null";
			
			try {				
				if (result != null && result instanceof IEntity) {					
					response = mapper.writeValueAsString(((IEntity) result)
							.cloneForJson());					
				}
				else if (result != null && (result instanceof List<?>)) {
					List<IEntity> newList = new ArrayList<IEntity>();
					for (IEntity entity : (List<IEntity>) result)
						newList.add(entity.cloneForJson()); 
					response = mapper.writeValueAsString(newList);
					
				}
				else 
					response = mapper.writeValueAsString(result);	
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(who + ": Response body: " + response);
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
			System.out.println(who + ": Success!");

		}
	}

	public static void main(final String[] args) throws Exception {
		@SuppressWarnings("unused")
		WebServer ws = new WebServer();

	}

	private static Context getInitialContext() throws NamingException {

		java.util.Properties props = new java.util.Properties();

		props.put("java.naming.factory.initial",
				"org.ow2.easybeans.component.smartclient.spi.SmartContextFactory");
		props.put("java.naming.provider.url", "smart://localhost:2503");
		return new InitialContext(props);

	}

	private static String getInitialContextFactory() {
		String prop = System
				.getProperty("easybeans.client.initial-context-factory");
		// If not found, use the default
		if (prop == null) {
			prop = DEFAULT_INITIAL_CONTEXT_FACTORY;
		}
		return prop;
	}

}
