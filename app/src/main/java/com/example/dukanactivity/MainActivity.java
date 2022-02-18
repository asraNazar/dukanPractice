package com.example.dukanactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dukanactivity.databinding.ActivityMainBinding;
import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    private List<ImagesResponse> mList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
//        binding.bottomNavView.getOrCreateBadge(R.id.cart_icon).setNumber(5);


        binding.bottomNavView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.home_icon:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.cart_icon:
                    replaceFragment(new CartFragment());
                    CartForRoom cart=new CartForRoom();
                    cart.setId(item.getItemId());
//                    if (MainActivity.myDatabase.cartDao().isAddToCart(item.getItemId())!=1){

                    //}
                        break;

                case R.id.account_icon:
                    replaceFragment(new AccountFragment());
                    break;
            }

            return true;
        });
    }

        private void replaceFragment(Fragment fragment) {

            FragmentManager fr = getSupportFragmentManager();
            FragmentTransaction ft = fr.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }

        public void updateCount(){
        //badge for displaying cart item number
            BadgeDrawable badgeDrawable = binding.bottomNavView.getOrCreateBadge(R.id.cart_icon);
            badgeDrawable.setNumber(CartDatabase.getInstance(this).cartDao().countCart());

        }






}




