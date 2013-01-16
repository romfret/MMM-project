package projet.locusta.mapacti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import locusta.project.entities.Event;
import locusta.project.entities.User;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class LocustaMapActivity extends MapActivity {
	
	private List<Overlay> mapOverlays;
	private Map<Integer, MapItemizedOverlay> itemzedOverlays;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locusta_map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    itemzedOverlays = new HashMap<Integer, MapItemizedOverlay>();
	    
	    // Icones Bars
	    Drawable drawable = this.getResources().getDrawable(R.drawable.img_37);
	    itemzedOverlays.put(37, new MapItemizedOverlay(drawable, this));
	    
	    // overlay's add 
	    mapOverlays = mapView.getOverlays();
	    
	    // Ad items to map
	    for (MapItemizedOverlay item : itemzedOverlays.values()) {
			mapOverlays.add(item);
		}
	    
	    Date d = new Date();
	    User u = new User("userName", "pass");
	    
	    Event rennes = new Event("La rue de la soif", "De la boisson à foison :)", d, -1.678905f, 48.112474f, u);
	    rennes.getEventType().setId(37);
	    
	    Collection<Event> events = new ArrayList<Event>();
	    events.add(rennes);
	    addEvents(events);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_locusta_map, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	/**
	 * Add events on the map
	 * @param events
	 */
	public void addEvents(Collection<Event> events) {
		for (Event event : events) {
			itemzedOverlays.get(event.getEventType().getId()).addOverlay(createOverlayItem(event));
		}
	}
	
	private OverlayItem createOverlayItem(Event event) {
		GeoPoint point = new GeoPoint((int)(event.getLatitude() * 1E6), (int)(event.getLongitude() * 1E6));
	    return new OverlayItem(point, event.getName(), event.getDescription());
	}

}
