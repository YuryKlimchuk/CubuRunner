package com.hydroyura.cuberunner.playscreen.controller;

import com.badlogic.gdx.InputProcessor;
import com.hydroyura.cuberunner.playscreen.world.Box;
import com.hydroyura.cuberunner.playscreen.world.BoxState;

public class TapController implements InputProcessor {

	Box box;
	Controller controller;
	
	public TapController(Box _box, Controller _controller) {
		box = _box;
		controller = _controller;
	}
	
	

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		switch (controller.fsm.getCurrentState()) {
		case WAIT_TAP:
			controller.fsm.changeState(ControllerState.COUNTDOWN);
			break;
			
		case ACTIVE:
			
			switch (box.fsm.getCurrentState()) {
			case STAY_LEFT:
				box.fsm.changeState(BoxState.MOVING_RIGHT);
				break;
				
			case STAY_RIGHT:
				box.fsm.changeState(BoxState.MOVING_LEFT);
				break;

			default:
				break;
			}
			
			break;

		default:
			break;
		}
		
		
		
		
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	
	

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	
	/*_____________________________*/
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

}
