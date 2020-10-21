package com.example.trialmvvm.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.model.TvShow;
import com.example.trialmvvm.repository.MovieRepository;
import com.example.trialmvvm.repository.TvShowRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    private final TvShowRepository repository;

    public TvShowViewModel(){
        repository = TvShowRepository.getInstance();
    }

    public LiveData<List<TvShow>> getTvShowCollection(){
        return repository.getTvShowCollection();
    }
}
