package com.example.onemoretime.data.repo;

import com.example.onemoretime.data.model.Competitions;
import com.example.onemoretime.data.model.Matches;
import com.example.onemoretime.data.model.Teams;

public class MatchesRepo {

    private final String TAG = MatchesRepo.class.getSimpleName();

    public static String createTable(){
        return "CREATE TABLE " + Matches.TABLE + "("
                + Matches.KEY_CompId + " TEXT not null , "
                + Matches.KEY_MatchNumber + " INTEGER not null PRIMARY KEY , "
                + Matches.KEY_TeamNumber + " INTEGER not null , "
                + Matches.KEY_MatchPosition + " INTEGER not null , "
                + " FOREIGN KEY ( " + Matches.KEY_CompId + " ) REFERENCES " + Competitions.TABLE
                + " ( " + Competitions.KEY_CompId + " ), "
                + " FOREIGN KEY ( " + Matches.KEY_TeamNumber + " ) REFERENCES " + Teams.TABLE
                + " ( " + Teams.KEY_TeamNum + " ))";

    }

}
