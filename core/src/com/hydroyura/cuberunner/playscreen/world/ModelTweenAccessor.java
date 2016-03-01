package com.hydroyura.cuberunner.playscreen.world;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;


public class ModelTweenAccessor implements TweenAccessor<ModelInstance> {
	
	
	public static final int Z_POS = 1;
	Vector3 position = new Vector3();

	@Override
	public int getValues(ModelInstance entity, int tweenType, float[] oldValues) {
		switch (tweenType) {
		case Z_POS:
			oldValues[0] = entity.transform.getTranslation(position).z;
	        //Gdx.app.log(null, "get" + position);
			return 1;

		default:
			assert false;
            return -1;
		}
	}

	@Override
	public void setValues(ModelInstance entity, int tweenType, float[] newValues) {
		switch (tweenType) {
		case Z_POS:
		    entity.transform.setTranslation(
		    		entity.transform.getTranslation(position).set(position.x, position.y, newValues[0])
		    );
		    //Gdx.app.log(null, "set" + position);
		    entity.calculateTransforms();
		    break;


		default:
			assert false;
            break;
		}
	}

}
