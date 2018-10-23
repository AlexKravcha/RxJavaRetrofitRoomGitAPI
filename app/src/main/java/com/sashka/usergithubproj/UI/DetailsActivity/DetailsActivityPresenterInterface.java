package com.sashka.usergithubproj.UI.DetailsActivity;

public interface DetailsActivityPresenterInterface {
    void viewCreated(String login);
    void destroyed();
    void likeIsChecked();
    void likeIsUnchecked();
}
