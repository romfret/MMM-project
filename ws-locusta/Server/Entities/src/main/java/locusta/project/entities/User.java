package locusta.project.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * A JPA 2.0 Entity.
 */
@Entity
public class User implements Serializable, IEntity {

	private static final long serialVersionUID = -7448829558275268188L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@Column(nullable = false, unique = true)
	private String _userName;

	/*
	 * Please hash the password (with MD5 for example) before set the variable
	 */
	@Column(nullable = false)
	private String _hashedPass;

	@Column(nullable = true)
	private double _longitude;

	@Column(nullable = true)
	private double _latitude;

	/*
	 * No REMOVE CascadeType to avoid accidental deletions friends
	 */
	@ManyToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	private Set<User> _friends;

	/*
	 * We don't need a list of events in user because an event contains already
	 * a user. So, just create a request to search the user events (the user is
	 * indexed on event in DBB)
	 */

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

	public IEntity cloneForJson() {
		return clone(1);
	}

	public IEntity clone(int depthFriend) {
		User newUser = new User(this._userName, this._hashedPass);
		newUser.setId(this._id);
		newUser.setLatitude(this._latitude);
		newUser.setLongitude(this._longitude);

		if (depthFriend > 0) {
			Set<User> newFriends = new HashSet<User>();
			--depthFriend;
			if (this._friends != null) {

				for (User friend : this.getFriends())
					newFriends.add((User) friend.clone(depthFriend));
			}
			newUser.setFriends(newFriends);
		}

		return newUser;
	}

}
