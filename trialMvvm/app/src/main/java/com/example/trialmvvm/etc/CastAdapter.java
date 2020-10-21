package com.example.trialmvvm.etc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trialmvvm.R;
import com.example.trialmvvm.model.Cast;
import com.example.trialmvvm.model.Movie;
import com.example.trialmvvm.util.Constants;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CardViewViewHolder>{

    private Context context;
    private List<Cast> castList;
    AlphaAnimation klik = new AlphaAnimation(1F,0.6F);

    public CastAdapter(Context context){
        this.context = context;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }

    public List<Cast> getCastList(){
        return castList;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cast_adapter, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Cast cast = castList.get(position);
        Glide.with(context)
                .load(Constants.IMAGE_URL + cast.getImg_url())
                .centerCrop()
                .into(holder.pic);
        holder.name.setText(cast.getName());
        holder.role.setText(cast.getRole());
    }

    @Override
    public int getItemCount() {
        return getCastList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView name, role;
        ImageView pic;

        CardViewViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_castName);
            role = itemView.findViewById(R.id.textView_castPlay);
            pic= itemView.findViewById(R.id.imageView_CastPic);
        }
    }
}
