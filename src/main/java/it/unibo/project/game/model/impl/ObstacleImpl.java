package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;
import java.util.Objects;

public class ObstacleImpl extends EntityImpl implements Obstacle {
	private final ObstacleType type;
	private Vector2D pixelPos = new Vector2D(50, 0);
	private final int speed;
	private final int moveLeft;

	public ObstacleImpl(final Vector2D initialPos, final ObstacleType type) {
		super(initialPos, type == ObstacleType.CAR_DX || type == ObstacleType.CAR_SX || type == ObstacleType.TRAIN_DX
				|| type == ObstacleType.TRAIN_SX || type == ObstacleType.TRUNK_SX || type == ObstacleType.TRUNK_DX);
		this.type = type;
		this.moveLeft = (type == ObstacleType.CAR_SX || type== ObstacleType.TRAIN_SX || type == ObstacleType.TRUNK_SX) ? 1 : -1;
		this.speed = this.moveLeft * 1;
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

	@Override
	public int speed() {
		return this.speed;
	}


}
