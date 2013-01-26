package locusta.project.location;

import locusta.project.mapacti.LocustaMapActivityMain;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

/**
 * This class extends MyLocationOverlay.<p>
 * It's provide an itemOverlay of the user and is tracked on mapView.<p>
 * It's provide also a geolocation for the mapActivity
 */
public class UserLocationOverlay extends MyLocationOverlay {

	private LocustaMapActivityMain locustaMapAcivityMain;

	public UserLocationOverlay(LocustaMapActivityMain locustaMapAcivityMain, MapView mapView) {
		super(locustaMapAcivityMain, mapView);
		this.locustaMapAcivityMain = locustaMapAcivityMain;
	}

	@Override
	public synchronized void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		
		GeoPoint p = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
		locustaMapAcivityMain.onLocationChanged(p);
	}

	@Override
	public GeoPoint getMyLocation() {
		GeoPoint p = super.getMyLocation();
		if (p == null)
			return new GeoPoint((int)(48.114722f * 1E6), (int)(-1.679444f * 1E6)); // Rennes location
		else
			return p;
	}

	
}
