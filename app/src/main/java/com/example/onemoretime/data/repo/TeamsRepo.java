package com.example.onemoretime.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.onemoretime.data.DatabaseManager;
import com.example.onemoretime.data.model.Teams;

public class TeamsRepo {

    private final String TAG = TeamsRepo.class.getSimpleName();

    public static String createTable(){
        return  "CREATE TABLE " + Teams.TABLE + "("
                + Teams.KEY_TeamNum + " INTEGER not null PRIMARY KEY , "
                + Teams.KEY_TeamName + " TEXT ) ";
    }

    //inserts a new team row into the database
    public int insert(Teams teams){
        int teamId = -1;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Teams.KEY_TeamNum, teams.getTeamNum());
        values.put(Teams.KEY_TeamName, teams.getTeamName());

        //checks for conflicts with return -1

    }

}
