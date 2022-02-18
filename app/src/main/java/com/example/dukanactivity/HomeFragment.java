package com.example.dukanactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

  RecyclerView recyclerView;
    private List<ImagesResponse> mList = new ArrayList<>();
   
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rec_view);
        Call<List<ImagesResponse>> imageResponse = ApiClient.getInterface().getAllImages();
        imageResponse.enqueue(new Callback<List<ImagesResponse>>() {
            @Override
            public void onResponse(Call<List<ImagesResponse>> call, Response<List<ImagesResponse>> response) {
                if(response.isSuccessful()){

                }
                mList = response.body();
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                Adapter_rec adp = new Adapter_rec(getContext(),mList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adp);



            }

            @Override
            public void onFailure(Call<List<ImagesResponse>> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }




}