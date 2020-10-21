package com.example.trialmvvm.etc;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trialmvvm.R;
import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.model.TvShow;
import com.example.trialmvvm.ui.main.movie.MovieFragmentDirections;
import com.example.trialmvvm.ui.main.tvShow.TvShowFragmentDirections;
import com.example.trialmvvm.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder> {

    private Context context;
    private List<TvShow> tvShowList;
    AlphaAnimation klik = new AlphaAnimation(1F,0.6F);
    private List<TvShow> getTvShowList(){
        return tvShowList;
    }

    public void setTvShowList(List<TvShow> movieList){
        this.tvShowList = movieList;
    }

    public TvShowAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movientvshow_adapter, parent, false);
        return new TvShowAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.CardViewViewHolder holder, int position) {
        final TvShow tvShow = getTvShowList().get(position);
        holder.name.setText(tvShow.getName());
        holder.first_air_date.setText(tvShow.getFirst_air_date());
        holder.popularity.setText(tvShow.getPopularity());
        holder.rating.setText(Double.toString(tvShow.getVote()));
        Glide.with(context)
                .load(Constants.IMAGE_URL + tvShow.getPoster())
                .centerCrop()
                .into(holder.cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(klik);
                NavDirections action = TvShowFragmentDirections.actionTvShowtoDetail(null,tvShow);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTvShowList().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView popularity, name, first_air_date,rating;
        ImageView cover;

        CardViewViewHolder(View itemView) {
            super(itemView);
            popularity = itemView.findViewById(R.id.textView_adapterPopularity);
            name = itemView.findViewById(R.id.textView_adapterTitle);
            first_air_date= itemView.findViewById(R.id.textView_adapterReleaseDate);
            cover = itemView.findViewById(R.id.imageView_adapterCover);
            rating = itemView.findViewById(R.id.textView_adapterRating);

        }
    }
}