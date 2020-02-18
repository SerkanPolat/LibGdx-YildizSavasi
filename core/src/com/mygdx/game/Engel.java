package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Engel extends Rectangle{


	public Texture texture;
	Random rnd;
	public Engel(float EkranGenisligi,float EkranYuksekligi) {
		rnd = new Random();
		this.x = rnd.nextInt((int)EkranYuksekligi);
		this.y = EkranGenisligi;
		
		this.width = 30;
		this.height = 30;
		
		texture = new Texture("tas.png");
	
	}
	

	public void HareketEt(float Hareket){
		
		this.y-=2;
		
	}
	
	public void Ciz(SpriteBatch batch) {
		batch.draw(texture,this.x,this.y,this.width,this.height);
	}
}
