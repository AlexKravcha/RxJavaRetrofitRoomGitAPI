package com.sashka.usergithubproj;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.sashka.usergithubproj.Database.AppDatabase;

public class App extends Application {
    public static App instance;
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "appDatabase")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
