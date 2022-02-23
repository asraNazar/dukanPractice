package com.example.dukanactivity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity(tableName = "MyProduct")
public class ImagesResponse implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int itemId;

    @SerializedName("id")
    public int id;


    @SerializedName("image")
    public String image;

    @SerializedName("price")
    private String price;

    @SerializedName("title")
    private String title;



    public ImagesResponse(){

    }

    public ImagesResponse(int id, String price,String title, String image) {
        this.id = id;
        this.price = price;
        this.image = image;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return    title;
    }

    public void setTitle(String  title) {
        this. title =  title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return image;
    }

    public void setUrl(String url) {
        this.image = url;
    }
}
