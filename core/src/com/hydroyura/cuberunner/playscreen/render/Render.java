package com.hydroyura.cuberunner.playscreen.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.hydroyura.cuberunner.playscreen.world.World;

public class Render implements Disposable {
	
	private World world;
	public ModelBatch modelBatch;
	public Environment environment;
	public PerspectiveCamera camera;
	
	public Render(World _world) {
		world = _world;
		initCamera();
		initEnvironment();
		initModelBatch();
	}
	
	
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(0.3f, 0.13f, 0.991f, 1f);

        

        modelBatch.begin(camera);
        
        for (ModelInstance instance : world.instances) {
        	modelBatch.render(instance, environment);
		}
        
        for (ModelInstance instance : world.activeObstacles) {
        	modelBatch.render(instance, environment);
		}
        
        modelBatch.end();
        
        //Gdx.app.log("camera", "" + camera.position);
	}
	
	

	
	private void initCamera() {
		camera = new PerspectiveCamera(67f, 800, 480);
		camera.position.set(-1f, 5f, 8f);
		camera.lookAt(0f, 0f, 0f);
		camera.far = 4500f;
        camera.near = 0.1f;
        camera.up.set(Vector3.Y);
        camera.update(); 
	}
	
	private void initModelBatch() {
		modelBatch = new ModelBatch();
	}
	
	private void initEnvironment() {
		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));
        environment.add(new DirectionalLight().set(0.6f, 0.6f, 0.6f, 0.5f, -0.8f, -0.2f));
        environment.add(new PointLight().set(1f, 1f, 1f, new Vector3(-16, -16, -16), 150f));
        environment.add(new PointLight().set(1f, 1f, 1f, new Vector3(6, 6, 6), 150f));
        
        environment.set(new ColorAttribute(ColorAttribute.Fog, 0.13f, 0.13f, 0.13f, 1f));
	}
	
	@Override
	public void dispose() {}

}
