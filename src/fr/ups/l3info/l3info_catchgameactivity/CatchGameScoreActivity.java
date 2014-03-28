package fr.ups.l3info.l3info_catchgameactivity;

import java.util.ArrayList;

import fr.ups.l3info.l3info_catchgametemplate.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;


public class CatchGameScoreActivity extends Activity {
	
	private ListView liste;
	private ImageView bPlay;
	private Button bBack;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		bPlay = (ImageView)findViewById(R.id.ImageView006);
		bBack = (Button)findViewById(R.id.button1);
		// Structure to save scores.
		SharedPreferences prefs = this.getSharedPreferences("scores", Context.MODE_PRIVATE);
		
		ArrayList<String> values = new ArrayList<String>();
		
		values.add("Dernier score - "+prefs.getInt("lastscore", 0));
		values.add("Meilleur score - "+prefs.getInt("bestscore", 0));

		
		liste = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);

	    //Assign adapter to ListView
		liste.setAdapter(adapter); 
		
		bPlay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				buttonStartClickEventHandler();
			}
		});

		bBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				buttonBackClickEventHandler();
			}
		});
	}

	private void buttonStartClickEventHandler() {
		Intent jeu = new Intent(this, CatchGameActivity.class);
        startActivity(jeu);
        this.finish();
	}
	private void buttonBackClickEventHandler() {
		Intent jeu = new Intent(this, CatchGameMenuActivity.class);
        startActivity(jeu);
        this.finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy(); 
	}

	@Override
	protected void onPause() {
		super.onPause(); 
	}

	@Override
	protected void onResume() {
		super.onResume(); 
	}
	@Override
	protected void onStart() {
		super.onStart(); 
	}

	@Override
	protected void onStop() {
		super.onStop(); 
	} 

}
