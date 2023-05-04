package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;
import java.util.Objects;

public class ObstacleImpl extends EntityImpl implements Obstacle {

	private ObstacleType type;
	private Vector2D pixelPos;

	public ObstacleImpl(final Vector2D initialPos, final ObstacleType type) {
		super(initialPos, type == ObstacleType.CAR_DX || type == ObstacleType.CAR_SX || type == ObstacleType.TRAIN_DX
				|| type == ObstacleType.TRAIN_SX);
		this.type = type;

	}

	@Override
	public ObstacleType getType() {
		return this.type;
	}

	@Override
	public Vector2D getPixelPosition() {
		Objects.requireNonNull(this.pixelPos);
		return this.pixelPos;
	}

	@Override
	public Vector2D movePixelPosition(final int x, final int y) {
		Vector2D newPixelPos = new Vector2D(x, y);
		this.pixelPos = newPixelPos;
		return this.pixelPos;
	}

}
