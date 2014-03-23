package fr.ups.l3info.l3info_catchgameactivity;

import fr.ups.l3info.l3info_catchgamedatastructure.EnumFruit;
import fr.ups.l3info.l3info_catchgamedatastructure.Fruit;
import fr.ups.l3info.l3info_catchgametemplate.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/* 
 * Main screen for the game
 * Comments to be added
 * To be modified to implement your own version of the game
 */
public class CatchGameActivity extends Activity {

	List<Fruit> fruitList;
	CatchGameView fruitView;
	Button bStart;
	Button bStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catch_game);
		
		fruitView = (CatchGameView)findViewById(R.id.l3InfoCatchGameView1);
		fruitView.setComponents();
		
		bStart = (Button)findViewById(R.id.buttonStart);
		bStop = (Button)findViewById(R.id.buttonStop);
		bStop.setEnabled(false);
		
		bStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				buttonStartClickEventHandler();
				
			}

		});
		
		testInitFruitList();
		//fruitView.setFruitList(fruitList);
		
		final Random r = new Random();
		
		Timer timerCreatingFruits = new Timer();
		timerCreatingFruits.schedule(new TimerTask() {			
			@Override
			public void run() {
				DisplayMetrics metrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(metrics);
				fruitView.addFruit(new Fruit(new Point(r.nextInt(metrics.widthPixels-50),0),100, EnumFruit.getRandomValue()));
			}
			
		}, 0, 500);
		
	}

	private void testInitFruitList() {
		fruitList = new ArrayList<Fruit>();
		fruitList.add(new Fruit(new Point(15, 15), 22, EnumFruit.getRandomValue()));
		
	}

	private void buttonStartClickEventHandler() {
		fruitView.initTimerFallingFruits();
		fruitView.initTimerCreatingFruits();		
		bStop.setEnabled(true);
		bStart.setEnabled(false);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.catch_game, menu);
		return true;
	}

}
