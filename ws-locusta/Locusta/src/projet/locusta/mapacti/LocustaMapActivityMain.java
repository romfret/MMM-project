package projet.locusta.mapacti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import locusta.project.entitiesAndroid.Event;
import locusta.project.entitiesAndroid.EventType;
import locusta.project.entitiesAndroid.User;
import projet.locusta.item.ItemizedOverlaysInitialization;
import projet.locusta.item.MapItemizedOverlay;
import projet.locusta.location.GeolocalisationService;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class LocustaMapActivityMain extends MapActivity {
	
	private MapView mapView;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private Map<Integer, MapItemizedOverlay> itemzedOverlays;
	private GeolocalisationService geolocalisationService;
	private MyLocationOverlay userLocationOverlay;
	
//	private float distance = 10000.0f; // The event distance in meter
	private int zoomLevel = 17; // Map zoom

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locusta_map);
		
		// Get map service
		mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    // Get map controller
	    mapController = mapView.getController();
	    mapController.setZoom(zoomLevel);
	    
	    // Get the geolocalisation service
	    geolocalisationService = new GeolocalisationService(this);
	    
	    // Add item's icons in a Map TODO
	    ItemizedOverlaysInitialization itemInit = new ItemizedOverlaysInitialization();
	    itemzedOverlays = itemInit.init(this);
	    
	    mapOverlays = mapView.getOverlays();
	    // Add items to map
	    for (MapItemizedOverlay item : itemzedOverlays.values()) {
			mapOverlays.add(item);
		}
	    
	    // Add user position item marker
	    userLocationOverlay = new MyLocationOverlay(this, mapView);
	    mapOverlays.add(userLocationOverlay);
	    userLocationOverlay.enableMyLocation();
	    /* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	     * userLocationOverlay contient les coordonnées de l'utilisateur. Est-ce possible de gérer le centrage
	     *  de la carte sur l'utilisateur avec un onLocationChanged ?? 
	     */
	    	    
	    // test
	    Date d = new Date();
	    User u = new User("userName", "pass");
	    
	    Event rennes = new Event("La rue de la soif", "De la boisson à foison :)", d, -1.678905f, 48.112474f, u);
	    EventType eventType = new EventType("Bars");
	    eventType.setId(37);
	    rennes.setEventType(eventType);
	    
	    Event rennesBouffe = new Event("La rue de la bouffe", "De la bouffe à foison :)", d, -1.681255f, 48.105397f, u);
	    EventType eventType2 = new EventType("Restaurant");
	    eventType2.setId(39);
	    rennesBouffe.setEventType(eventType2);
	    
	    Collection<Event> events = new ArrayList<Event>();
	    events.add(rennes);
	    events.add(rennesBouffe);
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
	 * Menu selection
	 */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.menu_clear_events :
			clearEvents();
			Toast.makeText(getApplicationContext(), "Events cleared", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_friends :
			// TODO La partie de Dany :)
			Toast.makeText(getApplicationContext(), "La partie de Dany :)", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_current_location :
			GeoPoint currentLocation = userLocationOverlay.getMyLocation();

			showToast(LocationManager.GPS_PROVIDER);
			showToast(currentLocation.getLatitudeE6() / 1E6 + ", " + currentLocation.getLongitudeE6() / 1E6);

			Geocoder geo = new Geocoder(this);
			String msg = "";
			try {
				List<Address> addresses = geo.getFromLocation(currentLocation.getLatitudeE6() / 1E6, currentLocation.getLongitudeE6() / 1E6, 1);
				
				if(addresses != null && addresses.size() >= 1) {
					Address address = addresses.get(0);
					msg = String.format("%s, %s %s",	address.getAddressLine(0), address.getPostalCode(),	address.getLocality());
				}
				else {
					msg = "No address was found !";
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		default:
			System.out.println("Menu id unrecognized");
			break;
		}
    	return true;
    }
    
    //---------------- Event ----------------//
	
	/**
	 * Add events on the map
	 * @param events
	 */
	public void addEvents(Collection<Event> events) {
		for (Event event : events) {
			// event.getEventType().getId() is the ID of the item marker
			itemzedOverlays.get(event.getEventType().getId()).addOverlay(createOverlayItem(event));
		}
	}
	
	/**
	 * Add only one event on the map
	 * @param event
	 */
	public void addEvent(Event event) {
		itemzedOverlays.get(event.getEventType().getId()).addOverlay(createOverlayItem(event));
	}
	
	/**
	 * Create an overlay item contain location and descriptions about an event
	 * @param event : contain location and descriptions
	 * @return
	 */
	private OverlayItem createOverlayItem(Event event) {
		GeoPoint point = new GeoPoint((int)(event.getLat() * 1E6), (int)(event.getLongitude() * 1E6));
	    return new OverlayItem(point, event.getName(), event.getDescription());
	}
	
	/**
	 * clear the event on the map
	 */
	public void clearEvents() {
		for (MapItemizedOverlay item : itemzedOverlays.values()) {
			item.clearOverlays();
		}
	}
	
	//---------------- Location ----------------//
	
	protected void onResume() {
		super.onResume();
		geolocalisationService.onResume();
	}
	
	protected void onPause() {
		super.onPause();
		geolocalisationService.onPause();
	}
	
	/**
	 * Map position refresh
	 * @param p
	 */
	public void onLocationChanged(GeoPoint p) {
		// Center the map on user
		mapController.animateTo(p);
		mapController.setCenter(p);
		showToast(p.toString()); // TODO : a effacer apres tests
	}
	
	public void showToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}

}
