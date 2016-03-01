package com.hydroyura.cuberunner.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.hydroyura.cuberunner.playscreen.PlayScreen;




public class ScreenManager implements Disposable{


	Game game;
	
	public ScreenManager(Game _game) {
		game = _game;
	}
	
	// ���������� ����� ��� ���������� ����
	public void setSplashScreen() {
		game.setScreen(new SplashScreen());
	}
	
	public void setPlayScreen(int _boxCount, int _level) {
		PlayScreen playScreen = new PlayScreen();
		game.setScreen((Screen) playScreen);
	}
	

	
	

	@Override
	public void dispose() {}
	
}
