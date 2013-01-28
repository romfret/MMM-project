package locusta.project.speech;

import java.util.HashMap;
import java.util.Locale;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;

public class TTSService extends Service implements OnInitListener,
		OnUtteranceCompletedListener {
	TextToSpeech mTTS;
	int ready = 999;
	String str;

	@Override
	public void onCreate() {
		Log.d("", "TTSService Created!");
		mTTS = new TextToSpeech(getApplicationContext(), this);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (ready == 999) {
					// wait
				}
				if (ready == 1) {
					HashMap<String, String> myHashStream = new HashMap<String, String>();
					myHashStream.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
							String.valueOf(AudioManager.STREAM_NOTIFICATION));
					myHashStream.put(
							TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "1");

					mTTS.setLanguage(Locale.FRANCE);
					// mTTS.setOnUtteranceCompletedListener(this);
					mTTS.speak(str, TextToSpeech.QUEUE_FLUSH,
							myHashStream);

				} else {
					Log.d("", "not ready");
				}
			}

		}).start();

		stopSelf();

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		mTTS.shutdown();
		super.onDestroy();
	}

	@Override
	public void onInit(int status) {
		Log.d("", "TTSService onInit: " + String.valueOf(status));
		if (status == TextToSpeech.SUCCESS) {
			ready = 1;
		} else {
			ready = 0;
			Log.d("", "failed to initialize");
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("tante"+intent.getStringExtra("textToSay"));
		str = intent.getStringExtra("textToSay");
		return super.onStartCommand(intent, flags, startId);
	}

	public void onUtteranceCompleted(String uttId) {
		Log.d("", "done uttering");
		if (uttId == "1") {
			mTTS.shutdown();
		}
	}

}