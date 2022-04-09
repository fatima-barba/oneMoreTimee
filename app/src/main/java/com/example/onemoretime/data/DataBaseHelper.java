package com.example.onemoretime.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "scoutingData.db", null, 1);
    }

    //there should be code here to create a new database. this will be called the first time you try to access a databse
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    //this is called if the database version number changes, think of it like updating a game
    //they are releasing a new version like minecraft going from 17.00 to 17.1
    //it prevents previous versions from crashing when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
