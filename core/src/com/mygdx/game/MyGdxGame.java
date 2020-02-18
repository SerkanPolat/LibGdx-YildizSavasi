package com.mygdx.game;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	int ZiplaY;
	int KordiY;
	Texture ZiplamaAlani;
	OrthographicCamera kamera;
	Viewport viewport;
	int viewPortX,viewPortY;
	int YerCekimiIvmesi;
	Texture ArkaPlan,img;
	ShapeRenderer shaperenderer;
	int EkranGenisligi;
	int EkranYuksekligi;
	
	boolean OYUN_CALISIYOR = true;
	boolean OYUN_DURDURULDU = false;
	boolean OyunState;
	Dunya dunya;
	BitmapFont font;
	static int OyunSkor;
	@Override
	public void create () {
		EkranGenisligi = 3*200;
		EkranYuksekligi = 5*200;
		OyunState = OYUN_CALISIYOR;
		kamera = new OrthographicCamera();
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		viewport = new FitViewport(EkranGenisligi,EkranYuksekligi);
		viewport.setCamera(kamera);
		viewport.apply();
		kamera.position.set(EkranGenisligi/2,EkranYuksekligi/2 , 0);
		dunya = new Dunya(EkranGenisligi,EkranYuksekligi);
        font = new BitmapFont();
		font.getData().setScale(1.5f);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(kamera.combined);
		kamera.update();
		batch.begin();
		
		OyunGrafikleriGuncelle();
		OyunMekanigiGuncelle();
		
		
		batch.end();

		
		
		
	}
	
	private void OyunMekanigiGuncelle() {
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
		     
		     dunya.HareketKontrolcusu(-2.5f);
		}else if(Gdx.input.isKeyPressed(Keys.D)) {
		     
		     dunya.HareketKontrolcusu(2.5f);
		}else {

		     dunya.HareketKontrolcusu(0);
		}
		
		dunya.CisimKontrolcusu(System.nanoTime());
		dunya.CarpismaKontrolcusu();
		
	}

	private void OyunGrafikleriGuncelle() {
		
		dunya.DunyaCizimKontrolcusu(batch);
		font.draw(batch,"Oyun Skor: "+OyunSkor,kamera.position.x+(kamera.viewportWidth/4),kamera.viewportHeight-25);
		
		
	}

	@Override
	public void dispose () {
		batch.dispose();

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		dunya.AtesKontrolcusu();
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
