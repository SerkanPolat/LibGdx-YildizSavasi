package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dunya {
	
	public Gemi gemi;
	public ArrayList<Mermi> Mermiler;
	public ArrayList<DusmanGemisi> DusmanGemileri;
	public ArrayList<Engel> Engeller;
	float EkranGenisligi;
	float EkranYuksekligi;
	public long SonEngelOlusturmaZamani;
	public long SonDusmanOlusturmaZamani;
	public Texture ArkaPlan1,ArkaPlan2,ArkaPlan3;
	public long ArkaPlanKonum;
	int ArkaPlanKatsayi;
	
	public Dunya(float EkranGenisligi,float EkranYuksekligi) {
		ArkaPlanKatsayi = 0;
		ArkaPlan1 = new Texture("arkaplan.png");
		ArkaPlan2 = new Texture("arkaplan.png");
		ArkaPlan3 = new Texture("arkaplan.png");
		ArkaPlanKonum = 0;
		SonEngelOlusturmaZamani = 0;
		SonDusmanOlusturmaZamani = 0;
		gemi = new Gemi(EkranGenisligi/2-50,100);
		Mermiler = new ArrayList<>();
		DusmanGemileri = new ArrayList<>();
		Engeller = new ArrayList<>();
		
		this.EkranGenisligi = EkranGenisligi;
		this.EkranYuksekligi = EkranYuksekligi;
		
	}
	Mermi iter;
	public void AtesKontrolcusu() {
		
		Mermiler.add(new Mermi(gemi.x+gemi.width/2-9,gemi.y+gemi.height/2-40));
		
		for(int i = 0;i<Mermiler.size();i++) {
			iter = Mermiler.get(i);
			
			if(iter.y>EkranYuksekligi||iter.y<0) {
				
				Mermiler.remove(i);
				i--;
			}
		}
	}
	
	public void HareketKontrolcusu(float Hareket) {
		gemi.HareketEt(Hareket);
		for(int i = 0;i<Mermiler.size();i++) {
			
			Mermiler.get(i).HareketEt();
			
		}for(int i = 0;i<Engeller.size();i++) {
			
			Engeller.get(i).HareketEt(Hareket);
			
		}
		for(int i = 0;i<DusmanGemileri.size();i++) {
			
			DusmanGemileri.get(i).HareketEt(Hareket);
		}
		ArkaPlanHareketKontrolu();
	}
	private void ArkaPlanHareketKontrolu() {
		
		ArkaPlanKonum-=2;
		if(ArkaPlanKonum%1000==0) {
			ArkaPlanKatsayi++;
			System.out.println("Katsayi Arttiriliyor.  "+ArkaPlanKatsayi);
		}
	}

	public void DunyaCizimKontrolcusu(SpriteBatch batch) {

		System.out.println((ArkaPlanKatsayi+1)*EkranYuksekligi);
		System.out.println("Karakter Konumu : "+ArkaPlanKonum);
		batch.draw(ArkaPlan1,0,ArkaPlanKonum+(ArkaPlanKatsayi*EkranYuksekligi),EkranGenisligi,EkranYuksekligi);
		batch.draw(ArkaPlan2,0,ArkaPlanKonum+((ArkaPlanKatsayi+1)*EkranYuksekligi),EkranGenisligi,EkranYuksekligi);
		batch.draw(ArkaPlan3,0,ArkaPlanKonum+((ArkaPlanKatsayi+2)*EkranYuksekligi),EkranGenisligi,EkranYuksekligi);
		
		for(int i = 0;i<Mermiler.size();i++) {
			
			Mermiler.get(i).Ciz(batch);
			
		}
		for(int i = 0;i<Engeller.size();i++) {
			
			Engeller.get(i).Ciz(batch);
			
		}
		for(int i = 0;i<DusmanGemileri.size();i++) {
			
			DusmanGemileri.get(i).Ciz(batch,this);
			
		}
		gemi.Ciz(batch);
		
	}
	
	public void CarpismaKontrolcusu() {
		
		for(DusmanGemisi dusman : DusmanGemileri) {
			
			for(Mermi mermi : Mermiler) {
				
				if(mermi.overlaps(dusman)) {
					
					dusman.PatladiMi = true;
					Mermiler.remove(mermi);
					MyGdxGame.OyunSkor += 15;
					break;
					
				}
			}
		}
		
		for(DusmanGemisi dusman : DusmanGemileri) {
			
			if(gemi.overlaps(dusman)&&(!dusman.PatladiMi)) {
				
				OyunBitti();
				return;
			}
		}
		for(Engel engel : Engeller) {
			
			if(gemi.overlaps(engel)) {
				
				OyunBitti();
				return;
			}
			
		}
	}
	
	private void OyunBitti() {
		
		Engeller.clear();
		DusmanGemileri.clear();
		Mermiler.clear();
		
		
	}

	public void CisimKontrolcusu(long SystemTime) {
		EngelOlusturma(SystemTime,1000000000);
		DusmanOlusturma(SystemTime,2000000000);
	}
	
	public void EngelOlusturma(long SystemTime,long OlusturmaZamani) {
		if(SystemTime - SonEngelOlusturmaZamani>OlusturmaZamani) {
			
			Engeller.add(new Engel(EkranYuksekligi,EkranGenisligi));
			SonEngelOlusturmaZamani = SystemTime;
			
		}
	}
	
	
	public void DusmanOlusturma(long SystemTime,long OlusturmaZamani) {
		
		if(SystemTime - SonDusmanOlusturmaZamani>OlusturmaZamani) {
			
			DusmanGemileri.add(new DusmanGemisi(EkranYuksekligi,EkranGenisligi));
			SonDusmanOlusturmaZamani = SystemTime;
			
		}
	}
}
