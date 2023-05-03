package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;

public class ObstacleImpl extends EntityImpl implements Obstacle {

	public ObstacleImpl(final Vector2D initialPos, final ObstacleType type) {
		super(initialPos, false);	//false temporaneo
	}

	@Override
	public ObstacleType getType() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getType'");
	}

	@Override
	public Vector2D getPixelPosition() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPixelPosition'");
	}

	@Override
	public Vector2D movePixelPosition(final int x, final int y) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'movePixelPosition'");
	}

}
