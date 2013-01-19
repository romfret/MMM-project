package locusta.project.services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import locusta.project.entities.Event;
import locusta.project.entities.EventType;
import locusta.project.entities.User;

@Remote
@WebService
public interface Services {

	public Event addEvent(Event event);

	public Event getEventById(int id);

	// Because there can be many events with the same name
	public List<Event> searchEvents(String somethingLikeThat);

	// Radius in meters
	public List<Event> lookEventsAround(double longitude, double latitude,
			double radius);

	public EventType addEventType(String name);

	public List<EventType> getEventTypes();

	// TODO Put others methods here
	
	//////////////////////////////////////////////////////////////////////
	
    public User userRegistration(User user);
	
    public String encryptPassword(String password);
    
    public User getUserById(int id);
	
    public User getUserByUserName(String userName); 
	
    public List<User> searchUsers(String somethingLikeThat); 
    
    public void updateUser(User user);

}
