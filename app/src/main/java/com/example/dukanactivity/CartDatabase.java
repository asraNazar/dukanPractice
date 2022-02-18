package com.example.dukanactivity;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={CartForRoom.class},version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

    private static CartDatabase instance;

    public static CartDatabase getInstance(Context context){
        if (instance==null)
            instance= Room.databaseBuilder(context,CartDatabase.class
            ,"DB_Cart").allowMainThreadQueries().build();
        return instance;
    }

}


