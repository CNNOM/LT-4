package com.example.task4;

import javafx.scene.image.Image;

import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class ImageIterator implements Iterator {
    private int current = 0;
    private String filetopic;
    private String basePath;

    public ImageIterator(String filetopic, String basePath) {
        this.filetopic = filetopic;
        this.basePath = basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        current = 0; // Сбрасываем текущий индекс при смене папки
    }

    private Image getImage(int index) {
        try {
            String filename = Paths.get(basePath + "/" + filetopic + index + ".png").toUri().toString();

            URL url = new URL(filename);
            URLConnection connection = url.openConnection();
            connection.connect();

            return new Image(filename);
        } catch (Exception e) {
            current = 1;
            return getImage(1);
        }
    }

    @Override
    public boolean hasNext(int i) {
        return getImage(current + i) != null;
    }

    @Override
    public Object next() {
        current++;
        return getImage(current);
    }

    @Override
    public Object preview() {
        if (current != 1){
            current--;
        }
        System.out.println(current);
        return getImage(current);
    }
}