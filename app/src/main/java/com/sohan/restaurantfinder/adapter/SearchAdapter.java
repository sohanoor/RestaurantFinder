package com.sohan.restaurantfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sohan.restaurantfinder.R;
import com.sohan.restaurantfinder.model.Cities;
import com.sohan.restaurantfinder.model.Search;

import java.util.ArrayList;

/**
 * Created by SOHAN on 28-Aug-16.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private ArrayList<Search> searchList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView resName, address, city;

        public MyViewHolder(View view) {
            super(view);
            resName = (TextView) view.findViewById(R.id.res_name);
            address = (TextView) view.findViewById(R.id.res_address);
            city = (TextView) view.findViewById(R.id.res_city);
        }
    }


    public SearchAdapter(ArrayList<Search> searchList) {
        this.searchList = searchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Search search = searchList.get(position);

   //     Log.e("POS ", "" + position);
        holder.resName.setText(search.getResName());
        holder.address.setText(search.getAddress());
        holder.city.setText(search.getCity());
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}