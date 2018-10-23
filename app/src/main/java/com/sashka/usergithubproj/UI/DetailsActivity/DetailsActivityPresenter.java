package com.sashka.usergithubproj.UI.DetailsActivity;


import com.sashka.usergithubproj.App;
import com.sashka.usergithubproj.Database.AppDatabase;
import com.sashka.usergithubproj.Model.UsersResponse;
import com.sashka.usergithubproj.Model.UsersResponseDao;
import com.sashka.usergithubproj.Network.NetworkClient;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailsActivityPresenter implements DetailsActivityPresenterInterface {
    private DetailsActivityInterface activityInterface;
    private Disposable disposable;
    private UsersResponseDao usersResponseDao;

    private void setUserDb(UsersResponse userDb) {
        this.userDb = userDb;
    }

    private UsersResponse userDb;

    DetailsActivityPresenter(DetailsActivityInterface activityInterface) {
        this.activityInterface = activityInterface;
        usersResponseDao = App.getInstance().getAppDatabase().usersResponseDao();
    }

    @Override
    public void viewCreated(String login) {
        usersResponseDao.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<UsersResponse>() {
                    @Override
                    public void onSuccess(UsersResponse user) {
                        setUserDb(user);
                        activityInterface.displayUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        disposable = getUser(login)
                                .doOnSuccess(DetailsActivityPresenter.this::setUserDb)
                                .doOnSuccess(usersResponse -> setUserDb(usersResponse))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(activityInterface::displayUser, Throwable::printStackTrace);

                    }
                });
    }

    private Single<UsersResponse> getUser(String login) {
        return NetworkClient.getRetrofit()
                .getUser(login);
    }

    @Override
    public void likeIsChecked() {
        userDb.checked = true;
        Completable.fromAction(() -> usersResponseDao.insert(userDb))
                .subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void likeIsUnchecked() {
        Completable.fromAction(() -> usersResponseDao.delete(userDb.getLogin()))
                .subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void destroyed() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
