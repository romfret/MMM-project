package locusta.project.services;


import java.util.List;

import javax.ejb.Remote;

import locusta.project.entities.Event;
import locusta.project.entities.EventType;
import locusta.project.entities.User;


@Remote
public interface ServicesEvent {

	  public Event addEvent(Event event);
	    
	  public User getById(int id);
	  
	  //Because there can be many events with the same name
	  public List<Event> getByEventName(String eventName); 
	  
	//Because there can be many events with the same name
	  public List<Event> searchEvents(String somethingLikeThat);  
	  
	  //Radius in meters
      public List<Event> lookEventsAround(double longitude, double latitude, double radius);
      
      public EventType addEventType(String name);
      public List<EventType> getEventTypes();
      

	//TODO Put others methods here

	
}
