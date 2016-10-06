package com.example.claire.exo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by claire on 23/09/2016.
 * Request API
 */

public interface ILoadImage {

    @GET("/handwritings")
    void getHandWritings(Callback<List<HandWriting>> list);

    @GET("/render/png")
    void getImage(
            @QueryMap HashMap<String, String> options, Callback<Response> response);
}