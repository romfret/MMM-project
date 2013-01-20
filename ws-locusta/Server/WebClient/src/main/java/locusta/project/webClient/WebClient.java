package locusta.project.webClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import locusta.project.entitiesAndroid.Event;
import locusta.project.entitiesAndroid.EventType;
import locusta.project.entitiesAndroid.User;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class WebClient {
	private ObjectMapper mapper;
	HttpClient httpClient;

	public WebClient() {
		mapper = new ObjectMapper();
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 5000);
		HttpConnectionParams.setSoTimeout(params, 5000);
		httpClient = new DefaultHttpClient(params);
	}

	public Event addEvent(Event event) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("event", mapper.writeValueAsString(event));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (Event) call("addEvent", params, Event.class);
	}

	public Event getEventById(int id) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("id", mapper.writeValueAsString(id));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (Event) call("getEventById", params, Event.class);
	}

	@SuppressWarnings("unchecked")
	public List<Event> searchEvents(String somethingLikeThat) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("somethingLikeThat",
					mapper.writeValueAsString(somethingLikeThat));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (ArrayList<Event>) callGeneric("searchEvents", params,
				new TypeReference<ArrayList<Event>>() {
				});
	}

	@SuppressWarnings("unchecked")
	public List<Event> lookEventsAround(double longitude, double latitude,
			double radius) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("longitude", mapper.writeValueAsString(longitude));
			params.put("latitude", mapper.writeValueAsString(latitude));
			params.put("radius", mapper.writeValueAsString(radius));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (ArrayList<Event>) callGeneric("lookEventsAround", params,
				new TypeReference<ArrayList<Event>>() {
				});
	}

	public EventType addEventType(String name) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("name", mapper.writeValueAsString(name));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (EventType) call("addEventType", params, EventType.class);
	}

	@SuppressWarnings("unchecked")
	public List<EventType> getEventTypes() {
		Map<String, String> params = new HashMap<String, String>();

		return (ArrayList<EventType>) callGeneric("getEventTypes", params,
				new TypeReference<ArrayList<EventType>>() {
				});
	}

	public User userRegistration(User user) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("user", mapper.writeValueAsString(user));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (User) call("userRegistration", params, User.class);
	}

	public String encryptPassword(String password) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("password", mapper.writeValueAsString(password));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (String) call("encryptPassword", params, String.class);
	}

	public User getUserById(int id) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("id", mapper.writeValueAsString(id));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (User) call("getUserById", params, User.class);
	}

	public User getUserByUserName(String userName) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("userName", mapper.writeValueAsString(userName));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (User) call("getUserByUserName", params, User.class);
	}

	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String somethingLikeThat) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("somethingLikeThat",
					mapper.writeValueAsString(somethingLikeThat));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return (ArrayList<User>) callGeneric("searchUsers", params,
				new TypeReference<ArrayList<User>>() {
				});
	}

	public void updateUser(User user) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			params.put("user", mapper.writeValueAsString(user));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		call("updateUser", params, null);

	}

	private Object callGeneric(String nameMethod, Map<String, String> params,
			TypeReference<?> typeReference) {

		String jsonResult = sendRequest(nameMethod, params);
		try {
			return mapper.readValue(jsonResult, typeReference);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Object call(String nameMethod, Map<String, String> params,
			@SuppressWarnings("rawtypes") Class returnClass) {

		String jsonResult = sendRequest(nameMethod, params);
		try {
			if (returnClass == null)
				return null;
			return mapper.readValue(jsonResult, returnClass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String sendRequest(String nameMethod, Map<String, String> params) {
		HttpPost request;
		try {
			request = new HttpPost("http://localhost:8000/locusta/services");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

		String json;
		json = "{\"methodName\":\"" + nameMethod + "\", \"parameters\": {";
		if (params.size() > 0) {
			for (Entry<String, String> entry : params.entrySet())
				json += "\"" + entry.getKey() + "\":" + entry.getValue() + ", ";
			json = json.substring(0, json.length() - 2);
		}
		json += "}}";

		StringEntity httpParams;
		try {
			httpParams = new StringEntity(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		request.addHeader("Content-type", "application/json;charset=latin-1");
		request.setEntity(httpParams);

		String responseBody = "";
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			BufferedReader responseDataBuff = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			responseBody = "";
			String line = "";
			while ((line = responseDataBuff.readLine()) != null) {
				responseBody += line + "\n";
			}
		} catch (HttpException e1) {
			e1.printStackTrace();
			return "null";
		} catch (IOException e) {
			e.printStackTrace();
			return "null";
		}

		return responseBody;
	}

	public static void main(String args[]) {
		WebClient wc = new WebClient();
		System.out.println("Connexion Ètablie");

		// User toto = wc.userRegistration(new User("toto", "toto"));
		// User titi = wc.userRegistration(new User("titi", "titi"));
		 User toto = wc.getUserById(1);
		// EventType et1 = wc.addEventType("cinÈma");
		List<EventType> let = wc.getEventTypes();
		EventType et1 = let.get(0);
		 Event e1 = new Event("event1", "descr1", new Date(), -1.659966,
		 48.101929, toto);
		 Event e2 = new Event("event2", "descr2", new Date(), -1.635761,
		 48.121815, toto);

		 e1.setEventType(et1);
		 wc.addEvent(e1);
		 wc.addEvent(e2);
		//
		// User titi = wc.getUserByUserName("titi");
		// toto.addFriend(titi);
		// wc.updateUser(toto);
		// wc.userUnRegistration(titi);

		/*
		 * if (toto == null) {
		 * 
		 * toto = wc.userRegistration(toto);
		 * System.out.println("Utilisateur cr√©√©"); }
		 * System.out.println(toto.getUserName());
		 */
		/*
		 * System.out.println("Recherche de toto par la chaine 'to'");
		 * List<User> usersFound = wc.searchUsers("tata");
		 * 
		 * System.out.println("Utilisateurs trouv√©s : " + usersFound.size());
		 * User userFound = usersFound.get(0);
		 */

	}

}
