package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Gemi extends Rectangle{
	
	public Texture texture;
	public Gemi(float KordiX,float KordiY) {
		this.x = KordiX;
		this.y = KordiY;
		this.width = 80;
		this.height = 80;
		texture = new Texture("ship.png");
		
	}
	
	
	public void HareketEt(float Hareket) {
		
		this.x += Hareket;
		
	}
	
	public void Ciz(SpriteBatch batch) {
		batch.draw(texture,this.x,this.y,this.width,this.height);
	}
	
	
	
}
