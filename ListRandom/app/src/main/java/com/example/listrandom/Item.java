package com.example.listrandom;

public class Item {
    private String name;
    private int progress;

    // create a new item
    public Item(String name, int progress) {
        this.name = name; // item name
        this.progress = progress; // item progress
    }

    public String getName() {
        return name;
    }
    public int getProgress() {
        return progress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}