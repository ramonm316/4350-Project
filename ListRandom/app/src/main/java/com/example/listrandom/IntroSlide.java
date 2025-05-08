package com.example.listrandom;

public class IntroSlide {
    private final String title;
    private final String description;
    private final int imageResId;

    public IntroSlide(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
