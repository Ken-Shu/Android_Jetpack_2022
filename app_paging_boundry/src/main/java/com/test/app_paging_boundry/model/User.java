package com.test.app_paging_boundry.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @SerializedName(value = "id")
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    private Integer id;

    @SerializedName(value = "login")
    @ColumnInfo(name = "loginName", typeAffinity = ColumnInfo.TEXT)
    private String loginName;


    @SerializedName(value = "avatar_url")
    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.TEXT)
    private String avatar;

    public User(){

    }

    public User(Integer id, String loginName, String avatar) {
        this.id = id;
        this.loginName = loginName;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
