/* C'EST UN BEANS */

package locusta.project.servicesBean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import locusta.project.entities.Event;
import locusta.project.entities.EventType;
import locusta.project.entities.User;
import locusta.project.services.ServicesEvent;
import locusta.project.services.ServicesUser;


/**
 * Stateless session bean that is using JPA 2.0.
 */
@Stateless(mappedName = "locusta")
public class ServicesBean implements ServicesEvent, ServicesUser {

	/**
	 * Entity manager used by this session bean.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public ServicesBean() {}

	//Persist or merge ???
	//Use merge to retrieve the persistence of an entity unlinked of JPA
	//http://stackoverflow.com/questions/1069992/jpa-entitymanager-why-use-persist-over-merge
	
	//JPA syntax is the same as SQL
	
	public User registration(User user) {
		entityManager.persist(user);
		return user;
	}

	public void unRegistration(User user) {
		entityManager.remove(user);		
	}
	
    public String encryptPassword(String s) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(s.getBytes());
            result = toHex(digest);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	public User getByUserName(String userName) {
		Query query = entityManager.createQuery(	
				"SELECT u " + 
				"FROM User u " +
				"WHERE u.userName = '" + userName + "'");
		return (User) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String somethingLikeThat) {
		Query query = entityManager.createQuery(	
				"SELECT u " +
				"FROM User u " +
				"WHERE u.userName LIKE '%" + somethingLikeThat + "%'");
		return  (List<User>) query.getResultList();
	}
	
	public Event addEvent(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> getByEventName(String eventName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> searchEvents(String somethingLikeThat) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> lookEventsAround(double longitude, double latitude,
			double radius) {
		// TODO Auto-generated method stub
		
		/* UNE FONCTION QUE J'AVAIS FAITES EN JAVZSCRIPT POUR UNE APP MOBILE GEOLOCALISEE
		 * A ADAPTER QUI RETOURNE LA DISTANCE EN M ENTRE 2 POSITIONS, 
		 * IL SUFFIT ALORS DE LA COMPARER AU RADIUS
		 * POUR SAVOIR SI ON GARDE L'EVENEMENT OU NON
		 * function deg2rad(deg) {
			  return deg * Math.PI / 180;
			}
					
			function distance(lat1, lon1, lat2, lon2) {
			    
			  var r = 6366;
			
			  lat1 = deg2rad(lat1);
			  lon1 = deg2rad(lon1);
			  lat2 = deg2rad(lat2);
			  lon2 = deg2rad(lon2);
			   
			  var d = Math.acos( Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1-lon2));
			  d = d * r * 1000;
			
			  return d;
			
			}
		 */
		return null;
	}

	public EventType addEventType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EventType> getEventTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	



    private String toHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (int i = 0; i < a.length; i++) {
            sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(a[i] & 0x0f, 16));
        }
        return sb.toString();
    }




}
