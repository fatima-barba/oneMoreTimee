package com.example.onemoretime.app;

import android.app.Application;
import android.content.Context;

import com.example.onemoretime.data.DatabaseHelper;
import com.example.onemoretime.data.DatabaseManager;

public class App extends Application {
    private static Context context;
    private static DatabaseHelper databaseHelper;

    @Override
    public void onCreate(){
        super.onCreate();
        context = this.getApplicationContext();
        databaseHelper = new DatabaseHelper();
        DatabaseManager.initializedInstance(databaseHelper);
    }
    public static Context getContext() return context;
}
