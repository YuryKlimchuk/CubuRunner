package com.hydroyura.cuberunner.playscreen.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.hydroyura.cuberunner.playscreen.world.Obstacle;

public enum ControllerState implements State<Controller> {
	
	WAIT_TAP() {},
	COUNTDOWN() {
		@Override
		public void enter(final Controller controller) {
			controller.tapLabel.remove();
			
			controller.countdownLabel.setText("3");
			
			controller.stage.addActor(controller.countdownLabel);
			
			controller.countdownLabel.addAction(
					Actions.sequence(
						Actions.delay(1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								controller.countdownLabel.setText("2");
							}
						}),
						Actions.delay(1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								controller.countdownLabel.setText("1");
							}
						}),
						Actions.delay(1f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								controller.fsm.changeState(ACTIVE);
								controller.inputs.addProcessor(controller.stage);
								controller.countdownLabel.remove();
							}
						})
					)
			);
			
		}
	},
	ACTIVE() {
		@Override
		public void enter(Controller controller) {
			if(controller.world.activeObstacles.size == 0) {
				Obstacle obstacle = controller.world.poolObstacle.obtain();
				obstacle.transform.trn(new Vector3(Obstacle.RESP_X_POS, 0.55f, 0.75f));
				obstacle.isMoving = true;
				controller.world.activeObstacles.add(obstacle);
			}
		}

		@Override
		public void update(Controller controller) {
			controller.world.box.translateBoundingBox();
			
			
			controller.incScore();
			Gdx.app.log("score", "" + controller.score);
			
			
			if(controller.world.activeObstacles.size != 0) {
				
				float z = (MathUtils.randomBoolean()) ? 0.75f : -0.75f;
				float x = controller.world.activeObstacles.get(controller.world.activeObstacles.size - 1).getX() + MathUtils.random(5f, 10f);
				
				if(x > (Obstacle.RESP_X_POS - 10) && x < (Obstacle.RESP_X_POS + 10)) {
					Obstacle obstacle = controller.world.poolObstacle.obtain();
					obstacle.transform.setTranslation(x, 0.55f, z);
					obstacle.isMoving = true;
					controller.world.activeObstacles.add(obstacle);
				}
			}
			
			for (int i = 0; i < controller.world.activeObstacles.size; i++) {
				
				Obstacle obstacle = controller.world.activeObstacles.get(i);
				
				if(obstacle.getX() < Obstacle.FREE_X_POS) {
					controller.world.activeObstacles.removeIndex(i);
					controller.world.poolObstacle.free(obstacle);
					obstacle.isMoving = false;
					//Gdx.app.log(null, "remove");
					//Gdx.app.log("activeObstacles size = " + controller.world.activeObstacles.size, "poolObstacle size = " + controller.world.poolObstacle.getFree());
				} else {
				obstacle.moving();
				}
				
				obstacle.translateBoundingBox();
				
				if(controller.world.box.isCollision(obstacle)) {
					Gdx.app.log("collide","");
					controller.fsm.changeState(PAUSE);
				}
			}
		}
	},
	
	PAUSE() {
		@Override
		public void update(Controller controller) {
			
		}
	},
	
	LOSS() {};
	
	@Override
	public void enter(Controller controller) {
		
	}

	@Override
	public void update(Controller controller) {
		
	}
	

	@Override
	public void exit(Controller controller) {
		
	}

	@Override
	public boolean onMessage(Controller controller, Telegram telegram) {
		return false;
	}

}
