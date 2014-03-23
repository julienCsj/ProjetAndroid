package fr.ups.l3info.l3info_catchgamedatastructure;

import java.util.Random;

public enum EnumFruit {APPLE, GRAPE, ORANGE, PAPAYA, PINAPPLE, STRAWBERRY, WATERMELON;

public static EnumFruit getRandomValue() {
	int nbItems = EnumFruit.values().length;
	Random r = new Random();
	return EnumFruit.values()[r.nextInt(nbItems)];
}}
