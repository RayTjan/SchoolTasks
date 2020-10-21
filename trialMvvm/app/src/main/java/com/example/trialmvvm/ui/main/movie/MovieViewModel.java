package com.example.trialmvvm.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.trialmvvm.model.Genre;
import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private final MovieRepository repository;

    public MovieViewModel(){
        repository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovieCollection(){
        return repository.getMovieCollection();
    }
}
