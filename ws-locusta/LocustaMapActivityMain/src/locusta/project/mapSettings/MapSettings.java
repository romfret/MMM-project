package locusta.project.mapSettings;

import java.util.ArrayList;
import java.util.List;

import locusta.project.entitiesAndroid.EventType;
import locusta.project.mapacti.R;
import locusta.project.webClient.WebClient;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class MapSettings extends Activity {
    /** Called when the activity is first created. */

    private List<Integer> et_ids = new ArrayList<Integer>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_settings);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEventTypes);       
       
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        
        
        et_ids.add(-1);
        adapter.add("all");
        WebClient wc = new WebClient();
        List<EventType> ets = wc.getEventTypes();
        for (EventType et : ets) {
        	et_ids.add(et.getId());
        	 adapter.add(et.getName());
        }
        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                SharedPreferences sp = getSharedPreferences("locusta_settings", MODE_WORLD_WRITEABLE);
                Editor e = sp.edit();
                e.putInt("idEventType", et_ids.get(pos));     
				e.commit();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbarRadius);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView lm = (TextView) findViewById(R.id.lblMeters);
				lm.setText(String.valueOf(progress) + "m");
				SharedPreferences sp = getSharedPreferences("locusta_settings", MODE_WORLD_WRITEABLE);
				Editor e = sp.edit();
				e.putInt("radius", progress);	
				e.commit();
				
				
			}
		});
        
        
        //Chargement des valeurs initiales
    	SharedPreferences sp = getSharedPreferences("locusta_settings", MODE_WORLD_WRITEABLE);
    	int radius = sp.getInt("radius", 0);
    	seekBar.setProgress(radius);
    	Integer item = sp.getInt("idEventType", 0);
    	if (item == null || item == -1) 
    		spinner.setSelection(0);
    	else
    		spinner.setSelection(et_ids.indexOf(item), false);

    	
    }
    
    public void validSettings(View v) {
    	this.finish();
    }
}