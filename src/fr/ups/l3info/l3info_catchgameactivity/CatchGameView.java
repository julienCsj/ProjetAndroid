package fr.ups.l3info.l3info_catchgameactivity;

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
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import fr.ups.l3info.l3info_catchgametemplate.R;

/* 
 * Custom view for displaying falling fruits
 * Comments to be added
 * To be modified to implement your own version of the game
 */
public class CatchGameView extends View {
	private Bitmap applePict = BitmapFactory.decodeResource(getResources(),R.drawable.apple);
	private Bitmap grapePict = BitmapFactory.decodeResource(getResources(),R.drawable.grape);
	private Bitmap orangePict = BitmapFactory.decodeResource(getResources(),R.drawable.orange);
	private Bitmap papayaPict = BitmapFactory.decodeResource(getResources(),R.drawable.papaya);
	private Bitmap pineapplePict = BitmapFactory.decodeResource(getResources(),R.drawable.pineapple);
	private Bitmap strawberryPict = BitmapFactory.decodeResource(getResources(),R.drawable.strawberry);
	private Bitmap watermelonPict = BitmapFactory.decodeResource(getResources(),R.drawable.watermelon);
	private List<Fruit> fallingDownFruitsList = new CopyOnWriteArrayList<Fruit>();
	
	private int width;
	private int height;
	
	/*
	 * CONSTRUCTEURS
	 */
	public CatchGameView(Context context) {
		super(context);
		this.initScreenDimension();
	}
	
	public CatchGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initScreenDimension();
	}
	
	public CatchGameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.initScreenDimension();
	public void initScreenDimension(){
		this.width = this.getWidth();
		this.height = this.getHeight();
	}
			
			if(fruit.getLocationInScreen().y > this.getFruitBottom()) {
	}
	
	
	
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
	
	
	
	public List<Fruit> getFallingDownFruitsList() {
		return fallingDownFruitsList;
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
            	if(this.getTouchFruitSurface(touchX, touchY, xRect, yRect)) {
            		this.fallingDownFruitsList.remove(fruit);
            	}
             }
        } 
	    return true;
	}
	}
	
	public boolean getTouchFruitSurface(int touchX, int touchY, int xRect, int yRect){
		int px = this.width*200/1080;
		return (touchX >= xRect - px && touchX <= xRect + px) 
    	&&( touchY >= yRect - px && touchY <= yRect + px);
	}
	
	public int getFruitBottom(){
		return this.width/2;
}
