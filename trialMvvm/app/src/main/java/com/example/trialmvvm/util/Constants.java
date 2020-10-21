package com.example.trialmvvm.util;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;

public class Constants {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "ff5dbcf57a29c953853e510ccc86f334";
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    public interface Type {
        String MOVIES = "movie";
        String TV_SHOWS = "tv";
    }}
