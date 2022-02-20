
package com.example.dukanactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;


import java.util.List;

class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {
    private List<CartForRoom> carts;
    private Context context;
    private CartDao cartDao;
    SharedPreferences sr;

    public CartProductAdapter(List<CartForRoom> carts, Context context) {
        this.carts = carts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position
    ) {

        final CartForRoom cart = carts.get(position);





        Glide.with(context).load(carts.get(position).imageid).into(holder.img_article_cart);
        //Glide.with(context).load(carts.get(position)).into(holder.img_article_cart);
        holder.txt_price_cart.setText("Rs. " + cart.getPrice());
        holder.quantity.setText("Quantity: " + getItemCount());
        holder.btn_remove_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartDatabase.getInstance(context).cartDao().deleteCartItems(cart);
                CartDatabase.getInstance(context).cartDao().updateCart(cart);

                // updateRec_Cart(carts);
                notifyItemRemoved(position);
                holder.quantity.setText("Quantity: " + getItemCount());
                //CartProductAdapter.this.notify();
               // notifyDataSetChanged();




//
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.updateCount();

                Intent intent = new Intent("mymsg");

                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


            }
        });

        //holder.re notifyDataSetChanged();


    }

    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position,
                                 List<Object> payloads) {

        final CartForRoom cart = carts.get(position);
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);

        }
        else {
            Bundle bundle = (Bundle) payloads.get(0);
            for (String key : bundle.keySet()) {
                if (key.equals("image") && key.equals("price")) {
                    Glide.with(context).load(carts.get(position)).into(holder.img_article_cart);
                    holder.txt_price_cart.setText("Rs. " + cart.getPrice());
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_article_cart;
        private TextView txt_price_cart, quantity;
        private Button btn_remove_cart;
        RecyclerView rec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_article_cart = itemView.findViewById(R.id.img_item_name);
            txt_price_cart = itemView.findViewById(R.id.txt_item_price);
            quantity = itemView.findViewById(R.id.txt_item_quantity);
            btn_remove_cart = itemView.findViewById(R.id.btn_remove);

        }
    }

    public void updateRec_Cart(List<CartForRoom> cartsRec) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilsClass(carts, cartsRec));
        diffResult.dispatchUpdatesTo(this);

        carts.clear();
        carts.addAll(cartsRec);
    }

}
