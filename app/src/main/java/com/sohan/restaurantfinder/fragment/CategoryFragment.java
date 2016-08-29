package com.sohan.restaurantfinder.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.sohan.restaurantfinder.JSONParser.CategoriesParser;
import com.sohan.restaurantfinder.R;
import com.sohan.restaurantfinder.adapter.CategoryAdapter;
import com.sohan.restaurantfinder.app.InternetConnectionDetector;

import java.util.ArrayList;


public class CategoryFragment extends Fragment{


    private RecyclerView recyclerView;
    public static CategoryAdapter categoryAdapter;
    InternetConnectionDetector internetDetector;
  //  ProgressBar progressBar;

    public CategoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        internetDetector = new InternetConnectionDetector(getActivity());


        if (!internetDetector.isConnectedToInternet()) {
            showPopUp();
        } else {
            CategoriesParser.getJSONData();
        }



        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
   //     progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
      //  CategoriesParser.getJSONData();
        categoryAdapter = new CategoryAdapter(CategoriesParser.CategoryListModel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void showPopUp() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("No Internet :(");
        alertDialog.setMessage("Network connection is not available. Choose either of the options to get connected to a network.");
        alertDialog.setPositiveButton("WiFi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    internetDetector.connectToWifi(getActivity(), true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Wifi Turned On", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNeutralButton("Mobile Data",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Process Terminated!", Toast.LENGTH_SHORT).show();

                    }
                });
        alertDialog.show();
    }
}


