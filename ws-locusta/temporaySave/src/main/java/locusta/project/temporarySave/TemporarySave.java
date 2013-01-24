package locusta.project.temporarySave;

import locusta.project.entitiesAndroid.User;

public class TemporarySave {
	private static TemporarySave instance = null;
	private User currentUser;
	private double latitude;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double altitude) {
		this.latitude = altitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
