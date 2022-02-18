package com.example.dukanactivity;

public class Rec_ModelClass {

    private int image;
    private String text;

    //public String[] arr;

    public Rec_ModelClass(String text , int image) {
        this.text = text;
        this.image=image;

    }


    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String  getText() {
        return text ;
    }
}







