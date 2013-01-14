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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locusta_map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    // Icones
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.ico_1);
	    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
	    
	    // GPS
	    float lat = 48.112474f;
	    float lng = -1.678905f;
	    GeoPoint point = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6)); // rennes => 48.112474, -1.678905 => a mettre en degree
	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
	    
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
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

}
