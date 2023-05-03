package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Entity;
import it.unibo.project.utility.Vector2D;

public class EntityImpl implements Entity {

	Vector2D entityPos;
	boolean movable;

	/**
     * Constructor of class Entity
     */
	public EntityImpl(final Vector2D initialPos, boolean movable) {
		this.entityPos = initialPos;
		this.movable = movable;
	}

	@Override
	public Vector2D getPosition() {
		return entityPos;
	}

	@Override
	public boolean isMovable() {
		return movable;
	}

	@Override
	public void move(final int x, final int y) {
		if(movable){
			Vector2D newPos = new Vector2D(x, y); 
			entityPos = newPos;
		} 
	}

}
