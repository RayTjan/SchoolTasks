package com.example.trialmvvm.ui.main.detail;

import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trialmvvm.R;
import com.example.trialmvvm.etc.CastAdapter;
import com.example.trialmvvm.etc.Glovar;
import com.example.trialmvvm.model.Genre;
import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.model.TvShow;
import com.example.trialmvvm.ui.MainActivity;
import com.example.trialmvvm.ui.splash.SplashFragmentDirections;
import com.example.trialmvvm.util.Constants;

import org.w3c.dom.Text;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {

    @BindView(R.id.textView_MdisplayTitle)
    TextView titleView;
    @BindView(R.id.textView_MdisplayGenre)
    TextView genreView;
    @BindView(R.id.recyclerView_Cast)
    RecyclerView rvCast;
    @BindView(R.id.textView_detailPopularity)
    TextView popularity;
    @BindView(R.id.textView_detailVote)
    TextView rating;
    @BindView(R.id.textView_detailDescription)
    TextView description;
    @BindView(R.id.imageViewMTCover)
    ImageView cover;
    @BindView(R.id.imageView_MTPoster)
    ImageView poster;

    private Movie movie;
    private TvShow tvShow;
    private DetailViewModel viewModel;
    private String genreDisplay;

    private CastAdapter adapter;
    Dialog dialog; //SET ANIMATION ORRR FIND OUT HOW TO MAKE GOOD PROGRESS BAR. BUT FIRST XXI DESIGN, BLACK AND YELLOW, FONT, LOGO, ETC.


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Log.d("IM IN DETAIL","WHY SO BLACK");

        dialog = Glovar.loadingDialog(getActivity());
        dialog.show();
        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);
        genreDisplay = "";
        rvCast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CastAdapter(getActivity());
        if (getArguments() != null) {
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvShow();
            if (movie != null) {
                Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
                titleView.setText(movie.getTitle());
                observeShowMViewModel(movie.getId(), "movie");
//                genre.setText(m.getGenre()[0]);
            } else {
                Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getName());
                titleView.setText(tvShow.getName());
                observeShowMViewModel(tvShow.getId(), "tv");

//                genre.setText(tvShow.getGenre()[0]);
            }

        }
    }

    private void observeShowMViewModel(int idMedia, String type) {
        if (type.equalsIgnoreCase("movie")) {
            popularity.setText(movie.getPopularity());
            rating.setText(Double.toString(movie.getVote()));
            description.setText(movie.getDescription());
            Glide.with(getActivity())
                    .load(Constants.IMAGE_URL + movie.getCover())
                    .centerCrop()
                    .into(cover);
            Glide.with(getActivity())
                    .load(Constants.IMAGE_URL + movie.getPoster())
                    .centerCrop()
                    .into(poster);
            viewModel.getGenresM(idMedia).observe(requireActivity(), genres -> {
                if (genres != null) {
                    for (int i = 0; i < genres.size(); i++) {
                        if (i == 0) {
                            genreDisplay = genreDisplay + genres.get(i).getName();
                        } else {
                            genreDisplay = genreDisplay + " | " + genres.get(i).getName();
                        }

                    }

                }
                genreView.setText(genreDisplay);//ok its working
            });
            viewModel.getCastsM(idMedia).observe(requireActivity(), casts -> {
                if (casts != null) {
                    adapter.setCastList(casts);
                    adapter.notifyDataSetChanged();
                    rvCast.setAdapter(adapter);
                }
            });
        } else {
            popularity.setText(tvShow.getPopularity());
            rating.setText(Double.toString(tvShow.getVote()));
            description.setText(tvShow.getDescription());
            Glide.with(getActivity())
                    .load(Constants.IMAGE_URL + tvShow.getCover())
                    .centerCrop()
                    .into(cover);
            Glide.with(getActivity())
                    .load(Constants.IMAGE_URL + tvShow.getPoster())
                    .centerCrop()
                    .into(poster);
            viewModel.getGenresT(idMedia).observe(requireActivity(), genres -> {
                if (genres != null) {
                    for (int i = 0; i < genres.size(); i++) {
                        if (i == 0) {
                            genreDisplay = genreDisplay + genres.get(i).getName();
                        } else {
                            genreDisplay = genreDisplay + " | " + genres.get(i).getName();
                        }

                    }

                }
                genreView.setText(genreDisplay);//ok its working
            });
            viewModel.getCastsT(idMedia).observe(requireActivity(), casts -> {
                if (casts != null) {
                    adapter.setCastList(casts);
                    adapter.notifyDataSetChanged();
                    rvCast.setAdapter(adapter);
                }
            });

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();

            }
        }, 700);

    }


}