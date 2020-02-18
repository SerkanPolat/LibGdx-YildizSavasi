package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Mermi extends Rectangle{
	
	TextureAtlas KarakterTexture;
	private Animation anim;
	private float timePass = 0;
	public Texture texture;
	public Rectangle EtkiAlani;
	public Mermi(float KordiX,float KordiY) {
		KarakterTexture = new TextureAtlas("ates.atlas");
		this.x = KordiX;
		this.y = KordiY;
		this.width = 15;
		this.height = 50;
		texture = new Texture("badlogic.jpg");
		anim = new Animation(1/9f,KarakterTexture.getRegions());
	}
	
	public boolean CarpismaKontrolu() {
		return false;
	}
	
	public void Ciz(SpriteBatch batch) {
		

		
		timePass += Gdx.graphics.getDeltaTime();
		
		batch.draw((TextureRegion)anim.getKeyFrame(timePass, false), this.x,this.y,this.width,this.height);
		
		//batch.draw(texture,this.x,this.y,this.width,this.height);
	}
	
	public void HareketEt() {
		this.y+=4;
	}
}
