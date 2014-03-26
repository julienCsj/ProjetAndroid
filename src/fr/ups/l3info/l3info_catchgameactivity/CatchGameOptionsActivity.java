package fr.ups.l3info.l3info_catchgameactivity;

import fr.ups.l3info.l3info_catchgametemplate.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class CatchGameOptionsActivity extends Activity {
	

	private ImageView bPlay;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		bPlay = (ImageView)findViewById(R.id.ImageView006);
		bPlay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				buttonStartClickEventHandler();
			}
		});
	}

	private void buttonStartClickEventHandler() {
		Intent jeu = new Intent(this, CatchGameActivity.class);
        startActivity(jeu);
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
