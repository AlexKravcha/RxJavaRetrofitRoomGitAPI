package com.sashka.usergithubproj.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sashka.usergithubproj.Model.UsersResponse;
import com.sashka.usergithubproj.Model.UsersResponseDao;

@Database(entities = {UsersResponse.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UsersResponseDao usersResponseDao();
}
