package com.hydroyura.cuberunner.playscreen.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Obstacle extends CollisionObject implements Poolable{

	public static final int RESP_X_POS = 35;
	public static final int FREE_X_POS = -10;
	public static final float OBSTACLE_SPEED = -10;
	
	public boolean isMoving;
	
	public Obstacle(Model model) {
		super(model);
	}

	@Override
	public void reset() {
		Gdx.app.log(this.getClass().toString(), "reset()");
		transform.setTranslation(10, 10, 30);
	}
	
	public void draw() {
		
	}
	
	public void moving() {
		transform.translate(OBSTACLE_SPEED * Gdx.graphics.getDeltaTime(), 0f ,0f);
	}
	
	public float getX() {
		return transform.getTranslation(new Vector3()).x;
	}
	
	

}
