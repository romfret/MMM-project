package locusta.project.entitiesAndroid;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class User implements Serializable {

	private static final long serialVersionUID = -7448829558275268188L;

	private int id;

	private String userName;

	private String pass;
	private double longitude;
	private double latitude;

	private Set<User> friends;
	public User() {
		friends = new HashSet<User>();
	}

	public User(String userName, String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}

	public User(String userName, String hashedPass, double lontitude,
			double latitude) {
		super();
		this.userName = userName;
		this.pass = hashedPass;
		this.longitude = lontitude;
		this.latitude = latitude;
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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
