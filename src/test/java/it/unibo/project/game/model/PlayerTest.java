package it.unibo.project.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.project.game.model.api.Player;
import it.unibo.project.game.model.impl.PlayerImpl;
import it.unibo.project.utility.Vector2D;

/**
 * tests for {@linkplain PlayerTest} class.
 */
public class PlayerTest {
    /**
     * assure that player methods works
     */
    @Test
    void testPlayer() {
        Player player = new PlayerImpl(new Vector2D(7, 4));

        assertEquals(player.getPosition(), new Vector2D(7, 4));
        assertEquals(player.getMaxDistance(), 4);

        player.move(7, 5);
        assertEquals(player.getMaxDistance(), 5);
        assertFalse(player.getPosition().equals(new Vector2D(7, 4)));
        assertTrue(player.getPosition().equals(new Vector2D(7, 5)));
    }

}
