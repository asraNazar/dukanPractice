package com.example.dukanactivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public class DiffUtilsClass extends DiffUtil.Callback {

    private List<CartForRoom> cartsOld = new ArrayList<>();
    private List<CartForRoom> cartsNew = new ArrayList<>();

    public DiffUtilsClass(List<CartForRoom> cartsOld, List<CartForRoom> cartsNew) {
        this.cartsOld = cartsOld;
        this.cartsNew = cartsNew;
    }

    @Override
    public int getOldListSize() {

        return cartsOld !=null ? cartsOld.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return cartsNew !=null ? cartsNew.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//       int result = cartsNew.get(newItemPosition) == (cartsOld.get(oldItemPosition));
       if(cartsNew.get(newItemPosition) == (cartsOld.get(oldItemPosition)))
           return true;

       return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        CartForRoom crtNew = cartsNew.get(newItemPosition);
        CartForRoom crtOld = cartsOld.get(oldItemPosition);

        Bundle bundle = new Bundle();
        if (!(cartsNew.get(newItemPosition) ==cartsOld.get(oldItemPosition))){

        }
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
