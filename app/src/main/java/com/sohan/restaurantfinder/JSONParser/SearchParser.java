package com.sohan.restaurantfinder.JSONParser;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sohan.restaurantfinder.Data.Config;
import com.sohan.restaurantfinder.app.AppController;
import com.sohan.restaurantfinder.fragment.CitiesFragment;
import com.sohan.restaurantfinder.fragment.SearchFragment;
import com.sohan.restaurantfinder.model.Cities;
import com.sohan.restaurantfinder.model.Search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SOHAN on 28-Aug-16.
 */
public class SearchParser {

    public static ArrayList<Search> SearchListModel = new ArrayList<Search>();

    public static void getJSONData() {


        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.SEARCH_GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("CITIES ", "" + response.toString());

                try {

                    JSONObject responseJSON = new JSONObject(response);
                    JSONArray category = responseJSON.getJSONArray("restaurants");
                    for (int i = 0; i < category.length(); i++) {
                        JSONObject item = category.getJSONObject(i);

                        JSONObject restaurant = item.getJSONObject("restaurant");

                        String resName = restaurant.getString("name");

                        JSONObject location = restaurant.getJSONObject("location");

                        String address = location.getString("address");
                        String city = location.getString("city");

                        Log.e("resName", "" + resName);
                        Log.e("address", "" + address);
                        Log.e("city:", city);

                        Search searchModel = new Search();
                        searchModel.setResName(resName);
                        searchModel.setAddress(address);
                        searchModel.setCity(city);
                        SearchListModel.add(searchModel);
                        SearchFragment.searchAdapter.notifyDataSetChanged();
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

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("q", "delhi");
              //  params.put("param2", num2);
                return params;
            };
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }
}
