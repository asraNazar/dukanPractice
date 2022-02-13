package com.example.dukanactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private List<ImagesResponse> mList = new ArrayList<>();
    private ImageView img_backArrow;
//    Adapter_rec adapter_rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec_view);
        Call<List<ImagesResponse>> imageResponse = ApiClient.getInterface().getAllImages();
        imageResponse.enqueue(new Callback<List<ImagesResponse>>() {
            @Override
            public void onResponse(Call<List<ImagesResponse>> call, Response<List<ImagesResponse>> response) {
                if(response.isSuccessful()){




                }
                mList = response.body();
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
                Adapter_rec adp = new Adapter_rec(MainActivity.this,mList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adp);

            }

            @Override
            public void onFailure(Call<List<ImagesResponse>> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });

//        getAllImages();




        Fragment fr = HomeFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fr,"HomeFragment");
        ft.commit();





    }


    private void navigate(int position) {
        switch (position) {
            case 0:

                Fragment fr = HomeFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, fr).addToBackStack(null).commit();
                break;

            case 1:

                Fragment fragment_one = CartFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, fragment_one)
                        .addToBackStack(null).commit();
                break;

            case 2:

                Fragment fragment_two = AccountFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, fragment_two)
                        .commit();
                break;
        }
    }


//            void addList() {
//        Rec_ModelClass amc = new Rec_ModelClass("Account Holder", R.drawable.ic_launcher_background);
//        mList.add(amc);
//        amc = new Rec_ModelClass("Debit Card", R.drawable.ic_launcher_background);
//        mList.add(amc);
//        amc = new Rec_ModelClass("Credit Card", R.drawable.ic_launcher_background);
//        mList.add(amc);
//        amc = new Rec_ModelClass("Existing Wallet", R.drawable.ic_launcher_background);
//        mList.add(amc);
//
//
//    }

    public void getAllImages(){
//        Call<List<ImagesResponse>> imageResponse = ApiClient.getInterface().getAllImages();
//        imageResponse.enqueue(new Callback<List<ImagesResponse>>() {
//            @Override
//            public void onResponse(Call<List<ImagesResponse>> call, Response<List<ImagesResponse>> response) {
//                if(response.isSuccessful()){
//
//
//
//
//                }
//                mList = response.body();
//                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
//                Adapter_rec adp = new Adapter_rec(MainActivity.this,mList);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(adp);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<ImagesResponse>> call, Throwable t) {
//
//                String message = t.getLocalizedMessage();
//                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
//            }
//        });
   }


}