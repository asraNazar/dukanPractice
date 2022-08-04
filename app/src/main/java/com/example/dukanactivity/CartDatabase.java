package com.example.dukanactivity;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CartForRoom.class, ImagesResponse.class}, version = 3, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDao cartDao();

    private static CartDatabase instance;


    public static final Migration MIGRATION_1_2 = new Migration(2, 3) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE MyCart" +
                    " RENAME COLUMN test_srgtrh to testing");
            // Since we didn't alter the table, there's nothing else to do here.


            database.execSQL("ALTER TABLE MyCart RENAME TO users " );

        }
    };

    public static CartDatabase getInstance(Context context) {
        if (instance == null)


            instance = Room.databaseBuilder(context, CartDatabase.class
                    , "DB_Cart")
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries().build();
        return instance;
    }

}


