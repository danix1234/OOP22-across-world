package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.utility.Vector2D;

public class BackgroundCellImpl extends EntityImpl implements BackgroundCell {

	public BackgroundCellImpl(Vector2D initialPos) {
		super(initialPos);
	}

	@Override
	public BackgroundCellType getType() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getType'");
	}

}
