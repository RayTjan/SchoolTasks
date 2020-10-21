package com.example.trialmvvm.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.trialmvvm.model.Cast;
import com.example.trialmvvm.model.Genre;
import com.example.trialmvvm.repository.MovieRepository;
import com.example.trialmvvm.repository.TvShowRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private TvShowRepository tvShowRepository;

    public DetailViewModel() {
        movieRepository = MovieRepository.getInstance();
        tvShowRepository = TvShowRepository.getInstance();
    }

    public LiveData<List<Genre>> getGenresM(int id) {
        return movieRepository.getGenreCollection(id);
    }

    public LiveData<List<Cast>> getCastsM(int id) {
        return movieRepository.getCastCollection(id);
    }

    public LiveData<List<Genre>> getGenresT(int id) {
        return tvShowRepository.getGenreCollection(id);
    }

    public LiveData<List<Cast>> getCastsT(int id) {
        return tvShowRepository.getCastCollection(id);
    }
}
