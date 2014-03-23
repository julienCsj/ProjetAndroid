package fr.ups.l3info.l3info_catchgameactivity;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.ups.l3info.l3info_catchgamedatastructure.Fruit;
import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
	int fruitFallDelay = 100;
	int fruitCreateDelay = 1000;
	//List<Fruit> fruitList;
	Timer timerFallingFruits;
	Timer timerCreatingFruits;
	
	
	/*
	 * CONSTRUCTEURS
	 */
	public CatchGameView(Context context) {
		super(context);
		fallingDownFruitsList.clear();
	}
	
	public CatchGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		fallingDownFruitsList.clear();
	}
	
	public CatchGameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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
			fruit.getVue().offset(100, 0); // if we want add wind, modify second parameter
			//Log.i("DEBUG", "Fruit = "+fruit);
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
			case APPLE : canvas.drawBitmap(applePict, fruit.getVue().top, fruit.getVue().left,null);
				break;
			case GRAPE : canvas.drawBitmap(grapePict, fruit.getVue().top, fruit.getVue().left,null);
				break;
			case ORANGE : canvas.drawBitmap(orangePict, fruit.getVue().top, fruit.getVue().left,null);
				break;
			case PAPAYA : canvas.drawBitmap(papayaPict, fruit.getVue().top, fruit.getVue().left,null);
				break;
			case PINAPPLE : canvas.drawBitmap(pineapplePict, fruit.getVue().top, fruit.getVue().left,null);
				break;
			case STRAWBERRY : canvas.drawBitmap(strawberryPict, fruit.getVue().top, fruit.getVue().left,null);
				break;
			case WATERMELON : canvas.drawBitmap(watermelonPict, fruit.getVue().top, fruit.getVue().left,null);
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
	public boolean onTouchEvent(MotionEvent event) {
	    int touchX = (int) event.getX();
	    int touchY = (int) event.getY();
        Log.i("DEBUG", "TouchEvent detected !");
        for(Fruit fruit : this.fallingDownFruitsList){
        	if(fruit.getVue().contains(touchY, touchX)) {
        		Log.i("DEBUG" , "TOUCHER !");
        		this.fallingDownFruitsList.remove(fruit);
        	}
        }
        /*int xRect = (int) rect.exactCenterX();
    	int yRect = (int) rect.exactCenterY();
    	if((touchX >= yRect - 250 && touchX <= yRect + 250) 
    	&&( touchY >= xRect - 250 && touchY <= xRect + 250)) {
    		Log.i("DEBUG" , "TOUCHER !");
    		this.fallingDownFruitsList.remove(rect);
    	}*/
	    return true;
	}


	public void setFruitFallDelay(int delay){
		fruitFallDelay = delay;
	}
}
