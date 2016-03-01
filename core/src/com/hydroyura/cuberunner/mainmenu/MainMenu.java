package com.hydroyura.cuberunner.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.hydroyura.cuberunner.GameManager;
import com.hydroyura.cuberunner.playscreen.PlayScreen;
import com.hydroyura.cuberunner.utils.Constants;

public class MainMenu implements Screen {

	Stage stage;
	Skin skin;
	GameManager gameManager;
	
	@Override
	public void show() {
		Gdx.app.log(this.getClass().toString(), "show()");
		stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		skin = gameManager.resManager.getMainMenuSkin();
		
		
		
		ImageButton btnPlay = new ImageButton(skin, "playBtn");
		btnPlay.setTransform(true);
		btnPlay.setSize(120f, 120f);
		btnPlay.setOrigin(btnPlay.getWidth()/2, btnPlay.getHeight()/2);
		btnPlay.setPosition(340f, 200f);
		btnPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(this.getClass().toString(), "btnPlay.clicked()");
				Actor actor = event.getTarget();
				actor.addAction(Actions.sequence(
						//Actions.scaleTo(1.05f, 1.05f, 0.1f),
						//Actions.scaleTo(1f, 1f, 0.1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								gameManager.setScreen(new PlayScreen());
							}
						})));
			}
		});
		stage.addActor(btnPlay);
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(167 / 255f, 160 / 255f, 171 / 255f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.draw();
        stage.act(delta);
        
        
        stage.getBatch().begin();
    	gameManager.resManager.getFont26().draw(
    			stage.getBatch(),
    			"FPS : " + Gdx.graphics.getFramesPerSecond(),
    			670, 450);
    	stage.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
