package com.sashka.usergithubproj.Network;

import com.sashka.usergithubproj.Model.UsersResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkClientInterface {

    @GET("users")
    Single<List<UsersResponse>> getUsersList();

    @GET("users/{username}")
    Single<UsersResponse> getUser(@Path("username") String login);

// если нужно с какого то элемнта вывести 10 пользователей пишем since и в MainActivityPresenter в getUsersList пишем число
//    @GET("users")
//    Single<List<UsersResponse>> getUsersList(@Query("since") String since);
}
