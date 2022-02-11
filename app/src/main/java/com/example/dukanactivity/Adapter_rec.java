package com.example.dukanactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_rec extends RecyclerView.Adapter<Adapter_rec.myViewHolder>{

    Context context;
    private List<Rec_ModelClass> mList;


    public Adapter_rec(Context context , List<Rec_ModelClass> mList) {
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
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Rec_ModelClass md = mList.get(position);
        holder.txt_price.setText(md.getText());
        //String str = md.arr[position];
        holder.img_article.setImageResource(md.getImage());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView txt_price;
        ImageView img_article;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_price = itemView.findViewById(R.id.textViewA);
            img_article = itemView.findViewById(R.id.imageViewI);
        }
    }
}

