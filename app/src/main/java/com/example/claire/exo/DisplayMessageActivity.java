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

    public List<HandWriting> list_font;
    public RetrofitManager tmp;
    public String _message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        _message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        tmp = RetrofitManager.getInstance();
        tmp.getService().getHandWritings(new Callback<List<HandWriting>>() {
            @Override
            public void success(List<HandWriting> font, final Response response) {
                LoadPrintImage(font);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("DisplayMessageActivity", error.getMessage());
            }
        });
    }

    public void LoadPrintImage(List<HandWriting> font) {
        list_font = font;
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("handwriting_id", list_font.get(1).getId());
        data.put("text", _message);
        data.put("handwriting_size", "200px");

        tmp.getService().getImage(data, new Callback<Response>() {
            @Override
            public void success(Response response, Response reponse) {
                TypedByteArray ret = (TypedByteArray)response.getBody();
                Bitmap decodeByte = BitmapFactory.decodeByteArray(ret.getBytes(), 0, ret.getBytes().length);
                ImageView image = (ImageView)findViewById(R.id.image_text);
                image.setImageBitmap(decodeByte);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("DisplayMessageActivity", error.getMessage());
            }
        });
    }
}

