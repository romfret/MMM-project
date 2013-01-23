/* C'EST UN BEANS */

package locusta.project.servicesBean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import locusta.project.entities.Event;
import locusta.project.entities.EventType;
import locusta.project.entities.User;
import locusta.project.services.Services;

/**
 * Stateless session bean that is using JPA 2.0.
 */
@Stateless(mappedName = "locusta")
public class ServicesBean implements Services {

	/**
	 * Entity manager used by this session bean.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public ServicesBean() {
	}

	// Persist or merge ???
	// Use merge to retrieve the persistence of an entity unlinked of JPA
	// http://stackoverflow.com/questions/1069992/jpa-entitymanager-why-use-persist-over-merge

	// JPA syntax is the same as SQL

	public User userRegistration(User user) {
		if (getUserByUserName(user.getUserName()) != null)
			return null;
		entityManager.persist(user);
		return user;
	}

	public String encryptPassword(String s) {
		String result = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] digest = md5.digest(s.getBytes());
			result = toHex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User getUserById(int id) {
		try {
			Query query = entityManager.createQuery("SELECT u "
					+ "FROM User u " + "WHERE u.id = " + id);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public User getUserByUserName(String userName) {
		User user = null;
		try {
			Query query = entityManager.createQuery("SELECT u "
					+ "FROM User u " + "WHERE u.userName = '" + userName + "'");
			user = (User) query.getSingleResult();
		} catch (Exception e) {

		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String somethingLikeThat) {
		Query query = entityManager.createQuery("SELECT u " + "FROM User u "
				+ "WHERE u.userName LIKE '%" + somethingLikeThat
				+ "%' ORDER BY u.userName");
		return (List<User>) query.getResultList();
	}

	public Event getEventById(int id) {
		try {
			Query query = entityManager.createQuery("SELECT e "
					+ "FROM Event e " + "WHERE e.id = " + id);
			return (Event) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Event addEvent(Event event) {
		if (getEventById(event.getId()) != null)
			return null;

		entityManager.persist(event);
		return event;
	}

		
	public List<Event> lookEventsAround(double longitude, double latitude,
			double radius /* in meters */, EventType eventType) {
		String query = "";


		
		query += "SELECT e "
				+ "FROM Event e, EventType et " 
				+ "WHERE (ACOS(COS(" + latitude + " * PI() / 180) * COS(e.lat * PI() / 180) * COS("
				+ "e.lon * PI() / 180 - " + longitude + " * PI() / 180) + ("
				+ "SIN(" + latitude + " * PI() / 180) * SIN(e.lat * PI() / 180)))"
				+ " * 6371000) <= " + radius + " AND "
				+ "e.endDate > now() AND e.startDate < now()" ;
		if (eventType != null) {
			query += " AND " + "e.eventType_id = et._id and et._id = "
					+ eventType.getId();
		}
		
		TypedQuery<Event> tquery = entityManager.createQuery(query,
				Event.class);

		return (List<Event>) tquery.getResultList();


	}

	/*
	 * private double distanceInMeters(double lon1, double lat1, double lon2,
	 * double lat2) { double r = 6366; lat1 = deg2rad(lat1); lon1 =
	 * deg2rad(lon1); lat2 = deg2rad(lat2); lon2 = deg2rad(lon2);
	 * 
	 * double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1)
	 * Math.cos(lat2) * Math.cos(lon1 - lon2)); d = d * r * 1000; return d; }
	 * 
	 * private double deg2rad(double deg) { return deg * Math.PI / 180; }
	 */

	@SuppressWarnings("unchecked")
	public EventType addEventType(String name) {
		Query query = entityManager.createQuery("SELECT et "
				+ "FROM EventType et " + "WHERE LOWER(et.name) = '"
				+ name.toLowerCase() + "'");
		List<EventType> listET = (List<EventType>) query.getResultList();
		if (listET.size() > 0)
			return listET.get(0);
		else {
			EventType et = new EventType(name);
			entityManager.persist(et);
			return et;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EventType> getEventTypes() {
		Query query = entityManager.createQuery("SELECT et "
				+ "FROM EventType et ORDER BY et.name");
		return (List<EventType>) query.getResultList();
	}

	private String toHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (int i = 0; i < a.length; i++) {
			sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(a[i] & 0x0f, 16));
		}
		return sb.toString();
	}

	public void updateUser(User user) {
		if (getUserById(user.getId()) == null)
			return;

		User realUser = getUserById(user.getId());
		realUser.setUserName(user.getUserName());
		realUser.setPass(user.getPass());
		realUser.setLongitude(user.getLongitude());
		realUser.setLatitude(user.getLatitude());
		realUser.getFriends().clear();
		for (User u : user.getFriends()) {
			User ru = getUserById(u.getId());
			if (ru != null)
				realUser.addFriend(ru);
		}
		entityManager.persist(realUser);
		entityManager.flush();

	}

}
