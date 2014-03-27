package fr.ups.l3info.l3info_catchgameactivity;

import fr.ups.l3info.l3info_catchgamedatastructure.EnumFruit;
import fr.ups.l3info.l3info_catchgamedatastructure.Fruit;
import fr.ups.l3info.l3info_catchgametemplate.R;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
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
	private ImageView bRegame;
	private ImageView coeur1;
	private ImageView coeur2;
	private ImageView coeur3;
	private TextView scoreTextView;
	private TextView basket;
	private int fruitFallDelay;
	private int fruitCreateDelay;
	private TextView affScore;
	private TextView affNbCatch;
	private MediaPlayer player;
	private boolean music = false;
	private int score = 0;
	private int nbCatch = 0;
	private int life;
	private Timer timerFallingFruits;
	private Timer timerCreatingFruits;
	private int height;
	private int width;
	private int nbScore;
	private int optionGravity;
	private int optionNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catch_game);

		// On récupère les options (entre 0 et 100 actuellement)
		SharedPreferences prefs = getSharedPreferences("options", Context.MODE_PRIVATE);
		// On récupère la bonne valeur suivant la clé, l'entier en deuxième paramètre est la valeur par défaut si
		// la clé n'a pas été initialisée (le joueur n'est jamais allé dans les options)
		optionGravity = prefs.getInt("gravity", 1);
		optionNumber = prefs.getInt("number", 100);
		
		music  = prefs.getBoolean("music", false);
		fruitView = (CatchGameView)findViewById(R.id.l3InfoCatchGameView1);
		bStart = (ImageView)findViewById(R.id.imageView1);
		this.coeur1 = (ImageView) findViewById(R.id.coeur1);
		this.coeur2 = (ImageView) findViewById(R.id.coeur2);
		this.coeur3 = (ImageView) findViewById(R.id.coeur3);
		this.scoreTextView = (TextView) findViewById(R.id.score);
		this.basket = (TextView) findViewById(R.id.basket);
		this.life = 3;

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		this.height = metrics.heightPixels;
		this.width = metrics.widthPixels;

		fruitCreateDelay = 1000;
		fruitFallDelay = 2;

		bStart.setVisibility(View.INVISIBLE);
		buttonStartClickEventHandler();

		//Bypass
		bStart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				v.setVisibility(View.INVISIBLE);
				buttonStartClickEventHandler();
			}
		});
		fruitList = new ArrayList<Fruit>();
		
		// Set the music player if the user checked the option
		if(this.music) {
			player = MediaPlayer.create(CatchGameActivity.this, R.drawable.bgsound); 
	        player.setLooping(true); // Set looping 
	        player.setVolume(100,100); 
	        player.start(); 
		}
		
		
		
	}

	private void buttonStartClickEventHandler() {
		timerFallingFruits = null;
		timerCreatingFruits = null;
		this.initTimerCreatingFruits();
		this.initTimerFallingFruits();
		this.life = 3;
		this.score = 0;
		this.nbCatch = 0;
	}

	private void buttonRegameClickEventHandler() {
		this.buttonStartClickEventHandler();
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
				final Random r = new Random();
				switch( r.nextInt(4)){
					case 0 :
						fruitCreateDelay = 800;
						break;
					case 1 :
						fruitCreateDelay = 600;
						break;
					case 2 :
						fruitCreateDelay = 400;
						break;
					default :
						fruitCreateDelay = 200;
				}
				fruitCreateDelay -= optionNumber;
				initTimerCreatingFruits();
			}

		}, fruitCreateDelay);

	}

	// Faire apparaitre un fruit
	private void timerCreateFruitEventHandler(){
		//this.fallingDownFruitsList.add(new Rect(0, 200, 50, 50));
		final Random r = new Random();
		fruitView.addFruit(new Fruit(new Point(r.nextInt(width -80)+40,0),this.getWidthFruit(), EnumFruit.getRandomValue()));
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
	/*
	 * EVENT HANDLER
	 */
	private void timerFallingFruitEventHandler(){
		for(Fruit fruit : this.fruitView.getFallingDownFruitsList()) {
			fruit.setLocationInScreen(new Point(fruit.getLocationInScreen().x, this.getFruitYFalling(fruit.getLocationInScreen().y)));
			if(fruit.getLocationInScreen().y > this.height) {
				Log.i("VIE", "Une vie a été perdu");
				this.life -= 1;
				// Make the phone vibrate
				Vibrator vib=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		        vib.vibrate(100);
				this.lostLife();
				if(this.life == 0) {
					this.endOfGame();
				}
				this.fruitView.getFallingDownFruitsList().remove(fruit);
			}
		}
		this.fruitView.postInvalidate();	
	}
	public int getFruitYFalling(int y){
		y += optionGravity;
		if(this.score > 5000){
			return y + 5;
		}
		if(this.score > 3000){
			return y + 4;
		}
		if(this.score > 1000){
			return y + 3;
		}
		if(this.score > 500){
			return y + 2;
		}
		return y + 1;
	}


	public void lostLife() {
		runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // This code will always run on the UI thread, therefore is safe to modify UI elements.
            	Log.i("VIE", "Traitement perte vie"+life);
            	System.out.println(life);
            	switch(life) {
            	case 0 : 
        			coeur1.setVisibility(View.INVISIBLE);
        			coeur2.setVisibility(View.INVISIBLE);
        			coeur3.setVisibility(View.INVISIBLE);
        			break;
            	case 1 : 
        			coeur1.setVisibility(View.INVISIBLE);
        			coeur2.setVisibility(View.INVISIBLE);
        			coeur3.setVisibility(View.VISIBLE);
        			break;
        		case 2 : 
        			coeur1.setVisibility(View.INVISIBLE);
        			coeur2.setVisibility(View.VISIBLE);
        			coeur3.setVisibility(View.VISIBLE);
        			break;
        		case 3 : 
        			coeur1.setVisibility(View.VISIBLE);
        			coeur2.setVisibility(View.VISIBLE);
        			coeur3.setVisibility(View.VISIBLE);
        			break;
        		
        		}
            }
        });
	}

	private void endOfGame() {
		Intent jeu = new Intent(this, CatchGameScoreActivity.class);
        startActivity(jeu);

        Vibrator vib=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(1000);
        
		this.timerFallingFruits.cancel();
		this.timerCreatingFruits.cancel();
		SharedPreferences prefs = this.getSharedPreferences("scores", Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		if (this.score > prefs.getInt("bestscore", 0)) {
			editor.putInt("bestscore", this.score);
		}
		editor.putInt("lastscore", this.score);
		editor.commit();
		this.fruitView.getFallingDownFruitsList().clear();
		
		// Stop the music
		if(this.music) {
			this.player.stop();
		}

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    int touchX = (int) event.getX();
	    int touchY = (int) event.getY();
	    boolean touched = false;

	    int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
            touched = true;
            break;
        default: touched = false;
        }

        if(touched) {
        	 for(Fruit fruit : this.fruitView.getFallingDownFruitsList()){
             	Log.i("DEBUG" , "EVENT : ("+ touchX+ " "+ touchY +") ("+fruit.getLocationInScreen().x + " "+ fruit.getLocationInScreen().y +")");
             	int xRect = fruit.getLocationInScreen().x;
            	int yRect = fruit.getLocationInScreen().y;
            	if(this.getTouchFruitSurface(touchX, touchY, xRect, yRect)) {
            		this.fruitView.getFallingDownFruitsList().remove(fruit);
            		this.score += 20;
            		nbCatch++;
            		runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        	scoreTextView.setText("0000"+score);
                        	basket.setText(""+nbCatch);
                        }
            		});
            	}
             }
        } 
	    return true;
	}

	public boolean getTouchFruitSurface(int touchX, int touchY, int xRect, int yRect){
		int px = this.width*150/1080;
		int py = this.height*150/1980;
		xRect += px; 
		yRect += py;
		float  precision = 1.3F;
		if(         touchX >= xRect - precision*px 
				&& touchX <= xRect + precision*px 
				&& touchY >= yRect - precision*py
				&& touchY <= yRect + precision*py){
			Log.i("INFO","Vous avez cliqué autour du fruit");
			return true;
		}
		return  false;
	}
	public int getWidthFruit(){
		return this.width/10;
	}
	public int getFruitBottom(){
		return this.width/2;
	}


}
