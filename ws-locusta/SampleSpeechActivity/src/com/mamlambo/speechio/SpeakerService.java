package com.mamlambo.speechio;

import java.util.Locale;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.Toast;

public class SpeakerService extends Service implements OnInitListener {

	public static TextToSpeech mtts;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		Log.d("SpeakerService", "Service created successfully!");
		mtts = new TextToSpeech(getApplicationContext(), this);
		mtts.setLanguage(Locale.FRANCE);
	}

	@Override
	public void onStart(Intent intent, int startid) {
		Log.d("SpeakerService", "Service started successfully!");
		Log.d("SpeakerService", "tspker.mtts = ");
		mtts = new TextToSpeech(getApplicationContext(), this);
		mtts.setLanguage(Locale.FRANCE);
		mtts.speak("UN MESSAGE bine long pour q'on puisse l'entendre longtemps et pas le louper !", TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	public void onDestroy() {
		if (mtts != null) {
			mtts.stop();
			Toast.makeText(getApplicationContext(), "The service has been destroyed!", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub
	}

}