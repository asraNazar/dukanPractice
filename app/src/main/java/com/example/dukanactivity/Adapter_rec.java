package com.example.dukanactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_rec extends RecyclerView.Adapter<Adapter_rec.myViewHolder> {

    Context context;
    private List<ImagesResponse> mList;


    public Adapter_rec(Context context, List<ImagesResponse> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view, parent, false);


        return new myViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {


        ImagesResponse md = mList.get(position);
        holder.txt_price.setText("Rs. " + md.getPrice());


        holder.txt_description.setText(md.getTitle());

        Glide.with(context).load(mList.get(position).getUrl()).into(holder.img_article);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImagesResponse response = mList.get(position);


                Bundle bundle = new Bundle();
                bundle.putSerializable("product",response);

                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                ItemDescription it = new ItemDescription();
                it.setArguments(bundle);
                appCompatActivity.getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, it)
                        .addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView txt_price;
        ImageView img_article;
        TextView txt_description;
        LinearLayout cardView;
        TextView txt_price_cart;
        ImageView img_article_cart;
        TextView quantity;
        Button btn_remove_cart;




        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_price = itemView.findViewById(R.id.textViewA);
            img_article = itemView.findViewById(R.id.imageViewI);
            txt_description = itemView.findViewById(R.id.txt_des);
            cardView = itemView.findViewById(R.id.card_view);

            //Cart View
            img_article_cart = itemView.findViewById(R.id.img_item_name);
            txt_price_cart = itemView.findViewById(R.id.txt_item_price);
            quantity = itemView.findViewById(R.id.txt_item_quantity);
            btn_remove_cart = itemView.findViewById(R.id.btn_remove);


        }
    }
}

