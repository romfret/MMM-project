package locusta.project.entitiesAndroid;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class Event implements Serializable {

	private static final long serialVersionUID = -7448829558275268188L;

	private int id;
	private String name;

	private String description;

	private Date startDate;

	private Date endDate;

	private double longitude;

	private double latitude;


	EventType eventType;


	User owner;

	public Event() {
		eventType = null;
	}

	/* This constructor use only the non nullable fields */
	public Event(String name, String description, Date startDate,
			double lon, double lat, User owner) {
		super();

		this.name = name;
		this.description = description;
		this.startDate = startDate;

		Calendar cal = Calendar.getInstance();
		cal.setTime(this.startDate);
		cal.add(Calendar.HOUR_OF_DAY, 24);
		this.endDate = cal.getTime();

		this.longitude = lon;
		this.latitude = lat;
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

	public void setLongitude(double lon) {
		this.longitude = lon;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double lat) {
		this.latitude = lat;
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
