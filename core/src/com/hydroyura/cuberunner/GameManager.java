package com.hydroyura.cuberunner;

import com.badlogic.gdx.Game;
import com.hydroyura.cuberunner.mainmenu.MainMenu;
import com.hydroyura.cuberunner.utils.ResourcesManager;
import com.hydroyura.cuberunner.utils.ScreenManager;
import com.hydroyura.cuberunner.utils.SplashScreen;

public class GameManager extends Game {
	
	
	public ResourcesManager resManager;
	public ScreenManager screenManager;

	@Override
	public void create () {
		
		resManager = new ResourcesManager();
		
		
		setScreen(new SplashScreen());
		//setScreen(new MainMenu());
	}

}
