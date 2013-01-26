package locusta.project.temporarySave;

import locusta.project.entitiesAndroid.User;

public class TemporarySave {
	private static TemporarySave instance = null;
	private User currentUser; // TODO à supr lors de l'jout de l apartie de dany


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

	
}
