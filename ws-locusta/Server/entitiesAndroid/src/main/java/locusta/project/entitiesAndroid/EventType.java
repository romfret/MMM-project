
package locusta.project.entitiesAndroid;


public class EventType implements IEntity {

    private int _id;

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

    
}
