package com.sashka.usergithubproj.UI.MainActivity;

import com.sashka.usergithubproj.databinding.ActivityMainBinding;

public interface MainActivityPresenterInterface {
    void viewCreated();
    void destroyed();
    void loadFromServer();
    void loadFromDatabase();
}
