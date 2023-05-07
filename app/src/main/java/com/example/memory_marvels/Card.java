package com.example.memory_marvels;

public class Card {

    private int imageId;
    private boolean flipped;

    public Card(int imageId) {
        this.imageId = imageId;
        flipped = false;
    }

    public int getImageId() {
        return imageId;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
