package com.example.ble_application;

public class SliderItem {

    private int imageResId;
    private String title;
    private String button1Text;
    private String button2Text;

    public SliderItem(int imageResId, String title, String button1Text, String button2Text) {
        this.imageResId = imageResId;
        this.title = title;
        this.button1Text = button1Text;
        this.button2Text = button2Text;
    }


    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getButton1Text() {
        return button1Text;
    }

    public String getButton2Text() {
        return button2Text;
    }
}
