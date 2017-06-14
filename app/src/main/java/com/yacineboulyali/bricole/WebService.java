package com.yacineboulyali.bricole;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yacine on 08/06/2017.
 */

public class WebService {

    public static String GET_BY_ID = "http://10.0.2.2/bricole/api/ouvrier/";
    public static String type="?transform=1";

    Context context;


    public void getDate(int id) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, GET_BY_ID+id+type, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject obj = response.getJSONObject("ouvrier");
                    JSONArray columen = obj.getJSONArray("records");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(request);

    }


}
