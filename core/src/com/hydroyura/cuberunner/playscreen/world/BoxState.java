package com.hydroyura.cuberunner.playscreen.world;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.hydroyura.cuberunner.utils.Constants;

public enum BoxState implements State<Box>{

	
	
	
	STAY_LEFT() {},
	
	STAY_RIGHT() {},
	
	MOVING_LEFT() {
		@Override
		public void enter(Box box) {
			
			Tween.to(box, ModelTweenAccessor.Z_POS, 0.5f)
				.target(-0.75f)
				.ease(TweenEquations.easeNone)
				
				.setCallback(box.cb)
				.setCallbackTriggers(TweenCallback.COMPLETE)
				
				.start(box.tweenManager);
		}
	},
	
	MOVING_RIGHT() {
		@Override
		public void enter(Box box) {
			Tween.to(box, ModelTweenAccessor.Z_POS, 0.5f)
				.target(0.75f)
				.ease(TweenEquations.easeNone)
				
				.setCallback(box.cb)
				.setCallbackTriggers(TweenCallback.COMPLETE)
				
				.start(box.tweenManager);
		}
	},
	
	COLLISION(){};
	
	@Override
	public void enter(Box entity) {}

	@Override
	public void update(Box entity) {}

	@Override
	public void exit(Box entity) {}

	@Override
	public boolean onMessage(Box entity, Telegram telegram) {
		return false;
	}

}
