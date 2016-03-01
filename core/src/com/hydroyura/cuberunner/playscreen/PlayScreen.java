package com.hydroyura.cuberunner.playscreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.hydroyura.cuberunner.playscreen.controller.Controller;
import com.hydroyura.cuberunner.playscreen.render.Render;
import com.hydroyura.cuberunner.playscreen.world.World;

public class PlayScreen implements Screen {

	World world;
	Render render;
	Controller controller;
	
	@Override
	public void show() {
		
		world = new World();
		render = new Render(world);
		controller = new Controller(world, render);
	}

	@Override
	public void render(float delta) {
		
		world.update(delta);
		render.render(delta);
		controller.update(delta);
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		world.dispose();
	}

}
