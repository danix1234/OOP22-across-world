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
public class EntityTest {
    /**
     * assure that entity methods works
     */
    @Test
    void entityTest() {
        Entity staticEntity = new EntityImpl(new Vector2D(2, 6), false);
        Entity movableEntity = new EntityImpl(new Vector2D(0, 8), true);

        assertFalse(staticEntity.isMovable());
        assertTrue(movableEntity.isMovable());
        assertEquals(staticEntity.getPosition(), new Vector2D(2, 6));
        assertEquals(movableEntity.getPosition(), new Vector2D(0, 8));
        staticEntity.move(2, 7);
        assertTrue(staticEntity.getPosition().equals(new Vector2D(2, 6))); // rimasta posizione originaria (non può
                                                                           // muoversi)
        movableEntity.move(1, 8);
        assertTrue(movableEntity.getPosition().equals(new Vector2D(1, 8))); // posizione corrente aggiornata (movimento
                                                                            // avvenuto correttamente)
    }
}
