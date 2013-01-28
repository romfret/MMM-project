/*
 * Copyright (c) 2011, Lauren Darcey and Shane Conder
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this list of 
 *   conditions and the following disclaimer.
 *   
 * * Redistributions in binary form must reproduce the above copyright notice, this list 
 *   of conditions and the following disclaimer in the documentation and/or other 
 *   materials provided with the distribution.
 *   
 * * Neither the name of the <ORGANIZATION> nor the names of its contributors may be used
 *   to endorse or promote products derived from this software without specific prior 
 *   written permission.
 *   
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * <ORGANIZATION> = Mamlambo
 */
package locusta.project.speech;

import java.util.ArrayList;
import java.util.Locale;

import locusta.project.entitiesAndroid.User;
import locusta.project.mapacti.R;
import locusta.project.temporarySave.TemporarySave;
import locusta.project.webClient.WebClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SampleSpeechActivity extends Activity implements OnInitListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speech);
		
		WebClient c = new WebClient();
		System.out.println("user="+c.getUserById(1));
		
		User u = TemporarySave.getInstance().getCurrentUser();
		u.getLatitude();
		u.getLongitude();
		
		//Event e = new Event(name, description, startDate, lon, lat, owner);
		
	}

	private TextToSpeech mTextToSpeech = null;

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void listenToMe(String text) {

		mTextToSpeech = new TextToSpeech(getApplicationContext(), this);
		// if (!speechSynthReady) {
		// Toast.makeText(getApplicationContext(),"Speech Synthesis not ready.",
		// Toast.LENGTH_SHORT).show();
		// return;
		// }
		int result = mTextToSpeech.setLanguage(Locale.FRANCE);
		if (result == TextToSpeech.LANG_MISSING_DATA
				|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
			Toast.makeText(
					getApplicationContext(),
					"Language not available. Check code or config in settings.",
					Toast.LENGTH_SHORT).show();
		} else {
			mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}

		mTextToSpeech.shutdown();
		mTextToSpeech = null;

	}

	@Override
	public void onInit(int status) {
		// if (status == TextToSpeech.SUCCESS) {
		// speechSynthReady = true;
		// }
	}

	// Speech recognition
	private static final int VOICE_RECOGNITION_REQUEST = 0x10101;

	public void speakToMe(View view) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				"Please speak slowly and enunciate clearly.");
		startActivityForResult(intent, VOICE_RECOGNITION_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		
		
		if (resultCode != RESULT_OK && requestCode == VOICE_RECOGNITION_REQUEST)
			return;
		if (requestCode == VOICE_RECOGNITION_REQUEST && resultCode == RESULT_OK) {
			ArrayList<String> matches = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			TextView textView = (TextView) findViewById(R.id.speech_io_text);
			String firstMatch = matches.get(0);
			
			Intent intentTTS = new Intent(this.getApplicationContext(), TTSService.class);
			intentTTS.putExtra("textToSay", firstMatch);
			startService(intentTTS);
			
			textView.setText(firstMatch);
		}
	}

	private void parseText(String text) {
		if (text.startsWith("ajouter")) {
			TextToSpeech mTextToSpeech2 = new TextToSpeech(
					getApplicationContext(), this);
			mTextToSpeech2.setLanguage(Locale.FRANCE);
			mTextToSpeech2.speak("evenement reconnu", TextToSpeech.QUEUE_FLUSH,
					null);
		}
	}

}