package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Entity;
import it.unibo.project.utility.Vector2D;

public class EntityImpl implements Entity {

	private Vector2D entityPos;
	private final boolean movable;

	/**
	 * Constructor of class Entity.
	 */
	public EntityImpl(final Vector2D initialPos, final boolean movable) {
		this.entityPos = initialPos;
		this.movable = movable;
	}

	@Override
	public Vector2D getPosition() {
		return this.entityPos;
	}

	@Override
	public boolean isMovable() {
		return this.movable;
	}

	@Override
	public void move(final int x, final int y) {
		if (movable) {
			final Vector2D newPos = new Vector2D(x, y);
			this.entityPos = newPos;
		}
	}

}
