package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.utility.Vector2D;

public class CollectableImpl extends EntityImpl implements Collectable {

	private CollectableType type;
	
	public CollectableImpl(final Vector2D initialPos, final CollectableType type) {
		super(initialPos, false);
		this.type = type;
	}

	@Override
	public CollectableType getType() {
		return this.type;
	}

}
