package com.sashka.usergithubproj.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface UsersResponseDao {
    @Query("SELECT * FROM usersresponse")
    Flowable<List<UsersResponse>> getAllUsers();


    @Query("SELECT * FROM usersresponse WHERE login IS :login")
    Single<UsersResponse> getUser(String login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UsersResponse user);

    @Update
    void update(UsersResponse user);

    @Query("DELETE FROM usersresponse WHERE login IS :login")
    void delete(String login);
}
