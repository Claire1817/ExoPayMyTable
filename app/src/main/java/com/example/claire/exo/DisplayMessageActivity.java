package com.example.claire.exo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.JsonObject;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit2.Retrofit;

public class DisplayMessageActivity extends AppCompatActivity {

    private List<HandWriting> listFont;

    private RetrofitManager tmp;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        tmp = RetrofitManager.getInstance();
        tmp.getService().getHandWritings(new Callback<List<HandWriting>>() {
            @Override
            public void success(List<HandWriting> font, final Response response) {
                loadPrintImage(font);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("DisplayMessageActivity", error.getMessage());
            }
        });
    }

    public void loadPrintImage(List<HandWriting> font) {

        listFont = font;
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("handwriting_id", listFont.get(1).getId());
        data.put("text", message);
        data.put("handwriting_size", "200px");

        tmp.getService().getImage(data, new Callback<Response>() {
            @Override
            public void success(Response response, Response reponseApi) {
                putInImage(response);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("DisplayMessageActivity", error.getMessage());
            }
        });
    }

    public void putInImage(Response response) {

        TypedByteArray ret = (TypedByteArray)response.getBody();
        Bitmap decodeByte = BitmapFactory.decodeByteArray(ret.getBytes(), 0, ret.getBytes().length);
        ImageView image = (ImageView)findViewById(R.id.image_text);
        image.setImageBitmap(decodeByte);
    }
}

