package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.Player;
import it.unibo.project.utility.Vector2D;

public class PlayerImpl extends EntityImpl implements Player {

	public PlayerImpl(Vector2D initialPos) {
		super(initialPos);
	}

	@Override
	public int getMaxDistance() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getMaxDistance'");
	}

}
