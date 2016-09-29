package com.example.claire.exo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Retrofit;

public class DisplayMessageActivity extends AppCompatActivity {

    public List<HandWriting> list_font;
    public RetrofitManager tmp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
  //  private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

        tmp = RetrofitManager.getInstance();
        tmp.getService().getHandWritings(new Callback<List<HandWriting>>() {
            @Override
            public void success(List<HandWriting> font, Response response) {
                list_font = font;
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("handwriting_id", list_font.get(1).getId());
                data.put("text", "LLLL");

                tmp.getService().getImage(data, new Callback<Response>() {
                    @Override
                    public void success(Response l, Response reponse) {
                        Toast.makeText(DisplayMessageActivity.this, "BBBBBBBBBBBB", Toast.LENGTH_LONG).show();
                        Toast.makeText(DisplayMessageActivity.this, reponse.getStatus(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(DisplayMessageActivity.this, "AAAAAAAAAAA", Toast.LENGTH_LONG).show();
                        Log.d("DisplayMessageActivity", error.getMessage());
                        Toast.makeText(DisplayMessageActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                Toast.makeText(DisplayMessageActivity.this, "KKKKKKKK", Toast.LENGTH_LONG).show();
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("DisplayMessageActivity", error.getMessage());
                Toast.makeText(DisplayMessageActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void DisplayFont(List<HandWriting> font) {
        for (int i = 0; i < font.size(); ++i)
            Toast.makeText(this, font.get(i).getId(), Toast.LENGTH_LONG).show();
    }

}