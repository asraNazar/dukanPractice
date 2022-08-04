package com.example.dukanactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    private List<ImagesResponse> mList = new ArrayList<>();
    RecyclerView rv;
    TextView txt_quantity;


    CartProductAdapter cartProductAdapter;
    List<CartForRoom> carts;



    private ImageView img;

    private Button addToCart;
    private TextView price;


    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rv = view.findViewById(R.id.rec_cart2);
        getCartData();

        return view;
    }


    private void getCartData() {

        //cartProductAdapter.
        carts = CartDatabase.getInstance(getActivity()).cartDao().getCartItems();

        cartProductAdapter = new CartProductAdapter(carts, getActivity(), new CartProductAdapter.OnOptionSelectedListener() {
            @Override
            public void onOptionSelected(int position) {
                navigate(position);

            }
        });

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(cartProductAdapter);


        if (carts.isEmpty()) {
            BlankFragment fr = new BlankFragment();

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fr)
                    .commit();

        }


    }

    private void navigate(int position) {
        cartProductAdapter.setSelectedPosition(position);
        CartForRoom cartForRoom = carts.get(position);

        CartDatabase.getInstance(getActivity()).cartDao().deleteCartItems(cartForRoom);

        carts.remove(position);

        if (carts.isEmpty()) {
            BlankFragment fr = new BlankFragment();

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fr)
                    .commit();

        }
//
    }


}
