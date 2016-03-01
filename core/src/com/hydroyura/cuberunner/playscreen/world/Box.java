package com.hydroyura.cuberunner.playscreen.world;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.g3d.Model;


public class Box extends CollisionObject {
	
	public DefaultStateMachine<Box, BoxState> fsm = new DefaultStateMachine<Box, BoxState>(this, BoxState.STAY_LEFT);

	TweenManager tweenManager;
	BoxTweenCallBack cb;
	
	public Box(Model model, TweenManager _tweenManager) {
		super(model);
		// TODO Auto-generated constructor stub
		
		tweenManager = _tweenManager;
		
		cb = new BoxTweenCallBack(this);
		
	}
}
