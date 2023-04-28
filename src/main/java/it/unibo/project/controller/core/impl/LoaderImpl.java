package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.util.List;
import java.util.Random;

import it.unibo.project.controller.core.api.Difficulty;
import it.unibo.project.game.model.api.BackgroundCell;
import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.Collectable;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.GameStat;
import it.unibo.project.game.model.api.Obstacle;
import it.unibo.project.game.model.api.ObstacleType;

public class LoaderImpl extends AbstractImageLoader {

    @Override
    public final GameStat getGameStat() {
        return this.gameStat.orElseGet(() -> {
            loadStats();
            return this.gameStat.orElseThrow();
        });
    }

    @Override
    public final List<Image> getBackgroundCellSprites(final BackgroundCellType backgroundCellType) {
        return this.backgroundCellImages.orElseGet(() -> {
            loadImages();
            return this.backgroundCellImages.orElseThrow();
        }).get(backgroundCellType);
    }

    @Override
    public final List<Image> getCollectablesSprites(final CollectableType collectableType) {
        return this.collectableImages.orElseGet(() -> {
            loadImages();
            return this.collectableImages.orElseThrow();
        }).get(collectableType);
    }

    @Override
    public final List<Image> getObstacleSprites(final ObstacleType obstacleType) {
        return this.obstacleImages.orElseGet(() -> {
            loadImages();
            return this.obstacleImages.orElseThrow();
        }).get(obstacleType);
    }

    @Override
    public final List<Image> getPlayerSprites() {
        return this.playerImages.orElseGet(() -> {
            loadImages();
            return this.playerImages.orElseThrow();
        });
    }

    @Override
    public final Image getElementRandom(final List<Image> collection) {
        return collection.get(new Random().nextInt(collection.size()));
    }

    @Override
    public List<BackgroundCell> getBackgroundCells(final Difficulty difficulty) {
        return this.backgroundCells.orElseGet(() -> {
            loadMaps();
            return this.backgroundCells.orElseThrow();
        }).get(difficulty);
    }

    @Override
    public List<Collectable> getCollectables(final Difficulty difficulty) {
        return this.collectables.orElseGet(() -> {
            loadMaps();
            return this.collectables.orElseThrow();
        }).get(difficulty);
    }

    @Override
    public List<Obstacle> getObstacles(final Difficulty difficulty) {
        return this.obstacles.orElseGet(() -> {
            loadMaps();
            return this.obstacles.orElseThrow();
        }).get(difficulty);
    }

}
