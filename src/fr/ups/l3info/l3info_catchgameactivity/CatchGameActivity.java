package fr.ups.l3info.l3info_catchgameactivity;

import fr.ups.l3info.l3info_catchgamedatastructure.EnumFruit;
import fr.ups.l3info.l3info_catchgamedatastructure.Fruit;
import fr.ups.l3info.l3info_catchgametemplate.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/* 
 * Main screen for the game
 * Comments to be added
 * To be modified to implement your own version of the game
 */
public class CatchGameActivity extends Activity {

	private List<Fruit> fruitList;
	private CatchGameView fruitView;
	
	private ImageView bStart;
	private ImageView coeur1;
	private ImageView coeur2;
	private ImageView coeur3;
	private int fruitFallDelay = 2;
	private int fruitCreateDelay = 1000;
	private TextView affScore;
	private TextView affNbCatch;
	private int score = 0;
	private int nbCatch = 0;
	private int life = 3;
	private Timer timerFallingFruits;
	private Timer timerCreatingFruits;
	private int height;
	private int weight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catch_game);
		fruitView = (CatchGameView)findViewById(R.id.l3InfoCatchGameView1);
		bStart = (ImageView)findViewById(R.id.imageView1);
		this.coeur1 = (ImageView) findViewById(R.id.coeur1);
		this.coeur2 = (ImageView) findViewById(R.id.coeur2);
		this.coeur3 = (ImageView) findViewById(R.id.coeur3);
		
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
		this.initTimerFallingFruits();
		this.initTimerCreatingFruits();
		bStart.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.catch_game, menu);
		return true;
	}
	

	/*
	 * Timer qui fait apparaitre les fruits
	 */
	public void initTimerCreatingFruits(){
		timerCreatingFruits = new Timer();
		timerCreatingFruits.schedule(new TimerTask() {			
			@Override
			public void run() {
				timerCreateFruitEventHandler();
			}
			
		}, 0, fruitCreateDelay);
		
	}
	
	/*
	 * Timer qui fait descendre les fruits
	 */
	public void initTimerFallingFruits(){
		timerFallingFruits = new Timer();
		timerFallingFruits.schedule(new TimerTask() {			
			@Override
			public void run() {
				timerFallingFruitEventHandler();
			}
			
		}, 0, fruitFallDelay);
		
	}
	
	public void stopTimer(){
		timerFallingFruits.cancel();
	}
	
	/*
	 * EVENT HANDLER
	 */
	private void timerFallingFruitEventHandler(){
		for(Fruit fruit : this.fruitView.getFallingDownFruitsList()) {
			//fruit.getVue().offset(100, 0); // if we want add wind, modify second parameter
			fruit.setLocationInScreen(new Point(fruit.getLocationInScreen().x, fruit.getLocationInScreen().y + 2));
			if(fruit.getLocationInScreen().y > this.height) {
				this.lostLife();
			}
			//Log.i("DEBUG", "Fruit = "+fruit);
		}
		this.fruitView.postInvalidate();
	}
	
	private void lostLife() {
		switch(this.life) {
		case 0 : this.endOfGame();
			break;
		case 1 : 
			this.coeur1.setVisibility(View.INVISIBLE);
			this.coeur2.setVisibility(View.INVISIBLE);
			this.coeur3.setVisibility(View.VISIBLE);
			break;
		case 2 : 
			this.coeur1.setVisibility(View.INVISIBLE);
			this.coeur2.setVisibility(View.VISIBLE);
			this.coeur3.setVisibility(View.VISIBLE);
			break;
		case 3 : 
			this.coeur1.setVisibility(View.VISIBLE);
			this.coeur2.setVisibility(View.VISIBLE);
			this.coeur3.setVisibility(View.VISIBLE);
			break;
		
		}
		
	}

	private void endOfGame() {
		// TODO Auto-generated method stub
		// Structure to save scores.
		SharedPreferences prefs = this.getSharedPreferences("scores", Context.MODE_PRIVATE);
		int size = prefs.getAll().size();
		Editor editor = prefs.edit();
		editor.putInt("score"+size, this.score);
		editor.commit();
	}

	// Faire apparaitre un fruit
	private void timerCreateFruitEventHandler(){
		//this.fallingDownFruitsList.add(new Rect(0, 200, 50, 50));
		
	}
	

}
