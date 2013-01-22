package locusta.project.entitiesAndroid;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class Event implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1148258706503362545L;

	private int id;

	private String name;

	private String description;

	private Date startDate;

	private Date endDate;
	private double lon;
	private double lat;

	EventType eventType;

	User owner;

	public Event() {
		eventType = null;
	}

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

		this.lon = lon;
		this.lat = lat;
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
		return lon;
	}

	public void setLongitude(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
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
