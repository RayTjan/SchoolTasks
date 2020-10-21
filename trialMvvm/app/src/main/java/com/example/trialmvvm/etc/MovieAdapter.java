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
import com.example.trialmvvm.ui.main.movie.MovieFragmentDirections;
import com.example.trialmvvm.util.Constants;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder> {

    private Context context;
    private List<Movie> movieList;
    AlphaAnimation klik = new AlphaAnimation(1F,0.6F);
    private List<Movie> getMovieList(){
        return movieList;
    }

    public void setMovieList(List<Movie> movieList){
         this.movieList = movieList;
    }

    public MovieAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movientvshow_adapter, parent, false);
        return new MovieAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.CardViewViewHolder holder, int position) {
        final Movie movie = getMovieList().get(position);
        holder.title.setText(movie.getTitle());
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.popularity.setText(movie.getPopularity());
        holder.rating.setText(Double.toString(movie.getVote()));
        Glide.with(context)
                .load(Constants.IMAGE_URL + movie.getCover())
                .centerCrop()
                .into(holder.cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(klik);
                NavDirections action = MovieFragmentDirections.actionMovietoDetail(movie,null);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMovieList().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView popularity, title, releaseDate,rating;
        ImageView cover;

        CardViewViewHolder(View itemView) {
            super(itemView);
            popularity = itemView.findViewById(R.id.textView_adapterPopularity);
            title = itemView.findViewById(R.id.textView_adapterTitle);
            releaseDate= itemView.findViewById(R.id.textView_adapterReleaseDate);
            cover = itemView.findViewById(R.id.imageView_adapterCover);
            rating = itemView.findViewById(R.id.textView_adapterRating);
        }
    }
}