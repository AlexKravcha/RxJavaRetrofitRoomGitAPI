
package com.sashka.usergithubproj.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class UsersResponse {

    public boolean checked;
    @SerializedName("login")
    @Expose
    private String login;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;


    /**
     * No args constructor for use in serialization
     * 
     */
    public UsersResponse() {
    }

    /**
     * @param checked
     *
     * @param id
     * @param avatarUrl

     * @param login
     */

    public UsersResponse(boolean checked, String login, Integer id,  String avatarUrl) {
        super();
        this.checked = checked;
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
