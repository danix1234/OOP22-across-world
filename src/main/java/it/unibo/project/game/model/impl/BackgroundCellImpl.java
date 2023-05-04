package it.unibo.project.game.model.impl;

import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.utility.Vector2D;

public class BackgroundCellImpl extends EntityImpl implements BackgroundCell {

	BackgroundCellType type;

	public BackgroundCellImpl(final Vector2D initialPos, final BackgroundCellType type) {
		super(initialPos, false);
		this.type = type;
	}

	@Override
	public BackgroundCellType getType() {
		return this.type;
	}

}
