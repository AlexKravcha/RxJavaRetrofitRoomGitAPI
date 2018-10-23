package com.sashka.usergithubproj.UI.MainActivity;

import android.content.Intent;
import android.view.MenuItem;

import com.sashka.usergithubproj.App;
import com.sashka.usergithubproj.Database.AppDatabase;
import com.sashka.usergithubproj.Model.UsersResponse;
import com.sashka.usergithubproj.Model.UsersResponseDao;
import com.sashka.usergithubproj.Network.NetworkClient;
import com.sashka.usergithubproj.R;

import com.sashka.usergithubproj.databinding.ActivityMainBinding;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityPresenterInterface {

    private MainActivityInterface activityInterface;
    private Disposable disposable;
    private Disposable disposable2;
    private UsersResponseDao usersResponseDao;

    MainActivityPresenter(MainActivityInterface activityInterface) {
        this.activityInterface = activityInterface;
        usersResponseDao = App.getInstance().getAppDatabase().usersResponseDao();
    }

    @Override
    public void viewCreated() {

    }

    @Override
    public void loadFromServer() {
        disposable = getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activityInterface::displayUsers, Throwable::printStackTrace);
    }

    @Override
    public void loadFromDatabase() {
        disposable2 = usersResponseDao.getAllUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activityInterface::displayUsers, Throwable::printStackTrace);
    }

    private Single<List<UsersResponse>> getUsersList() {
        return NetworkClient.getRetrofit()
                .getUsersList();
    }

    @Override
    public void destroyed() {
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
        if (disposable2 != null && !disposable2.isDisposed()) disposable2.dispose();
    }


}
