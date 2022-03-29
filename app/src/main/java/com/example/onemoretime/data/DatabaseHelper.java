package com.example.onemoretime.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.onemoretime.app.App;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="ScoutingData.db";
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public DatabaseHelper(){
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //Creates Tables using the names of the repos
    public void onCreate(SQLiteDatabase db){
        db.execSQL();
    }

}
