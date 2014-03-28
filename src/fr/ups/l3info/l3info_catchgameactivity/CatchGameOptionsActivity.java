package fr.ups.l3info.l3info_catchgameactivity;

import java.util.TooManyListenersException;

import fr.ups.l3info.l3info_catchgametemplate.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;


public class CatchGameOptionsActivity extends Activity {
	
	private SeekBar number;
	private SeekBar gravity;
	private ToggleButton buttonMusique;

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
		
		number = (SeekBar) findViewById(R.id.SeekBar01);
		gravity = (SeekBar) findViewById(R.id.SeekBar02);
		buttonMusique = (ToggleButton) findViewById(R.id.btnMusic);
		
		SharedPreferences prefs = getSharedPreferences("options", Context.MODE_PRIVATE);
		buttonMusique.setChecked(prefs.getBoolean("music", false));
		number.setProgress(prefs.getInt("number", 100));
		gravity.setProgress(prefs.getInt("gravity", 1));
		
		number.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				SharedPreferences prefs = getSharedPreferences("options", Context.MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putInt("number", number.getProgress());
				editor.commit();
			}
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
		
		gravity.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				SharedPreferences prefs = getSharedPreferences("options", Context.MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putInt("gravity", gravity.getProgress());
				editor.commit();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
		
		buttonMusique.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	        @Override
	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            	SharedPreferences prefs = getSharedPreferences("options", Context.MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putBoolean("music", isChecked);
				editor.commit();
	        }
	    });
	}

	private void buttonStartClickEventHandler() {
		Intent jeu = new Intent(this, CatchGameActivity.class);
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
