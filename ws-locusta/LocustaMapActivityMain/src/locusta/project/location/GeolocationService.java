package locusta.project.location;

import locusta.project.mapacti.LocustaMapActivityMain;
import locusta.project.mapacti.R;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;

public class GeolocationService implements LocationListener {

	// http://www.tutos-android.com/geolocalisation-android
	
	private LocustaMapActivityMain activity;
	private LocationManager locationManager;
	private Location location;
	
	public GeolocationService(LocustaMapActivityMain activity) {
		this.activity = activity;
		location = new Location(LocationManager.GPS_PROVIDER);
	}
	
	//---------------- Listener ----------------//
	
	public void onLocationChanged(Location location) {
		this.location = location;
		
		GeoPoint p = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
		activity.onLocationChanged(p);
	}

	public void onProviderDisabled(String provider) {
		String msg = String.format(activity.getResources().getString(R.string.provider_disabled), provider);
		activity.showToast(msg);
	}

	public void onProviderEnabled(String provider) {
		String msg = String.format(activity.getResources().getString(R.string.provider_enabled), provider);
		activity.showToast(msg);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		String newStatus = "";
		switch (status) {
			case LocationProvider.OUT_OF_SERVICE:
				newStatus = "OUT_OF_SERVICE";
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				newStatus = "TEMPORARILY_UNAVAILABLE";
				break;
			case LocationProvider.AVAILABLE:
				newStatus = "AVAILABLE";
				break;
		}
		String msg = String.format(activity.getResources().getString(R.string.provider_new_status), provider, newStatus);
		activity.showToast(msg);
	}
	
	//---------------- Life cycle ----------------//
	
	// Subscription
	public void onResume() {
		locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
	}
	
	// Unsubscribe
	public void onPause() {
		locationManager.removeUpdates(this);
	}
	
	//---------------- Other ----------------//
	
	public Location getLocation() {
		return location;
	}

}
