package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class DusmanGemisi extends Rectangle{

	public boolean PatladiMi;
	public boolean YokEt;
	private float timePass = 0;
	TextureAtlas KarakterTexture;
	private Animation anim;
	public Texture texture;
	public int HareketKatsayisi;
	
	Random rnd;
	public DusmanGemisi(float EkranGenisligi,float EkranYuksekligi) {
		HareketKatsayisi = 3;
		PatladiMi = false;
		KarakterTexture = new TextureAtlas("patlama.atlas");
		rnd = new Random();
		this.x = rnd.nextInt((int)EkranYuksekligi-65);
		this.y = EkranGenisligi;
		
		this.width = 84;
		this.height = 105;
		
		texture = new Texture("enemyship.png");
		anim = new Animation(1/10f,KarakterTexture.getRegions());
		
	}
	
	public void HareketEt(float Hareket){
		
		this.y-=HareketKatsayisi;
		
	}
	
	public void Ciz(SpriteBatch batch,Dunya dunya) {
		
		if(PatladiMi) {
			HareketKatsayisi = 2;
			timePass += Gdx.graphics.getDeltaTime();
			batch.draw((TextureRegion)anim.getKeyFrame(timePass, false), this.x,this.y,this.width,this.height);
			if(anim.isAnimationFinished(timePass)) {
				dunya.DusmanGemileri.remove(this);
			}
		
		}else {
		
			batch.draw(texture,this.x,this.y,this.width,this.height);
		
		}
		
		
		
		
		
	}

}
