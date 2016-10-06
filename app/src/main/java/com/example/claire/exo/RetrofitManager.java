package com.example.claire.exo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;

/**
 * Created by claire on 23/09/2016.
 * Connect to API
 */

public class RetrofitManager {

    private static final String ENDPOINT = "https://api.handwriting.io";

    private static int timeOutValue = 10;

    private static ILoadImage service = null;

    private static RetrofitManager instance = null;

    private RetrofitManager() {

        String Username = "2176W8NHDNZVNB6V";
        String Password = "V5MDVZ7591W57ZCQ";

        // concatenate username and password with colon for authentication
        String credentials = Username + ":" + Password;
        // create Base64 encodet string
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(timeOutValue, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(timeOutValue, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();

        final RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLog(new AndroidLog("retrofit"))
                .setConverter(new LenientGsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient));

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", basic);
                request.addHeader("Accept", "application/json");
            }
        });
        service = builder
                .build()
                .create(ILoadImage.class);
    }

    public static RetrofitManager getInstance() {

        if (instance == null) {
            return (new RetrofitManager());
        } else
            return instance;
    }

    public static ILoadImage getService() {
        return service;
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}