package com.example.dukanactivity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "MyCart")
public class CartForRoom {
        @PrimaryKey(autoGenerate = true)
        public int itemId;

        private int id;

        @ColumnInfo(name = "imageid")
        public String imageid;

        @ColumnInfo(name = "price")
        public String price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

//        public List<CartForRoom> getCarts (){
//
//            List<CartForRoom> cart = getCarts();
//        return carts();
//        }

    }


