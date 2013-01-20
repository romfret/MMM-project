/* C'EST UN BEANS */

package locusta.project.servicesBean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
					+ "FROM User u " + "WHERE u._id = " + id);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public User getUserByUserName(String userName) {
		User user = null;
		try {
			Query query = entityManager
					.createQuery("SELECT u " + "FROM User u "
							+ "WHERE u._userName = '" + userName + "'");
			user = (User) query.getSingleResult();
		} catch (Exception e) {

		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String somethingLikeThat) {
		Query query = entityManager.createQuery("SELECT u " + "FROM User u "
				+ "WHERE u._userName LIKE '%" + somethingLikeThat
				+ "%' ORDER BY u._userName");
		return (List<User>) query.getResultList();
	}

	public Event getEventById(int id) {
		try {
			Query query = entityManager.createQuery("SELECT e "
					+ "FROM Event e " + "WHERE e._id = " + id);
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

	@SuppressWarnings("unchecked")
	public List<Event> searchEvents(String somethingLikeThat) {
		Query query = entityManager.createQuery("SELECT e " + "FROM Event e "
				+ "WHERE e._name LIKE '%" + somethingLikeThat
				+ "%' ORDER BY e._name");
		
		return (List<Event>) query.getResultList();
	}

	public List<Event> lookEventsAround(double longitude, double latitude,
			double radius /* in meters */) {

		TypedQuery<Object[]> query = entityManager
				.createQuery(
						"SELECT e,  ((ACOS(SIN("
								+ latitude
								+ " * PI() / 180) * SIN(e._latitude * PI() / 180) + COS("
								+ latitude
								+ " * PI() / 180) * COS(e._latitude * PI() / 180) * COS(("
								+ longitude
								+ " – e._longitude) * PI() / 180)) * 180 / PI()) * 6366000) as distance"
								+ "FROM Event e " + "WHERE distance <= "
								+ radius + " " + "ORDER BY distance",
						Object[].class);

		List<Object[]> result = (List<Object[]>) query.getResultList();

		List<Event> events = new ArrayList<Event>();
		for (Object[] objs : result)
			events.add((Event) objs[1]);

		return events;

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
				+ "FROM EventType et " + "WHERE LOWER(et._name) = '"
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
				+ "FROM EventType et ORDER BY et._name");
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
			realUser.setHashedPass(user.getHashedPass());
			realUser.setLongitude(user.getLongitude());
			realUser.setLatitude(user.getLatitude());
			realUser.getFriends().clear();
			for(User u: user.getFriends()) {
				User ru = getUserById(u.getId());
				if (ru != null) realUser.addFriend(ru);
			}				
			entityManager.persist(realUser);
			entityManager.flush();
	
	}

}
