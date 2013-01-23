
package locusta.project.entitiesAndroid;

import java.io.Serializable;

public class EventType implements Serializable{


    private static final long serialVersionUID = -7448829558275268188L;

    private int id;

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

    
}
