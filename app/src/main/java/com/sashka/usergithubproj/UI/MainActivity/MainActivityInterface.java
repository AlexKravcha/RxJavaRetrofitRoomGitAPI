package com.sashka.usergithubproj.UI.MainActivity;

import com.sashka.usergithubproj.Model.UsersResponse;

import java.util.List;

public interface MainActivityInterface {
    void displayUsers(List<UsersResponse> users);
}
