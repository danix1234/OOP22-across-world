package it.unibo.project.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.project.game.model.api.Entity;
import it.unibo.project.game.model.impl.EntityImpl;
import it.unibo.project.utility.Vector2D;

/**
 * tests for {@linkplain EntityTest} class.
 */
class EntityTest {
    /**
     * assure that entity methods works.
     */
    @Test
    void entityTest() {
        final Vector2D pos1 = new Vector2D(2, 6);
        final Vector2D pos2 = new Vector2D(0, 8);
        final Entity staticEntity = new EntityImpl(pos1, false);
        final Entity movableEntity = new EntityImpl(pos2, true);

        assertFalse(staticEntity.isMovable());
        assertTrue(movableEntity.isMovable());
        assertEquals(staticEntity.getPosition(), pos1);
        assertEquals(movableEntity.getPosition(), pos2);

        staticEntity.move(pos1.getX(), pos1.getY() + 1);
        assertEquals(staticEntity.getPosition(), pos1);

        movableEntity.move(pos2.getX() + 1, pos2.getY());
        assertEquals(movableEntity.getPosition(), new Vector2D(pos2.getX() + 1, pos2.getY()));
    }
}
