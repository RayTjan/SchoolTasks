package com.example.trialmvvm.ui.main.movie;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.trialmvvm.R;
import com.example.trialmvvm.etc.Glovar;
import com.example.trialmvvm.etc.ItemClickSupport;
import com.example.trialmvvm.etc.MovieAdapter;
import com.example.trialmvvm.model.Genre;
import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.ui.MainActivity;
import com.example.trialmvvm.ui.splash.SplashFragmentDirections;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public class MovieFragment extends Fragment {
//    @BindView(R.id.button_movieToDetail)
//    Button button;

    @BindView(R.id.recyclerView_MovieList)
    RecyclerView rvMovie;

    private MovieViewModel viewModel;
    Dialog dialog;

    @Override
    public void onStart() {
        super.onStart();
        dialog = Glovar.loadingDialog(getActivity());
        dialog.show();
        Log.d("IM IN MOVIE","WHY SO BLACK");

    }

    ArrayList<Movie> listMovie;
    Movie movie;
    MovieAdapter movieAdapter;

    final ArrayList<Genre> genreArrayList = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setLogo(R.drawable.ic_baseline_movie_black);


        listMovie = new ArrayList<Movie>();
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieAdapter(getActivity());
        viewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        movie = new Movie();
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);

//        getGenreList(); //CARA JSON TAPI GK JADI KRN NANTI HILANG NILAI

//        viewModel.getGenreCollection().observe(requireActivity(),observeGenre);
//        button.setOnClickListener(view1 -> {
//            NavDirections action = MovieFragmentDirections.actionMovietoDetail(movie);
//            Navigation.findNavController(view).navigate(action);
//        });

        ///////////////////////////////////////////////////////////////////


    }

//    private void getGenreList() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=ff5dbcf57a29c953853e510ccc86f334";
//
//        client.get(url, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                try {
//                    String result = new String(responseBody);
//                    JSONObject responseObject = new JSONObject(result);
//                    JSONArray list = responseObject.getJSONArray("genres");
//                    for (int i = 0; i < list.length(); i++) {
//                        JSONObject obj = list.getJSONObject(i);
//                        Genre genre = new Genre(obj.getString("id"), obj.getString("name"));
//                        genreArrayList.add(genre);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//            }
//        });
//    }


    private Observer<List<Movie>> observeViewModel = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            if (movies != null) {
//                movie = movies.get(0);
//                button.setText(movie.getTitle());
//                Toast.makeText(getActivity(), movies.get(0).getTitle(), Toast.LENGTH_SHORT).show();
//                for (int i = 0; i < movies.size(); i++) {
//                    for (int k = 0; k < movies.get(i).getGenre().length; k++) {
//                        for (int j = 0; j < genreArrayList.size(); j++) {
//                            if (movies.get(i).getGenre()[k].equals(genreArrayList.get(j).getId())) {
//                                movies.get(i).getGenre()[k] = genreArrayList.get(j).getName();
//                                break;
//                            }
//
//                        }
//                    }
//                }
                movieAdapter.setMovieList(movies);
                movieAdapter.notifyDataSetChanged();
                rvMovie.setAdapter(movieAdapter);

            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.cancel();
                }
            }, 200);

        }
    };

//    private Observer <List<Genre>> observeGenre = new Observer<List<Genre>>() {
//        @Override
//        public void onChanged(List<Genre> genres) {
//            if (genres != null){
//            }
//        }
//    };
}