
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 * A JPA 2.0 Entity.
 */
@Entity
public class User implements Serializable {


    private static final long serialVersionUID = -7448829558275268188L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable=false, unique=true)
    private String userName;
    
    /*
	 *	Please hash the password (with MD5 for example)
     *  before set the variable 
     */  
    @Column(nullable=false)
    private String hashedPass;
    
    @Column(nullable=true)
    private double longitude;
    
    @Column(nullable=true)
    private double latitude;
    
    /*
     * No REMOVE CascadeType to
     * avoid accidental deletions friends
     */
    @JoinColumn
    @OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.LAZY)
    private Set<User> friends;
    
    /*
     * We don't need a list of events in user because
     * an event contains already a user. So, just create a request
     * to search the user events (the user is indexed on event in DBB)
     */
    
    public User() {
    	friends = new HashSet<User>();
    }
    
	public User(String userName, String hashedPass) {
		super();
		this.userName = userName;
		this.hashedPass = hashedPass;
	}
	
	public User(String userName, String hashedPass, double longitude,
			double latitude) {
		super();
		this.userName = userName;
		this.hashedPass = hashedPass;
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

	public String getHashedPass() {
		return hashedPass;
	}

	public void setHashedPass(String hashedPass) {
		this.hashedPass = hashedPass;
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
	
	public void removeFriend(User user){
		this.friends.remove(user);
	}


	
	
    
    
}
