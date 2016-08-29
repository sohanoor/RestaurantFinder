package com.sohan.restaurantfinder.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sohan.restaurantfinder.R;
import com.sohan.restaurantfinder.model.Categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SOHAN on 28-Aug-16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private ArrayList<Categories> categoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name;
        public ImageView tag;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            tag = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public CategoryAdapter(ArrayList<Categories> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Categories category = categoryList.get(position);

        Log.e("POS ", ""+ position);
        holder.id.setText(category.getId()+"");
        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}