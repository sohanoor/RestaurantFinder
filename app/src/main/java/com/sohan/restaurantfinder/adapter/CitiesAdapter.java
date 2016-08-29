package com.sohan.restaurantfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sohan.restaurantfinder.R;
import com.sohan.restaurantfinder.model.Categories;
import com.sohan.restaurantfinder.model.Cities;

import java.util.ArrayList;

/**
 * Created by SOHAN on 28-Aug-16.
 */
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.MyViewHolder> {

    private ArrayList<Cities> cityList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cityName, countryName;
        public ImageView tag;

        public MyViewHolder(View view) {
            super(view);
            cityName = (TextView) view.findViewById(R.id.cityName);
            countryName = (TextView) view.findViewById(R.id.country_name);
            tag = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public CitiesAdapter(ArrayList<Cities> cityList) {
        this.cityList = cityList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cities_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Cities cities = cityList.get(position);

   //     Log.e("POS ", "" + position);
        holder.cityName.setText(cities.getCityName());
        holder.countryName.setText(cities.getCountryName());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}