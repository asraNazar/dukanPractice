package com.example.dukanactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_rec extends RecyclerView.Adapter<Adapter_rec.myViewHolder>{

    Context context;
    private List<ImagesResponse> mList;



    public Adapter_rec(Context context , List<ImagesResponse> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view, parent, false);

        TextView txt_price = view.findViewById(R.id.textViewA);
        ImageView img_article = view.findViewById(R.id.imageViewI);
        TextView txt_description = view.findViewById(R.id.txt_des);

        txt_price.setText(mList.get(viewType).getPrice());
        txt_description.setText(mList.get(viewType).getTitle());

        GlideApp.with(context).load(mList.get(viewType).getUrl()).into(img_article);

        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {


        ImagesResponse md = mList.get(position);
        holder.txt_price.setText(md.getPrice());
        holder.txt_description.setText(md.getTitle());
        holder.img_article.setImageResource(md.getUrl());



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView txt_price;
        ImageView img_article;
        TextView txt_description;
        CardView cardView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_price = itemView.findViewById(R.id.textViewA);
            img_article = itemView.findViewById(R.id.imageViewI);
            txt_description = itemView.findViewById(R.id.txt_des);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}

