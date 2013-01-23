
package locusta.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import locusta.project.entities.IEntity;


/**
 * A JPA 2.0 Entity.
 */
@Entity
public class EventType implements Serializable, IEntity {


    private static final long serialVersionUID = -7448829558275268188L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable=false, unique=true)
    private String name;
    
    public EventType() {

    }    

	public EventType(String name) {
		super();
		this.name = name;
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
	
	public IEntity cloneForJson() {
		EventType newEventType = new EventType(this.name);
		newEventType.setId(this.id);
		return newEventType;
	}

    
}
