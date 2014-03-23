package fr.ups.l3info.l3info_catchgameactivity;

import fr.ups.l3info.l3info_catchgametemplate.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.TextView;

public class CatchGameMenuActivity  extends Activity {
	
	private TextView playButton;
	private TextView scoreButton;
	private TextView optionButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);


		playButton = (TextView)findViewById(R.id.textView1);
		scoreButton = (TextView)findViewById(R.id.textView2);
		optionButton = (TextView)findViewById(R.id.textView3);
		
		/*playButton.setVisibility(View.INVISIBLE);
		scoreButton.setVisibility(View.INVISIBLE);
		optionButton.setVisibility(View.INVISIBLE);*/
		
		playButton.setText("          ");
		scoreButton.setText("          ");
		optionButton.setText("          ");
		
		playButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("DEBUG","Appui sur bouton JOUER");
				Intent intent = new Intent(getBaseContext() , CatchGameActivity.class);     
				startActivity(intent);
				return true;
			}
		});
		
		
		scoreButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("DEBUG","Appui sur bouton SCORE");
				return true;
			}
		});


		optionButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("DEBUG","Appui sur bouton OPTIONS");
				return true;
			}
		});
	
	}

	protected void onDestroy() {
		super.onDestroy(); 
	}

	protected void onPause() {
		super.onPause(); 
	}

	protected void onResume() {
		super.onResume(); 
	}
	protected void onStart() {
		super.onStart(); 
	}

	protected void onStop() {
		super.onStop(); 
	} 

}
