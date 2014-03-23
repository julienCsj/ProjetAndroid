package fr.ups.l3info.l3info_catchgamedatastructure;

import android.graphics.Point;
import android.graphics.Rect;


public class Fruit {

	private Point locationInScreen; //Where the fruit is located
	private int radius; // How big is the fruit
	private EnumFruit type;
	private Rect vue;
	
	public Fruit(Point location, int rad, EnumFruit type){
		this.locationInScreen = location;
		this.radius = rad;
		/*this.type = type;
		this.vue = vue;*/
		
		this.type = EnumFruit.getRandomValue();
		this.vue = new Rect(this.getLocationInScreen().x, this.getLocationInScreen().y, 2*(this.getRadius()), 2*(this.getRadius()));
	
	}
	
	public void setLocation(Point p){
		locationInScreen = p;
	}
	
	public Point getLocationInScreen() {
		return locationInScreen;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}

	public EnumFruit getType() {
		return type;
	}

	public void setType(EnumFruit type) {
		this.type = type;
	}

	public void setLocationInScreen(Point locationInScreen) {
		this.locationInScreen = locationInScreen;
	}

	public Rect getVue() {
		// TODO Auto-generated method stub
		return this.vue;
	}
	
	
}

