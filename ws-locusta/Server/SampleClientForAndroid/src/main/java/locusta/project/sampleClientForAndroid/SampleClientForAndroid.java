package locusta.project.sampleClientForAndroid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import locusta.project.entities.User;
import locusta.project.services.ServicesUser;

public class SampleClientForAndroid {
	private static final String DEFAULT_INITIAL_CONTEXT_FACTORY = 
			"org.ow2.easybeans.component.smartclient.spi.SmartContextFactory";
	
	public SampleClientForAndroid() {
		try {
			// Lookup the Remote Bean interface through JNDI
			Context initialContext = getInitialContext();

			ServicesUser servicesUser = (ServicesUser) initialContext
					.lookup("locusta");

			System.out.println("Connexion établie");

			User toto = servicesUser.getByUserName("toto");
			
			if (toto == null) {
				toto = new User("toto",
						servicesUser.encryptPassword("totopass"));
				toto = servicesUser.registration(toto);
				System.out.println("Utilisateur toto créé");
			}
			
			System.out.println("Recherche de toto par la chaine 'to'");
			List<User> usersFound = servicesUser.searchUsers("to");

			System.out.println("Utilisateurs trouvés : " + usersFound.size());
			User userFound = usersFound.get(0);

			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			while (true) {
				System.out
						.println("Tapez le mot de passe de l'utilisateur toto : ");
				String password = in.readLine();
				
				//pas de == avec les string
				if (userFound.getHashedPass().equals(servicesUser
						.encryptPassword(password))) {
					
					System.out.println("OK c'est le bon !");
					break;
				}
				System.out.println("Mauvais mot de passe !");
				System.out.println("righ md5 = " + toto.getHashedPass());
				System.out.println("your md5 = " + servicesUser
						.encryptPassword(password));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) throws Exception {
		@SuppressWarnings("unused")
		SampleClientForAndroid scfa = new SampleClientForAndroid();
	}

	private static Context getInitialContext() throws NamingException {

		// if user don't use jclient/client container
		// we can specify the InitialContextFactory to use
		// But this is *not recommended*.
		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, getInitialContextFactory());

		// Usually a simple new InitialContext() without any parameters is
		// sufficent.
		// return new InitialContext();

		return new InitialContext(env);
	}

	private static String getInitialContextFactory() {
		String prop = System
				.getProperty("easybeans.client.initial-context-factory");
		// If not found, use the default
		if (prop == null) {
			prop =  DEFAULT_INITIAL_CONTEXT_FACTORY;
		}
		return prop;
	}

}
