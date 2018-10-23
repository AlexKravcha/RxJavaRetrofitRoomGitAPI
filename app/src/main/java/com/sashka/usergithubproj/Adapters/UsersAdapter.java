package com.sashka.usergithubproj.Adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sashka.usergithubproj.Model.UsersResponse;
import com.sashka.usergithubproj.R;
import com.sashka.usergithubproj.UI.DetailsActivity.DetailsActivity;
import com.sashka.usergithubproj.UI.MainActivity.MainActivity;
import com.sashka.usergithubproj.UI.MainActivity.MainActivityInterface;
import com.sashka.usergithubproj.databinding.UserItemBinding;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {

    private List<UsersResponse> usersList;
    private Context context;

    public UsersAdapter(List<UsersResponse> users, MainActivity context) {
        this.usersList = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        UserItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_item, parent, false);

        return new UsersHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        holder.userItemBinding.setUser(usersList.get(position));
        Glide.with(context).load(usersList.get(position).getAvatarUrl()).into(holder.userItemBinding.ivUserAvatar);

        holder.itemView.setOnClickListener(v -> {
            String login = usersList.get(position).getLogin();
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("userLogin",login);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UsersHolder extends RecyclerView.ViewHolder {
        UserItemBinding userItemBinding;

        public UsersHolder(UserItemBinding userItemBinding) {
            super(userItemBinding.getRoot());
            this.userItemBinding = userItemBinding;
        }
    }
}
