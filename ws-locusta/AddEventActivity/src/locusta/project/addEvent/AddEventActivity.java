package locusta.project.addEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import locusta.project.entitiesAndroid.Event;
import locusta.project.entitiesAndroid.EventType;
import locusta.project.entitiesAndroid.User;
import locusta.project.temporarySave.TemporarySave;
import locusta.project.webClient.WebClient;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddEventActivity extends Activity {
    
	 private List<Integer> et_ids = new ArrayList<Integer>();
	 private int selectedId = -1;
	 private WebClient wc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEventType);       
       
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        
        
        et_ids.add(-1);
        adapter.add("all");
         wc = new WebClient();
        List<EventType> ets = wc.getEventTypes();
        for (EventType et : ets) {
        	et_ids.add(et.getId());
        	 adapter.add(et.getName());
        }
        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectedId = et_ids.indexOf(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    
    public void ok(View v) {    	
    	Button b = (Button) findViewById(R.id.okAdd);
    	b.setText("Pz wait...");
    	b.setEnabled(false);
    	final Activity self = this;
    	Thread t = new Thread(new Runnable() {
            public void run() {
            	EventType et = null;
            	if (selectedId != -1)
            		et = wc.getEventTypeById(selectedId);
            	System.err.println(selectedId);
            	EditText nameV = (EditText) findViewById(R.id.txtName);
            	String name = nameV.getText().toString();
            	EditText descrV = (EditText) findViewById(R.id.txtDescr);
            	String description = descrV.getText().toString();
            	Date now = new Date();
            	User current = TemporarySave.getInstance().getCurrentUser();
            	if (TemporarySave.getInstance().getCurrentUser() != null)
            		current = TemporarySave.getInstance().getCurrentUser();
            	else {
            		System.err.println("User indéfini, ssauvegarde de l'évènement interrompue");
            		return;
            	}
            	Event e = new Event(name, description, now, TemporarySave.getInstance().getLongitude(), TemporarySave.getInstance().getLatitude(), current);
            	e.setEventType(et);
            	wc.addEvent(e);
            	self.finish();
            }
        });	
    	t.start();
    	
    }
}