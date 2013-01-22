package locusta.project.entitiesAndroid;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

	private static final long serialVersionUID = -7448829558275268188L;


	private int id;

	private String userName;


	private String pass;
	private double lon;

	private double lat;

	private Set<User> friends;

	public User() {
		friends = new HashSet<User>();
	}

	public User(String userName, String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}

	public User(String userName, String hashedPass, double lon,
			double lat) {
		super();
		this.userName = userName;
		this.pass = hashedPass;
		this.lon = lon;
		this.lat = lat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public void addFriend(User user) {
		this.friends.add(user);
	}

	public void removeFriend(User user) {
		this.friends.remove(user);
	}

}
