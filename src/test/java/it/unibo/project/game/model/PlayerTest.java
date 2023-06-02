package it.unibo.project.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import it.unibo.project.game.model.api.Player;
import it.unibo.project.game.model.impl.PlayerImpl;
import it.unibo.project.utility.Vector2D;

/**
 * tests for {@linkplain PlayerTest} class.
 */
class PlayerTest {
    /**
     * assure that player methods works.
     */
    @Test
    void testPlayer() {
        final Vector2D pos1 = new Vector2D(7, 4);
        final Vector2D pos2 = new Vector2D(7, 5);
        final Player player = new PlayerImpl(pos1);

        assertEquals(player.getPosition(), pos1);
        assertEquals(player.getMaxDistance(), pos1.getY());

        player.move(pos2.getX(), pos2.getY());
        assertEquals(player.getMaxDistance(), pos2.getY());
        assertNotEquals(player.getPosition(), pos1);
        assertEquals(player.getPosition(), pos2);
    }

}
