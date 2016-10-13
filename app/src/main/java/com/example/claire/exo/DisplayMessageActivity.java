package com.example.claire.exo;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.HashMap;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class DisplayMessageActivity extends AppCompatActivity {

    private List<HandWriting> listFont;

    private RetrofitManager tmp;

    private String message;

    private String sizeSelected;

    private String colorSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
             getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        sizeSelected = intent.getStringExtra(MainActivity.SIZE_SELECTED);
        colorSelected = intent.getStringExtra(MainActivity.COLOR_SELECTED);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadPrintImage(List<HandWriting> font) {

        listFont = font;
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("handwriting_id", listFont.get(1).getId());
        data.put("text", message);
        data.put("handwriting_size", sizeSelected);
        data.put("handwriting_color",colorSelected);
        data.put("width", "auto");
        data.put("height", "auto");


        tmp.getService().getImage(data, new Callback<Response>() {
            @Override
            public void success(Response response, Response reponseApi) {
                putInImage(response);
            }
            @Override
            public void failure(RetrofitError error) {
                ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar1);
                spinner.setVisibility(View.GONE);
                Toast.makeText(DisplayMessageActivity.this, "Character no accepted", Toast.LENGTH_LONG).show();
                Log.d("DisplayMessageActivity", error.getMessage());
            }
        });
    }

    public void putInImage(Response response) {

        TypedByteArray ret = (TypedByteArray) response.getBody();
        Bitmap decodeByte = BitmapFactory.decodeByteArray(ret.getBytes(), 0, ret.getBytes().length);
        ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        ImageView image = (ImageView) findViewById(R.id.image_text);
        image.setImageBitmap(decodeByte);
    }
}

