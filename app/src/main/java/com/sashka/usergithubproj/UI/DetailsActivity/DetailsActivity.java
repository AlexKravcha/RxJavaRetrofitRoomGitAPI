package com.sashka.usergithubproj.UI.DetailsActivity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sashka.usergithubproj.Model.UsersResponse;
import com.sashka.usergithubproj.R;
import com.sashka.usergithubproj.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityInterface {
    private ActivityDetailsBinding binding;
    private DetailsActivityPresenter presenter;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        presenter = new DetailsActivityPresenter(this);

        if (getIntent() != null) {
            login = getIntent().getStringExtra("userLogin");
            presenter.viewCreated(login);
        } else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();


    }


    @Override
    public void displayUser(UsersResponse user) {
        binding.setUser(user);
        Glide.with(this).load(user.getAvatarUrl()).into(binding.detailsIvAvatar);
        binding.likeIcon.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                presenter.likeIsChecked();
            } else {
                presenter.likeIsUnchecked();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyed();
    }
}
