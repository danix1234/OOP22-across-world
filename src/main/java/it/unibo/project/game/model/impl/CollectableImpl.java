package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.utility.Vector2D;

public class CollectableImpl extends EntityImpl implements Collectable {

	public CollectableImpl(final Vector2D initialPos, final CollectableType type) {
		super(initialPos);
	}

	@Override
	public CollectableType getType() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getType'");
	}

}
