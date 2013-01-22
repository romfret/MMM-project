package locusta.project.temporarySave;

import locusta.project.entitiesAndroid.User;

public class TemporarySave {
	private static TemporarySave instance = null;
	private User currentUser;
	private double altitude;
	private double longitude;

	public static TemporarySave getInstance() {
		if (instance == null)
			instance = new TemporarySave();
		return instance;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User user) {
		currentUser = user;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
