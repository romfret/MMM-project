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

import locusta.project.entities.IEntity;

/**
 * A JPA 2.0 Entity.
 */
@Entity
public class User implements Serializable, IEntity {

	private static final long serialVersionUID = -7448829558275268188L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, unique = true)
	private String userName;

	/*
	 * Please hash the password (with MD5 for example) before set the variable
	 */
	@Column(nullable = false)
	private String pass;

	@Column(nullable = true)
	private double longitude;

	@Column(nullable = true)
	private double latitude;

	/*
	 * No REMOVE CascadeType to avoid accidental deletions friends
	 */
	@ManyToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	private Set<User> friends;

	/*
	 * We don't need a list of events in user because an event contains already
	 * a user. So, just create a request to search the user events (the user is
	 * indexed on event in DBB)
	 */

	public User() {
		friends = new HashSet<User>();
	}

	public User(String userName, String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}

	public User(String userName, String hashedPass, double longitude,
			double latitude) {
		super();
		this.userName = userName;
		this.pass = hashedPass;
		this.longitude = longitude;
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

	public IEntity cloneForJson() {
		return clone(1);
	}

	public IEntity clone(int depthFriend) {
		User newUser = new User(this.userName, this.pass);
		newUser.setId(this.id);
		newUser.setLatitude(this.latitude);
		newUser.setLongitude(this.longitude);

		if (depthFriend > 0) {
			Set<User> newFriends = new HashSet<User>();
			--depthFriend;
			if (this.friends != null) {

				for (User friend : this.getFriends())
					newFriends.add((User) friend.clone(depthFriend));
			}
			newUser.setFriends(newFriends);
		}

		return newUser;
	}

}
