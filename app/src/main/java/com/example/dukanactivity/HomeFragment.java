package com.example.dukanactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    private List<ImagesResponse> mList = new ArrayList<>();
    private List<CartForRoom> carts = new ArrayList<>();

    int cacheSize = 10 * 1024 * 1024;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    private Retrofit getRetrofit() {


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)

                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fakestoreapi.com/").addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
                .build();

        return retrofit;

    }

    public ApiInterface getInterface() {
        ApiInterface apiInterface = getRetrofit().create(ApiInterface.class);
        return apiInterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = view.findViewById(R.id.rec_view);

        retrieveProduct();

//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
//        Adapter_rec adp = new Adapter_rec(getContext(), mList);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adp);
//        saveProducts(mList);


        Call<List<ImagesResponse>> imageResponse = getInterface().getAllImages();


        //Adapter_rec adapter_rec = new Adapter_rec(getContext(),mList);


        imageResponse.enqueue(new Callback<List<ImagesResponse>>() {
            @Override
            public void onResponse(Call<List<ImagesResponse>> call, Response<List<ImagesResponse>> response) {
                if (response.isSuccessful()) {




                mList = response.body();
                saveProducts(mList);


                setUpData(mList);

                }
            }

            @Override
            public void onFailure(Call<List<ImagesResponse>> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void setUpData(List<ImagesResponse> data) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        Adapter_rec adp = new Adapter_rec(getContext(), data);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adp);

    }

    public void saveProducts(List<ImagesResponse> mList) {


        CartDatabase.getInstance(getActivity()).cartDao().emptyCart();
        CartDatabase.getInstance(getActivity()).cartDao().addToRoom(mList);
    }

    public void retrieveProduct() {


        List<ImagesResponse> items = CartDatabase.getInstance(getActivity()).cartDao().getAllItems();
        setUpData(items);
    }
}


