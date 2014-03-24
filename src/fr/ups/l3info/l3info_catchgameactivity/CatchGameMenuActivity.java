package fr.ups.l3info.l3info_catchgameactivity;

import fr.ups.l3info.l3info_catchgametemplate.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CatchGameMenuActivity  extends Activity {
	
	private ImageView playButton;
	private ImageView scoreButton;
	private ImageView optionButton;
	private boolean firstTouch;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_v2);
		firstTouch = true;

		
		playButton = (ImageView) findViewById(R.id.ImageView06);
		scoreButton = (ImageView) findViewById(R.id.ImageView04);
		optionButton = (ImageView) findViewById(R.id.ImageView07);
		
		
		playButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("DEBUG","Appui sur bouton JOUER");
				if(firstTouch){
					firstTouch = false;
					Intent jeu = new Intent(CatchGameMenuActivity.this, CatchGameActivity.class);
		            startActivity(jeu);
					return true;
				}
				return false;
			}
		});
		
		
		scoreButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(firstTouch){
					firstTouch = false;
					Log.i("DEBUG","Appui sur bouton SCORE");
					Intent score = new Intent(CatchGameMenuActivity.this, CatchGameScoreActivity.class);
		            startActivity(score);
				}
				return true;
			}
		});


		optionButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(firstTouch){
					firstTouch = false;
					Log.i("DEBUG","Appui sur bouton OPTIONS");
					Intent option = new Intent(CatchGameMenuActivity.this, CatchGameOptionsActivity.class);
		            startActivity(option);
				}
				return true;
			}
		});
	
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
		firstTouch = true;
		super.onResume(); 
	}
	@Override
	protected void onStart() {
		firstTouch = true;
		super.onStart(); 
	}

	@Override
	protected void onStop() {
		super.onStop(); 
	} 

}
