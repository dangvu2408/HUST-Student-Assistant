package com.example.app_01.Constructor;

public class ReItems {
    private int resouceIcon;
    private String text, textdes;
    public ReItems(int resouceIcon, String text, String textdes) {
        this.resouceIcon = resouceIcon;
        this.text = text;
        this.textdes = textdes;
    }

    public int getResouceIcon() {
        return resouceIcon;
    }

    public void setResouceIcon(int resouceIcon) {
        this.resouceIcon = resouceIcon;
    }

    public String getReText() {
        return text;
    }

    public void setReText(String text) {
        this.text = text;
    }

    public String getTextdes() {
        return textdes;
    }

    public void setTextdes(String textdes) {
        this.textdes = textdes;
    }
}
