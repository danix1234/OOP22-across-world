package it.unibo.project.controller.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;
import it.unibo.project.game.model.impl.BackgroundCellImpl;
import it.unibo.project.game.model.impl.CollectableImpl;
import it.unibo.project.game.model.impl.ObstacleImpl;
import it.unibo.project.utility.Vector2D;

/**
 * class to load maps from files.
 */
public abstract class AbstractMapLoader extends AbstractStatLoader {

    private void loadMapBuffers() {
        final var mapBuffers = new HashMap<Difficulty, List<String>>();
        for (final var difficulty : Difficulty.values()) {
            mapBuffers.put(difficulty, loadResourceFile(MAPS_DIR + FILE_SEP + MAP_FILES.get(difficulty)));
        }
        setMapBufferOpt(Optional.of(mapBuffers));
    }

    private List<String> getMapBuffer(final Difficulty difficulty) {
        return getMapBufferOpt().orElseGet(() -> {
            loadMapBuffers();
            return getMapBufferOpt().orElseThrow();
        }).get(difficulty);
    }

    private List<Vector2D> loadEntity(final Difficulty difficulty, final String nameEntity) {
        return getMapBuffer(difficulty)
                .stream()
                .map(String::strip)
                .dropWhile(line -> !line.equalsIgnoreCase("[" + nameEntity + "]"))
                .skip(1)
                .takeWhile(line -> line.length() > 0 && line.charAt(0) != '[')
                .map(line -> line.split(" "))
                .map(line -> new Vector2D(Integer.parseInt(line[0]), Integer.parseInt(line[1])))
                .toList();
    }

    private List<Collectable> loadEntityCollectable(final Difficulty difficulty, final CollectableType type) {
        return loadEntity(difficulty, type.name())
                .stream()
                .map(vector -> (Collectable) new CollectableImpl(vector, type))
                .toList();
    }

    private List<Obstacle> loadEntityObstacle(final Difficulty difficulty, final ObstacleType type) {
        return loadEntity(difficulty, type.name())
                .stream()
                .map(vector -> (Obstacle) new ObstacleImpl(vector, type))
                .toList();
    }

    private List<BackgroundCell> loadEntityBackground(final Difficulty difficulty, final BackgroundCellType type) {
        return loadEntity(difficulty, type.name())
                .stream()
                .map(vector -> (BackgroundCell) new BackgroundCellImpl(vector, type))
                .toList();
    }

    @Override
    public final void loadMaps() {
        final Map<Difficulty, List<Obstacle>> obstacleAll = new HashMap<>();
        final Map<Difficulty, List<BackgroundCell>> backgroundCellAll = new HashMap<>();
        final Map<Difficulty, List<Collectable>> collectableAll = new HashMap<>();

        for (final var difficulty : Difficulty.values()) {
            final List<Obstacle> obstacles = new ArrayList<>();
            final List<BackgroundCell> backgroundCells = new ArrayList<>();
            final List<Collectable> collectables = new ArrayList<>();

            for (final var obstacleType : ObstacleType.values()) {
                obstacles.addAll(loadEntityObstacle(difficulty, obstacleType));
            }
            for (final var backgroundType : BackgroundCellType.values()) {
                backgroundCells.addAll(loadEntityBackground(difficulty, backgroundType));
            }
            for (final var collectableType : CollectableType.values()) {
                collectables.addAll(loadEntityCollectable(difficulty, collectableType));
            }

            obstacleAll.put(difficulty, obstacles);
            backgroundCellAll.put(difficulty, backgroundCells);
            collectableAll.put(difficulty, collectables);
        }

        setObstaclesOpt(Optional.of(obstacleAll));
        setBackgroundCellsOpt(Optional.of(backgroundCellAll));
        setCollectablesOpt(Optional.of(collectableAll));
    }

}
