
package locusta.project.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * A JPA 2.0 Entity.
 */
@Entity
public class Event implements Serializable {


    private static final long serialVersionUID = -7448829558275268188L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable=false)
    private String name;
    
    /*
	 *	Please hash the password (with MD5 for example)
     *  before set the variable 
     */  
    @Column(nullable=true)
    private String description;
    
    @Column(nullable=false)
    private Date startDate;
    
    /* Per default : 1 hour after startDate seems good */
    @Column(nullable=false)
    private Date endDate;
    
    @Column(nullable=false)
    private double longitude;
    
    @Column(nullable=false)
    private double latitude;
    
    /* 
     * nullable is true, it is explicitly the default type event
     * EAGER fetch because it's only one value and it's important
     * No REMOVE CascadeType to
     * avoid accidental deletions of type
     */    
    @JoinColumn(nullable=true)
    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
    EventType eventType;
    
    /* 
     * nullable is false because an event must have a user!
     * EAGER fetch because it's only one value and it's important
     * No REMOVE CascadeType to
     * avoid accidental deletions of type
     */    
    @JoinColumn(nullable=false)
    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
    User owner;

    
    public Event() {
    	eventType = new EventType();
    }

    /* This constructor use only the non nullable fields */
	public Event(String name, String description, Date startDate,
			double longitude, double latitude, User owner) {
		super();
		eventType = new EventType();
		
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(this.startDate); 
	    cal.add(Calendar.HOUR_OF_DAY, 1); 	    
		this.endDate = cal.getTime();
				
		this.longitude = longitude;
		this.latitude = latitude;
		this.owner = owner;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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


	public EventType getEventType() {
		return eventType;
	}


	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}   
    
}
