package com.example.onemoretime.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.onemoretime.app.App;
import com.example.onemoretime.data.model.Competitions;
import com.example.onemoretime.data.repo.CompetitionsRepo;

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
        db.execSQL(CompetitionsRepo.createTable());

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d", oldVersion, newVersion));

        db.execSQL("Drop Table if Exists "+ Competitions.TABLE);

        onCreate(db);
    }

}
