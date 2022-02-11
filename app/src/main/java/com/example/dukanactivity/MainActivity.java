package com.example.dukanactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private List<Rec_ModelClass> mList = new ArrayList<>();
    private ImageView img_backArrow;
    Adapter_rec adapter_rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec_view);
        addList();

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        adapter_rec = new Adapter_rec(this,mList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_rec);

        Fragment fr = HomeFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fr,"main_frag");
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
                //holder.m1.setBackgroundColor(Color.parseColor("#FF0000"));
                Fragment fragment_two = AccountFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, fragment_two)
                        .commit();
                break;
        }
    }


            void addList() {
        Rec_ModelClass amc = new Rec_ModelClass("Account Holder", R.drawable.ic_launcher_background);
        mList.add(amc);
        amc = new Rec_ModelClass("Debit Card", R.drawable.ic_launcher_background);
        mList.add(amc);
        amc = new Rec_ModelClass("Credit Card", R.drawable.ic_launcher_background);
        mList.add(amc);
        amc = new Rec_ModelClass("Existing Wallet", R.drawable.ic_launcher_background);
        mList.add(amc);


    }

}