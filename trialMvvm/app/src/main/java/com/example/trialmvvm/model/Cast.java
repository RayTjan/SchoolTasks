package com.example.trialmvvm.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cast implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("character")
    private String role;
    @SerializedName("profile_path")
    private String img_url;

    public Cast() {

    }

    public Cast(String name, String role, String img_url) {
        this.name = name;
        this.role = role;
        this.img_url = img_url;
    }

    protected Cast(Parcel in) {
        name = in.readString();
        role = in.readString();
        img_url = in.readString();
    }

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(role);
        parcel.writeString(img_url);
    }
}
