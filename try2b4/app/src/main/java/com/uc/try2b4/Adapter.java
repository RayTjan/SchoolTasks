package com.uc.try2b4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    private ArrayList<items> Bitemlist;
    OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }
    public static class Viewholder extends RecyclerView.ViewHolder {
        public ImageView img1, img2;
        TextView text1, text2, text3;


        public Viewholder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            img1 = itemView.findViewById(R.id.card_img1);
            img2 = itemView.findViewById(R.id.card_img2);
            text1 = itemView.findViewById(R.id.card_text1);
            text2 = itemView.findViewById(R.id.card_text2);
            text3 = itemView.findViewById(R.id.card_text3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position!= RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });

        }


    }

    public Adapter(ArrayList<items> itemlist) {
        Bitemlist = itemlist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from((parent.getContext())).inflate(R.layout.card_item, parent, false);
        Viewholder evh = new Viewholder(v,mlistener );
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        items currentItem = Bitemlist.get(position);
        holder.text1.setText(currentItem.getMtext1());
        holder.text2.setText(currentItem.getMtext2());
        holder.text3.setText(currentItem.getMtext3());
        holder.img1.setImageResource(currentItem.getImg1());
        holder.img2.setImageResource(currentItem.getImg2());
    }

    @Override
    public int getItemCount() {
        return Bitemlist.size();
    }



}
