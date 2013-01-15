package projet.locusta.mapacti;

import java.util.List;

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
	private MapItemizedOverlay barsItemizedOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locusta_map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    // Icones Bars
	    Drawable drawable = this.getResources().getDrawable(R.drawable.img_37);
	    barsItemizedOverlay = new MapItemizedOverlay(drawable, this);

	    // overlay's add 
	    mapOverlays = mapView.getOverlays();
	    mapOverlays.add(barsItemizedOverlay);
	    
	    addBar("La rue de la soif", "De la boisson à foison :)", 48.112474f, -1.678905f);
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
	 * Add a new bar on map
	 */
	public void addBar(String name, String description, float lat, float lng) {

		// GPS location
	    GeoPoint point = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
	    OverlayItem overlayitem = new OverlayItem(point, name, description);
	    
	    barsItemizedOverlay.addOverlay(overlayitem);
	}

}
