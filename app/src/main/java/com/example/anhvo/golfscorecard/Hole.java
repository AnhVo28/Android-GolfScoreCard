package com.example.anhvo.golfscorecard;

public class Hole {
    private String label;
    private int stokeCount;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getStokeCount() {
        return stokeCount;
    }

    public void setStokeCount(int stokeCount) {
        this.stokeCount = stokeCount;
    }

    public Hole(String label, int stokeCount) {

        this.label = label;
        this.stokeCount = stokeCount;
    }
}
