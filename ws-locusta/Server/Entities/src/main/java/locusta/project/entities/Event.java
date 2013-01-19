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
public class Event implements Serializable, IEntity {

	private static final long serialVersionUID = -7448829558275268188L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@Column(nullable = false)
	private String _name;

	/*
	 * Please hash the password (with MD5 for example) before set the variable
	 */
	@Column(nullable = true)
	private String _description;

	@Column(nullable = false)
	private Date _startDate;

	/* Per default : 1 hour after startDate seems good */
	@Column(nullable = false)
	private Date _endDate;

	@Column(nullable = false)
	private double _longitude;

	@Column(nullable = false)
	private double _latitude;

	/*
	 * nullable is true, it is explicitly the default type event EAGER fetch
	 * because it's only one value and it's important No REMOVE CascadeType to
	 * avoid accidental deletions of type
	 */
	@JoinColumn(nullable = true)
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	EventType _eventType;

	/*
	 * nullable is false because an event must have a user! EAGER fetch because
	 * it's only one value and it's important No REMOVE CascadeType to avoid
	 * accidental deletions of type
	 */
	@JoinColumn(nullable = false)
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	User _owner;

	public Event() {
		_eventType = new EventType();
	}

	/* This constructor use only the non nullable fields */
	public Event(String name, String description, Date startDate,
			double longitude, double latitude, User owner) {
		super();
		_eventType = new EventType();

		this._name = name;
		this._description = description;
		this._startDate = startDate;

		Calendar cal = Calendar.getInstance();
		cal.setTime(this._startDate);
		cal.add(Calendar.HOUR_OF_DAY, 1);
		this._endDate = cal.getTime();

		this._longitude = longitude;
		this._latitude = latitude;
		this._owner = owner;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		this._startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		this._endDate = endDate;
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

	public EventType getEventType() {
		return _eventType;
	}

	public void setEventType(EventType eventType) {
		this._eventType = eventType;
	}

	public User getOwner() {
		return _owner;
	}

	public void setOwner(User owner) {
		this._owner = owner;
	}

	public IEntity cloneForJson() {
		Event newEvent = new Event(this._name, this._description,
				this._startDate, this._longitude, this._latitude,
				(User) this._owner.cloneForJson());
		newEvent.setEndDate(this._endDate);
		newEvent.setEventType((EventType) this._eventType.cloneForJson());
		return newEvent;
	}

}
