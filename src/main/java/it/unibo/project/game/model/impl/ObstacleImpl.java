package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.utility.Vector2D;
import java.util.Objects;

public class ObstacleImpl extends EntityImpl implements Obstacle {
	private final ObstacleType type;
	private Vector2D pixelPos = new Vector2D(50, 0);
	private final int speed;

	public ObstacleImpl(final Vector2D initialPos, final ObstacleType type) {
		super(initialPos, type.isMoveable());
		this.type = type;
		this.speed = type.getSpeed();
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
	public void movePixelPosition(final int x, final int y) {
		this.pixelPos = new Vector2D(x, y);
	}

	@Override
	public int speed() {
		return this.speed;
	}


}
