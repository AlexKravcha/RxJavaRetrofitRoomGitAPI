package com.sashka.usergithubproj.UI.MainActivity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.sashka.usergithubproj.Adapters.UsersAdapter;
import com.sashka.usergithubproj.Model.UsersResponse;
import com.sashka.usergithubproj.R;
import com.sashka.usergithubproj.databinding.ActivityMainBinding;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {
    private MainActivityPresenterInterface mainPresenter;
    private RecyclerView.Adapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainPresenter = new MainActivityPresenter(this);

        binding.recyclerList.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.loadFromServer();

        binding.bottomNavigationView.setOnNavigationItemSelectedListener((MenuItem item) -> {
            switch (item.getItemId()) {
                case R.id.action_everything:
                    mainPresenter.loadFromServer();
                    break;
                case R.id.action_mine:
                   mainPresenter.loadFromDatabase();
                    break;
            }
            return true;
        });

        mainPresenter.viewCreated();
    }


    @Override
    public void displayUsers(List<UsersResponse> users) {
        if (users != null) {
            adapter = new UsersAdapter(users, MainActivity.this);
            binding.recyclerList.setAdapter(adapter);
        } else {
            Log.d("error", "UsersResponse is null");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.destroyed();
    }
}
