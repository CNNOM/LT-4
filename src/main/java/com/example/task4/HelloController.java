package com.example.task4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

import java.io.File;

public class HelloController {

    public Aggregate aggregate;
    public Iterator iter;

    public Timeline time = new Timeline();

    private boolean isPlaying = false;

    @FXML
    private Button startStopButton;

    @FXML
    private ImageView screen;

    @FXML
    private TextField delayField;

    @FXML
    private Button chooseFolderButton;

    public void initialize() {
        aggregate = new ConcreteAggregate("src/main/resources/img");
        iter = aggregate.getIterator();

        // Установка количества повторений
        time.setCycleCount(Timeline.INDEFINITE);
        updateTimeline(1000);

        screen.setPreserveRatio(false);
    }

    // Обработчик события для показа кадров
    private class EvHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Image image = (Image) iter.next();
            if (image != null) {
                screen.setImage(image);
            }
        }
    }

    @FXML
    public void toggleAnimation() {
        if (isPlaying) {
            time.pause();
            startStopButton.setText("⏹");
        } else {
            startStopButton.setText("▶");
            time.play();
        }
        isPlaying = !isPlaying;
    }

    // Метод для обновления временной шкалы с новой задержкой
    @FXML
    public void updateDelay() {
        int newDelay = Integer.parseInt(delayField.getText());
        updateTimeline(newDelay);
    }

    // Метод для обновления временной шкалы
    private void updateTimeline(int delayMillis) {
        time.stop();
        time.getKeyFrames().clear();
        time.getKeyFrames().add(new KeyFrame(Duration.millis(delayMillis), new EvHandler()));
        if (isPlaying) {
            time.play(); // возобновляем
        }
    }

    @FXML
    public void next() {
        Image image = (Image) iter.next();
        if (image != null) {
            screen.setImage(image);
        }
    }

    @FXML
    public void preview() {
        Image image = (Image) iter.preview();
        if (image != null) {
            screen.setImage(image);
        }
    }

    @FXML
    public void chooseFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(chooseFolderButton.getScene().getWindow());

        if (selectedDirectory != null) {
            aggregate = new ConcreteAggregate(selectedDirectory.getAbsolutePath());
            iter = aggregate.getIterator();
            Image image = (Image) iter.next();
            if (image != null) {
                screen.setImage(image);
            }
        }
    }
}