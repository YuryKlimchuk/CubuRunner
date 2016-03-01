package com.hydroyura.cuberunner.playscreen.world;

import aurelienribon.tweenengine.Tween;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.UBJsonReader;



public class World implements Disposable{

	private Array<Disposable> disposables = new Array<Disposable>();

	
	private TweenManager tweenManager = new TweenManager();

	public Box box;
	
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	

	
	Model obstacleModel;
	
	// array containing the active bullets.
    public final Array<Obstacle> activeObstacles = new Array<Obstacle>();

    // bullet pool.
    public final Pool<Obstacle> poolObstacle = new Pool<Obstacle>(50) {
    	@Override
    	protected Obstacle newObject() {
    		return new Obstacle(obstacleModel);
    	}
    };
	
	
	
	
	
	public World() {
		
		
		
		Tween.registerAccessor(ModelInstance.class, new ModelTweenAccessor());
		
		ModelBuilder mBuilder = new ModelBuilder();
		
		Model boxModel = mBuilder.createBox(
				0.7f, 0.7f, 0.7f, 
				new Material(ColorAttribute.createDiffuse(1f, 1f, 1f, 1f)),
				Usage.Position | Usage.Normal
		);
		disposables.add(boxModel);
		
		obstacleModel = mBuilder.createBox(
				0.9f, 0.9f, 0.9f, 
				new Material(ColorAttribute.createDiffuse(1f, 1f, 1f, 1f)),
				Usage.Position | Usage.Normal
		); 
		disposables.add(obstacleModel);
		
		Model groundModel = mBuilder.createBox(
				40f, 0.2f, 1f, 
				new Material(ColorAttribute.createDiffuse(1f, 1f, 1f, 1f)),
				Usage.Position | Usage.Normal
		); 
		disposables.add(groundModel);
		
		
		box = new Box(boxModel, tweenManager);
		box.transform.trn(0f, 0.45f, 0.75f);
		instances.add(box);

		
		CollisionObject ground1 = new CollisionObject(groundModel);
		ground1.transform.trn(0f, 0f, 0.75f);
		instances.add(ground1);
		
		CollisionObject ground2 = new CollisionObject(groundModel);
		ground2.transform.trn(0f, 0f, -0.75f);
		instances.add(ground2);
		
		
		JsonReader json = new JsonReader();
		G3dModelLoader loader = new G3dModelLoader(json);
        
        Model skyBox = loader.loadModel(Gdx.files.getFileHandle("sky_box.g3dj", FileType.Internal));
        // Now create an instance.  Instance holds the positioning data, etc of an instance of your model
        
		ModelInstance instance1 =  new ModelInstance(skyBox);
		//instance1.transform.scale(100, 100, 100);
		
		instances.add(instance1);
		
		
		
		
		

		
	}
	
	
	public void update(float delta) {
		tweenManager.update(delta);

		
		
		//Gdx.app.log("activeObstacles size = " + activeObstacles.size, "poolObstacle size = " + poolObstacle.getFree());
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	


}
