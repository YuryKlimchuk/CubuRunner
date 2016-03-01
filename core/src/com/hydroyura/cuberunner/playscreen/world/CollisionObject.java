package com.hydroyura.cuberunner.playscreen.world;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.collision.BoundingBox;

public class CollisionObject extends ModelInstance {
	
	private BoundingBox staticBoundingBox = new BoundingBox();
	public BoundingBox bufferBoundingBox = new BoundingBox();

	public CollisionObject(Model model) {
		super(model);
		calculateBoundingBox(staticBoundingBox);
	}
	
	public boolean isCollision(final CollisionObject obj) {
		return bufferBoundingBox.intersects(obj.bufferBoundingBox);
	}
	
	public void translateBoundingBox() {
		bufferBoundingBox.set(staticBoundingBox).mul(transform);
	}

}
