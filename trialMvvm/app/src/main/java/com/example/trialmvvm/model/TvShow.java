package com.example.trialmvvm.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TvShow implements Parcelable {
    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private Double vote;

    @SerializedName("backdrop_path")
    private String cover;

    public TvShow(){}

    public TvShow(String name, String popularity, String poster, String cover, String description, String first_air_date, int id, Double vote) {
        this.name = name;
        this.popularity = popularity;
        this.poster = poster;
        this.cover = cover;
        this.description = description;
        this.first_air_date = first_air_date;
        this.id = id;
        this.vote = vote;
    }

    protected TvShow(Parcel in) {
        name = in.readString();
        popularity = in.readString();
        poster = in.readString();
        cover = in.readString();
        description = in.readString();
        first_air_date = in.readString();
        id = in.readInt();
        if (in.readByte() == 0) {
            vote = null;
        } else {
            vote = in.readDouble();
        }
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(popularity);
        parcel.writeString(description);
        parcel.writeString(poster);
        parcel.writeString(cover);
        parcel.writeString(first_air_date);
        parcel.writeInt(id);
        parcel.writeDouble(vote);
    }
}

