package com.hydroyura.cuberunner.playscreen.world;

import com.badlogic.gdx.Gdx;
import com.hydroyura.cuberunner.utils.Constants;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

public class BoxTweenCallBack implements TweenCallback {
	
	Box box;
	
	public BoxTweenCallBack(Box _box) {
		Gdx.app.log("CB", "BoxTweenCallBack()");
		
		box = _box;
	}

	@Override
	public void onEvent(int trigger, BaseTween<?> arg1) {
		Gdx.app.log("CB", "onEvent");
		
		
		switch (trigger) {
		case TweenCallback.COMPLETE:
			Gdx.app.log("CB", "TweenCallback.COMPLETE");
			
			switch (box.fsm.getCurrentState()) {
			case MOVING_LEFT:
				box.fsm.changeState(BoxState.STAY_LEFT);
				break;

			case MOVING_RIGHT:
				box.fsm.changeState(BoxState.STAY_RIGHT);
				break;	
				
			default:
				break;
			}
			
			break;
		default:
			Gdx.app.log("CB", "DEFAULT");
			break;
		}
		
	}

}
