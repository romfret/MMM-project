package com.example.locusta.project.sampleclientdorandroid;

import java.util.List;

import locusta.project.entitiesAndroid.User;
import locusta.project.webClient.WebClient;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private String inBuff = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}



	public void launch(View v) {
		
		new Thread(new Runnable() {
			
			public void run() {
				WebClient wc = new WebClient();
				println("Connexion etablie");

				User toto = wc.getUserByUserName("toto");
				
				if (toto == null) {
					toto = new User("toto",
							wc.encryptPassword("totopass"));
					toto = wc.userRegistration(toto);
					println("Utilisateur toto cree");
				}
				
				println("Recherche de toto par la chaine 'to'");
				List<User> usersFound = wc.searchUsers("to");

				println("Utilisateurs trouves : " + usersFound.size());
				User userFound = usersFound.get(0);

				while (true) {
					println("Tapez le mot de passe de l'utilisateur toto : ");
					String password;
			
					while(inBuff.equals("")) 
					{
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					password = inBuff;
					inBuff = "";
					
					
					
					//pas de == avec les string
					if (userFound.getPass().equals(wc
							.encryptPassword(password))) {				
						println("OK c'est le bon !");
						break;
					}
					println("Mauvais mot de passe !");
					println("righ md5 = " + toto.getPass());
					println("your md5 = " + wc
							.encryptPassword(password));
				}
			}
		}).start();
	
		
		
	}
	
	public void println(final String m) {
		runOnUiThread(new Runnable() {
	            public void run() {
	            	TextView tv = (TextView) findViewById(R.id.out);
	        		tv.setText(tv.getText() + "\r\n" + m);
	            }
	        });		
	}

	public void valid(View v) {
		inBuff = ((TextView) findViewById(R.id.in)).getText().toString();
	}


}
