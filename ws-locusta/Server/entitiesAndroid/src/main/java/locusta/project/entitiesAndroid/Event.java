package locusta.project.entitiesAndroid;

import java.util.Calendar;
import java.util.Date;

public class Event implements IEntity {


	private int _id;

	private String _name;

	/*
	 * Please hash the password (with MD5 for example) before set the variable
	 */

	private String _description;

	private Date _startDate;

	/* Per default : 1 hour after startDate seems good */

	private Date _endDate;

	private double _longitude;

	private double _latitude;

	EventType _eventType;

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

	

}
