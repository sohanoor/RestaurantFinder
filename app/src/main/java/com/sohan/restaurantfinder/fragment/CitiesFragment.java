package com.sohan.restaurantfinder.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohan.restaurantfinder.JSONParser.CitiesParser;
import com.sohan.restaurantfinder.R;
import com.sohan.restaurantfinder.adapter.CitiesAdapter;


public class CitiesFragment extends Fragment {

    private RecyclerView recyclerView;
    public static CitiesAdapter citiesAdapter;

    public CitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        CitiesParser.getJSONData();
        citiesAdapter = new CitiesAdapter(CitiesParser.CitiesListModel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(citiesAdapter);

        // Inflate the layout for this fragment
        return rootView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}