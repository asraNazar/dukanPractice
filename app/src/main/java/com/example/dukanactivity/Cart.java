package com.example.dukanactivity;

import java.util.ArrayList;

public class Cart {
    //creating singleton object to pass data
    private static Cart INSTANCE;
    private ArrayList<ImagesResponse> cartItems = new ArrayList<>();
    private Cart(){}

    public static Cart getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Cart();
            return INSTANCE;
        }

        return INSTANCE;
    }

    public ArrayList<ImagesResponse> getCartItems() {
        return cartItems;
    }

    public void addToCart(ImagesResponse product){
        cartItems.add(product);
    }

    public int getItemsCount(){
        return cartItems.size();
    }

    public void updateToCart(ImagesResponse product){
        cartItems = getCartItems();
        if (!cartItems.isEmpty()){
        cartItems.add(product);
         }
//        cartItems.remove(product);

    }



}
