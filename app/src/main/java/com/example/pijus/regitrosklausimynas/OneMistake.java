package com.example.pijus.regitrosklausimynas;

public class OneMistake {
    boolean isSelected;
    String oneMistake;
    public OneMistake(boolean isSelected, String mistake) {
        this.isSelected=isSelected;
        this.oneMistake=mistake;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public String getOneMistake() {
        return oneMistake;
    }
}
