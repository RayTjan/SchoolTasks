package com.example.trialmvvm.ui.main.tvShow;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trialmvvm.R;
import com.example.trialmvvm.etc.Glovar;
import com.example.trialmvvm.etc.MovieAdapter;
import com.example.trialmvvm.etc.TvShowAdapter;
import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.model.TvShow;
import com.example.trialmvvm.ui.MainActivity;
import com.example.trialmvvm.ui.main.movie.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TvShowFragment extends Fragment {

    @BindView(R.id.recyclerView_TvShowList)
    RecyclerView rvTvShow;

    private TvShowViewModel viewModel;
    ArrayList<TvShow> listTvShow;
    TvShow tvShow;
    TvShowAdapter tvShowAdapter;
    Dialog dialog;

    public void onStart() {
        super.onStart();
        dialog = Glovar.loadingDialog(getActivity());
        dialog.show();
        Log.d("IM IN TVSHOW","WHY SO BLACK");

    }
    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setLogo(R.drawable.ic_baseline_live_tv_black);

        listTvShow = new ArrayList<TvShow>();
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvShowAdapter = new TvShowAdapter(getActivity());
        viewModel = ViewModelProviders.of(getActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);

        tvShow = new TvShow();

        ///////////////////////////////////////////////////////////////////


    }


    private Observer<List<TvShow>> observeViewModel = new Observer<List<TvShow>>() {
        @Override
        public void onChanged(List<TvShow> tvShows) {
            if (tvShows != null){
                tvShowAdapter.setTvShowList(tvShows);
                tvShowAdapter.notifyDataSetChanged();
                rvTvShow.setAdapter(tvShowAdapter);

            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.cancel();
                }
            }, 200);
        }
    };
}