package com.sohan.restaurantfinder.JSONParser;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sohan.restaurantfinder.Data.Config;
import com.sohan.restaurantfinder.app.AppController;
import com.sohan.restaurantfinder.fragment.CategoryFragment;
import com.sohan.restaurantfinder.fragment.CitiesFragment;
import com.sohan.restaurantfinder.model.Categories;
import com.sohan.restaurantfinder.model.Cities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SOHAN on 28-Aug-16.
 */
public class CitiesParser {

    public static ArrayList<Cities> CitiesListModel = new ArrayList<Cities>();

    public static void getJSONData() {


        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.CITIES_GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("CITIES ", "" + response.toString());

                try {

                    JSONObject responseJSON = new JSONObject(response);
                    JSONArray category = responseJSON.getJSONArray("location_suggestions");
                    for (int i = 0; i < category.length(); i++) {
                        JSONObject item = category.getJSONObject(i);
                        //    JSONObject categories = item.getJSONObject("categories");

                        String city_name = item.getString("name");
                        String country_name = item.getString("country_name");

                        Log.e("id", "" + city_name);
                        Log.e("name:", country_name);

                        Cities citiesModel = new Cities();
                        citiesModel.setCityName(city_name);
                        citiesModel.setCountryName(country_name);
                        CitiesListModel.add(citiesModel);
                        CitiesFragment.citiesAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("", "Login Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("user-key", Config.USER_KEY);
                // headers.put("q", "delhi");
                // headers.put("count", "10");
                return headers;
            }

           /* public Map<String, String> getMap() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
               // map.put("user-key", Config.USER_KEY);
                map.put("q", "delhi");
                map.put("count", "10");
                return map;
            }*/
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }
}
