package com.hydroyura.cuberunner.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;


public class ResourcesManager implements Disposable{
	private AssetManager aManager = new AssetManager();
	
	Skin setLevelMenuSkin;
	
	
	public ResourcesManager() {
		startLoad();
	}
	
	private void startLoad() {
		
		aManager.load("skins/main_menu/main_menu.json", Skin.class);
		
		FileHandleResolver resolver = new InternalFileHandleResolver();
		aManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		aManager.setLoader(BitmapFont.class, ".TTF", new FreetypeFontLoader(resolver));
		FreeTypeFontLoaderParameter size1Params = new FreeTypeFontLoaderParameter();
		size1Params.fontFileName = "fonts/GOTHIC.TTF";
		size1Params.fontParameters.size = 56;
		aManager.load("font26.TTF", BitmapFont.class, size1Params);
		
	}
	

	public Skin getMainMenuSkin(){
		return aManager.get("skins/main_menu/main_menu.json", Skin.class);
	}
	
	// ----- fonts -------
	public BitmapFont getFont26() {
			return aManager.get("font26.TTF", BitmapFont.class);
	}
	
	
	
	
	public boolean finishLoad() {
        return aManager.update();
    }

	
	public void initResources() {}
	
	
	@Override
	public void dispose() {
		aManager.dispose();
	}
}
