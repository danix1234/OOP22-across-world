package it.unibo.project.controller.core.impl;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import it.unibo.project.game.model.api.BackgroundCellType;
import it.unibo.project.game.model.api.CollectableType;
import it.unibo.project.game.model.api.ObstacleType;

public abstract class AbstractImageLoader extends AbstractMapLoader {

    private Image loadImage(final String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            LauncherImpl.LAUNCHER.closeWindow();
        }

        // can't be reached, but java compiler can't know
        return null;
    }

    private List<Image> loadImages(final String directory, final List<String> fileNames) {
        return fileNames.stream()
                .map(fileName -> directory + FILE_SEP + fileName)
                .map(this::loadImage)
                .toList();
    }

    @Override
    public void loadImages() {
        // players
        this.playerImages = Optional.of(loadImages(PLAYER_DIR, PLAYER_FILES));

        // collectables
        final Map<CollectableType, List<Image>> collectableImages = new HashMap<>();
        for (final CollectableType type : CollectableType.values()) {
            collectableImages.put(type, loadImages(COLLECTABLE_DIR, COLLECTABLE_FILES.get(type)));
        }
        this.collectableImages = Optional.of(collectableImages);

        // obstacle
        final Map<ObstacleType, List<Image>> obstacleImages = new HashMap<>();
        for (final ObstacleType type : ObstacleType.values()) {
            obstacleImages.put(type, loadImages(OBSTACLE_DIR, OBSTACLE_FILES.get(type)));
        }
        this.obstacleImages = Optional.of(obstacleImages);

        // background
        final Map<BackgroundCellType, List<Image>> backgroundCellImages = new HashMap<>();
        for (final BackgroundCellType type : BackgroundCellType.values()) {
            backgroundCellImages.put(type, loadImages(BACKGROUND_DIR, BACKGROUND_FILES.get(type)));
        }
        this.backgroundCellImages = Optional.of(backgroundCellImages);
    }

}
