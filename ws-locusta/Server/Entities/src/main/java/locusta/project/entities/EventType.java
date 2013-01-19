
package locusta.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * A JPA 2.0 Entity.
 */
@Entity
public class EventType implements Serializable, IEntity {


    private static final long serialVersionUID = -7448829558275268188L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int _id;

    @Column(nullable=false, unique=true)
    private String _name;
    
    public EventType() {

    }    

	public EventType(String name) {
		super();
		this._name = name;
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
	
	public IEntity cloneForJson() {
		EventType newEventType = new EventType(this._name);
		newEventType.setId(this._id);
		return newEventType;
	}

    
}
