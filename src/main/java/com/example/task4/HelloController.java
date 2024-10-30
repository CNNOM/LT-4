package com.example.task4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    public ImageIterator iter = new ImageIterator("img", "src/main/resources");

    public Timeline time = new Timeline();

    private boolean isPlaying = false;
    @FXML
    private Button startStopButton;

    @FXML
    private ImageView screen;

    @FXML
    private TextField delayField;

    public void initialize() {
        iter.setBasePath("src/main/resources/img");

        // Установка количества повторений
        time.setCycleCount(Timeline.INDEFINITE);
        updateTimeline(1000);

        screen.setPreserveRatio(false);
    }

    // Обработчик события для показа кадров
    private class EvHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            screen.setImage((Image) iter.next());
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
        screen.setImage((Image) iter.next());
    }

    @FXML
    public void preview(){
        screen.setImage((Image) iter.preview());
    }

    @FXML
    public void setImgFolder() {
        iter.setBasePath("src/main/resources/img");
        screen.setImage((Image) iter.next()); // Обновляем изображение на экране
    }

    @FXML
    public void setImg1Folder() {
        iter.setBasePath("src/main/resources/img1");
        screen.setImage((Image) iter.next()); // Обновляем изображение на экране
    }
}