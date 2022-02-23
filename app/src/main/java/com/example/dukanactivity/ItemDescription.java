package com.example.dukanactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dukanactivity.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDescription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDescription extends Fragment {

    private List<ImagesResponse> mList = new ArrayList<>();
    private LinearLayout linearLayout;
    private ImageView img;
    private TextView tv_description;
    private Button addToCart;
    private TextView price;
    String my_count = "1";
    CartDao cartDao;

    public ItemDescription() {
        // Required empty public constructor
    }


    public static ItemDescription newInstance() {
        ItemDescription fragment = new ItemDescription();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_description, container, false);
        img = view.findViewById(R.id.img_1);
        tv_description = view.findViewById(R.id.txt_des2);
        addToCart = view.findViewById(R.id.btn_add_to_cart);
        price = view.findViewById(R.id.txt_price);

        Bundle bundle = getArguments();
        ImagesResponse model = (ImagesResponse) bundle.getSerializable("product");

        tv_description.setText(model.getTitle());
        price.setText(model.getPrice());
        Glide.with(getActivity()).load(model.getUrl()).into(img);


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartForRoom obj = CartDatabase.getInstance(getActivity()).cartDao().getData(model.getId());

                if (obj == null) {
                    obj = new CartForRoom();
                    obj.setImageid(model.getUrl());
                    obj.setPrice(model.getPrice());
                    obj.setId(model.getId());
                    obj.setCount(1);

                    CartDatabase.getInstance(getActivity()).cartDao().addToCart(obj);
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.updateCount();


//                    Log.d("addToCart_2", my_count + "");
                }else{
                    int count = obj.getCount();
                    obj.setCount(++count);
                    CartDatabase.getInstance(getActivity()).cartDao().updateCart(obj);
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.updateCount();
                }

            }
        });


        return view;
    }
}