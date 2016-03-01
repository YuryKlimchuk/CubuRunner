package com.hydroyura.cuberunner.playscreen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.hydroyura.cuberunner.GameManager;
import com.hydroyura.cuberunner.playscreen.render.Render;
import com.hydroyura.cuberunner.playscreen.world.Obstacle;
import com.hydroyura.cuberunner.playscreen.world.World;
import com.hydroyura.cuberunner.utils.Constants;

public class Controller {
	
	World world;
	Render render;
	InputMultiplexer inputs = new InputMultiplexer();
	CameraInputController camController;
	GameManager gameManager;
	public Stage stage;
	float score = 0;
	
	public Label tapLabel, countdownLabel;
	
	DefaultStateMachine<Controller, ControllerState> fsm = new DefaultStateMachine<Controller, ControllerState>(this, ControllerState.WAIT_TAP);
	
	public Controller(World _world, Render _render) {
		
		world = _world;
		render = _render;
		
		stage = new Stage(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
		gameManager = (GameManager) Gdx.app.getApplicationListener();
		
		camController = new CameraInputController(render.camera);
		TapController tapController = new TapController(world.box, this);
		
		inputs.addProcessor(tapController);
		inputs.addProcessor(camController);
		
		
		Gdx.input.setInputProcessor(inputs);
		
		fsm.changeState(ControllerState.WAIT_TAP);
		
		
		
		initStage();
		
	}
	
	private void initStage() {
		
		BitmapFont font = gameManager.resManager.getFont26();
		LabelStyle lStyle = new LabelStyle(font, Color.GOLD);
		
		tapLabel = new Label("Tap to Start", lStyle);
		tapLabel.setDebug(true);
		tapLabel.setSize(200, 200);
		tapLabel.setPosition(300, 140);
		tapLabel.setAlignment(Align.center);
		stage.addActor(tapLabel);
		
		countdownLabel = new Label("", lStyle);
		countdownLabel.setDebug(true);
		countdownLabel.setSize(200, 200);
		countdownLabel.setPosition(300, 140);
		countdownLabel.setAlignment(Align.center);
		countdownLabel.setOrigin(countdownLabel.getWidth()/2, countdownLabel.getHeight()/2);
		
	}
	
	
	public void update(float delta) {
		fsm.update();
		stage.act(delta);
		stage.draw();
	}
	
	public void incScore() {
		score -= Obstacle.OBSTACLE_SPEED * Gdx.graphics.getDeltaTime();
	}

}
