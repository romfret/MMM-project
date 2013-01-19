package locusta.project.entitiesAndroid;

import java.util.HashSet;
import java.util.Set;
public class User implements  IEntity {

	private int _id;

	private String _userName;
	private String _hashedPass;


	private double _longitude;


	private double _latitude;

	private Set<User> _friends;

	public User() {
		_friends = new HashSet<User>();
	}

	public User(String userName, String hashedPass) {
		super();
		this._userName = userName;
		this._hashedPass = hashedPass;
	}

	public User(String userName, String hashedPass, double longitude,
			double latitude) {
		super();
		this._userName = userName;
		this._hashedPass = hashedPass;
		this._longitude = longitude;
		this._latitude = latitude;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		this._userName = userName;
	}

	public String getHashedPass() {
		return _hashedPass;
	}

	public void setHashedPass(String hashedPass) {
		this._hashedPass = hashedPass;
	}

	public double getLongitude() {
		return _longitude;
	}

	public void setLongitude(double longitude) {
		this._longitude = longitude;
	}

	public double getLatitude() {
		return _latitude;
	}

	public void setLatitude(double latitude) {
		this._latitude = latitude;
	}

	public Set<User> getFriends() {
		return _friends;
	}

	public void setFriends(Set<User> friends) {
		this._friends = friends;
	}

	public void addFriend(User user) {
		this._friends.add(user);
	}

	public void removeFriend(User user) {
		this._friends.remove(user);
	}

}
