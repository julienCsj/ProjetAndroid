package fr.ups.l3info.l3info_catchgameactivity;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.ups.l3info.l3info_catchgamedatastructure.Fruit;
import android.R.color;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import fr.ups.l3info.l3info_catchgametemplate.R;

/* 
 * Custom view for displaying falling fruits
 * Comments to be added
 * To be modified to implement your own version of the game
 */
public class CatchGameView extends View {

	List<Fruit> fallingDownFruitsList = new CopyOnWriteArrayList<Fruit>();
	Bitmap applePict = BitmapFactory.decodeResource(getResources(),R.drawable.apple);
	Bitmap grapePict = BitmapFactory.decodeResource(getResources(),R.drawable.grape);
	Bitmap orangePict = BitmapFactory.decodeResource(getResources(),R.drawable.orange);
	Bitmap papayaPict = BitmapFactory.decodeResource(getResources(),R.drawable.papaya);
	Bitmap pineapplePict = BitmapFactory.decodeResource(getResources(),R.drawable.pineapple);
	Bitmap strawberryPict = BitmapFactory.decodeResource(getResources(),R.drawable.strawberry);
	Bitmap watermelonPict = BitmapFactory.decodeResource(getResources(),R.drawable.watermelon);
	int fruitFallDelay = 2;
	int fruitCreateDelay = 1000;
	private TextView affScore;
	private TextView affNbCatch;
	private int score = 0;
	private int nbCatch = 0;
	private int life = 3;
	//List<Fruit> fruitList;
	Timer timerFallingFruits;
	Timer timerCreatingFruits;
	
	
	public void setComponents() {
		affScore = (TextView)findViewById(R.id.score);
		score = 0;
		nbCatch = 0;
	}
	
	/*
	 * CONSTRUCTEURS
	 */
	public CatchGameView(Context context) {
		super(context);
		affScore = (TextView)findViewById(R.id.score);
		fallingDownFruitsList.clear();
	}
	
	public CatchGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		affScore = (TextView)findViewById(R.id.score);
		fallingDownFruitsList.clear();
	}
	
	public CatchGameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		affScore = (TextView)findViewById(R.id.score);
		fallingDownFruitsList.clear();
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
		for(Fruit fruit : fallingDownFruitsList) {
			//fruit.getVue().offset(100, 0); // if we want add wind, modify second parameter
			fruit.setLocationInScreen(new Point(fruit.getLocationInScreen().x, fruit.getLocationInScreen().y + 2));
			if(fruit.getLocationInScreen().y > 1920) {
				this.setComponents();
				this.fallingDownFruitsList.remove(fruit);
				//this.affScore.setText(""+score);
			}
			//Log.i("DEBUG", "Fruit = "+fruit);
			if (life == 0) {
				// Structure to save scores.
				SharedPreferences prefs = this.getSharedPreferences("scores", Context.MODE_PRIVATE);
				int size = prefs.getAll().size();
				Editor editor = prefs.edit();
				editor.putInt(size, this.score);
				editor.commit();
			}
		}
		this.postInvalidate();
	}
	
	// Faire apparaitre un fruit
	private void timerCreateFruitEventHandler(){
		//this.fallingDownFruitsList.add(new Rect(0, 200, 50, 50));
		
	}
	
	
	/*public void setFruitList(List<Fruit> fruitList){
		this.fruitList = fruitList;
		Rect fruitBounds;
		fallingDownFruitsList.clear();
		for (Fruit fruit:fruitList){
			fruitBounds = new Rect(fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, 2*(fruit.getRadius()), 2*(fruit.getRadius()));
			fallingDownFruitsList.add(fruitBounds);
		}
	}*/
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(color.holo_green_dark);
		Random r = new Random();
		
		for (Fruit fruit:fallingDownFruitsList) {
			switch(fruit.getType()) {
			case APPLE : canvas.drawBitmap(applePict, fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, null);
				break;
			case GRAPE : canvas.drawBitmap(grapePict, fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, null);
				break;
			case ORANGE : canvas.drawBitmap(orangePict, fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, null);
				break;
			case PAPAYA : canvas.drawBitmap(papayaPict, fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, null);
				break;
			case PINAPPLE : canvas.drawBitmap(pineapplePict, fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, null);
				break;
			case STRAWBERRY : canvas.drawBitmap(strawberryPict, fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, null);
				break;
			case WATERMELON : canvas.drawBitmap(watermelonPict, fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, null);
				break;
			}
		}
		
	}

	public void addFruit(Fruit fruit) {
		//Rect fruitBounds = new Rect(fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, 2*(fruit.getRadius()), 2*(fruit.getRadius()));
		fallingDownFruitsList.add(fruit);
	}
	
	/* 
	 * Handle touch event on a fruit
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
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
        	 for(Fruit fruit : this.fallingDownFruitsList){
             	Log.i("DEBUG" , "EVENT : ("+ touchX+ " "+ touchY +") ("+fruit.getLocationInScreen().x + " "+ fruit.getLocationInScreen().y +")");
             	int xRect = fruit.getLocationInScreen().x;
            	int yRect = fruit.getLocationInScreen().y;
            	if((touchX >= xRect - 200 && touchX <= xRect + 200) 
            	&&( touchY >= yRect - 200 && touchY <= yRect + 200)) {
            		Log.i("DEBUG" , "TOUCHER ! + "+this.affScore + " "+score);
            		this.fallingDownFruitsList.remove(fruit);
            		this.setComponents();
            		this.score += 10;
            		this.nbCatch += 1;
            		//this.affScore.setText(""+score);
            		//this.affNbCatch.setText(""+nbCatch);
            	}
             }
        }
       
        
        
        
        
        
	    return true;
	}


	public void setFruitFallDelay(int delay){
		fruitFallDelay = delay;
	}
}
