package locusta.project.services;

import java.util.List;

import javax.ejb.Remote;

import locusta.project.entities.Event;
import locusta.project.entities.EventType;
import locusta.project.entities.User;

@Remote
public interface Services {

	public Event addEvent(Event event);

	public Event getEventById(int id);

	// Radius in meters
	public List<Event> lookEventsAround(double longitude, double latitude,
			double radius, EventType eventType);

	public EventType addEventType(String name);

	public List<EventType> getEventTypes();
	
	public EventType getEventTypeById(int id);

	
	//////////////////////////////////////////////////////////////////////
	
    public User userRegistration(User user);
	
    public String encryptPassword(String password);
    
    public User getUserById(int id);
	
    public User getUserByUserName(String userName); 
	
    public List<User> searchUsers(String somethingLikeThat); 
    
    public void updateUser(User user);

}
