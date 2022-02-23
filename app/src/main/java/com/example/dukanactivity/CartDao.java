package com.example.dukanactivity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    public void addToCart(CartForRoom cart);

    @Update
    public void updateCart(CartForRoom cart);

    @Delete
    void deleteCartItems(CartForRoom cart);


    @Insert
    void addToRoom(List<ImagesResponse> products);


    @Query("SELECT * FROM MyProduct")
    public List<ImagesResponse> getAllItems();

    @Query("SELECT * FROM MyCart")
    public List<CartForRoom> getCartItems();

    @Query("SELECT * FROM mycart WHERE id=:id LIMIT 1")
    CartForRoom getData(int id);

    @Query("select SUM (count) from MyCart")
    int countCart();
//
    @Query("DELETE FROM MyProduct")
    void emptyCart();




}
