package com.sohan.restaurantfinder.JSONParser;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sohan.restaurantfinder.Data.Config;
import com.sohan.restaurantfinder.app.AppController;
import com.sohan.restaurantfinder.fragment.CategoryFragment;
import com.sohan.restaurantfinder.model.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SOHAN on 28-Aug-16.
 */
public class CategoriesParser {

    public static ArrayList<Categories> CategoryListModel = new ArrayList<Categories>();

 //   ProgressBar progressBar = new ProgressBar();

    public static void getJSONData() {

     //   progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.CATEGORIES_GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Main ", "Login Response: " + response.toString());

                try {

                    JSONObject responseJSON = new JSONObject(response);
                    JSONArray category = responseJSON.getJSONArray("categories");
                    for (int i = 0; i < category.length(); i++) {
                        JSONObject item = category.getJSONObject(i);

                        JSONObject categories = item.getJSONObject("categories");

                        int id = categories.getInt("id");
                        String name = categories.getString("name");

                        Log.e("id", "" + id);
                        Log.e("name:", name);

                        Categories categoriesModel = new Categories();
                        categoriesModel.setId(id);
                        categoriesModel.setName(name);
                        CategoryListModel.add(categoriesModel);
                        CategoryFragment.categoryAdapter.notifyDataSetChanged();
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

                return headers;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }
}
