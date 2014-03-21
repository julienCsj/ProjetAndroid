package fr.ups.l3info.l3info_catchgameactivity;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
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

	List<Rect> fallingDownFruitsList = new CopyOnWriteArrayList<Rect>();
	Bitmap applePict = BitmapFactory.decodeResource(getResources(),R.drawable.apple);
	Bitmap applePict2 = BitmapFactory.decodeResource(getResources(),R.drawable.apple);
	int fruitFallDelay = 100;
	int fruitCreateDelay = 1000;
	List<Fruit> fruitList;
	Timer timerFallingFruits;
	Timer timerCreatingFruits;
	
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
	
	private void timerFallingFruitEventHandler(){
		for(Rect fruit : fallingDownFruitsList) {
			fruit.offset(100, 0); // if we want add wind, modify second parameter
			//Log.i("DEBUG", "Fruit = "+fruit);
		}
		this.postInvalidate();
	}
	
	// Faire apparaitre un fruit
	private void timerCreateFruitEventHandler(){
		//this.fallingDownFruitsList.add(new Rect(0, 200, 50, 50));
		
	}
	
	public void setFruitFallDelay(int delay){
		fruitFallDelay = delay;
	}
	
	public void setFruitList(List<Fruit> fruitList){
		this.fruitList = fruitList;
		Rect fruitBounds;
		fallingDownFruitsList.clear();
		for (Fruit fruit:fruitList){
			fruitBounds = new Rect(fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, 2*(fruit.getRadius()), 2*(fruit.getRadius()));
			fallingDownFruitsList.add(fruitBounds);
		}
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(color.holo_green_dark);
		
		for (Rect fruitBounds:fallingDownFruitsList){
			canvas.drawBitmap(applePict, fruitBounds.top, fruitBounds.left,null);
		}
		
	}

	public void addFruit(Fruit fruit) {
		Rect fruitBounds = new Rect(fruit.getLocationInScreen().x, fruit.getLocationInScreen().y, 2*(fruit.getRadius()), 2*(fruit.getRadius()));
		fallingDownFruitsList.add(fruitBounds);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
	    int touchX = (int) event.getX();
	    int touchY = (int) event.getY();
        Log.i("DEBUG", "TouchEvent detected !");
        for(Rect rect : this.fallingDownFruitsList){
        	int xRect = (int) rect.exactCenterX();
        	int yRect = (int) rect.exactCenterY();
        	if((touchX >= yRect - 250 && touchX <= yRect + 250) 
        	&&( touchY >= xRect - 250 && touchY <= xRect + 250)) {
        		Log.i("DEBUG" , "TOUCHER !");
        		this.fallingDownFruitsList.remove(rect);
        	}
        	/*if(rect.contains(touchY, touchX)) {
        		Log.i("DEBUG" , "TOUCHER !");
        		this.fallingDownFruitsList.remove(rect);
        	}*/
        }
	    
	    return true;
	}

}
