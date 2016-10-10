package com.example.claire.exo;

import android.content.Intent;
import android.icu.text.Normalizer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "message";

    private Spinner spinnerSize;

    private Spinner spinnerColor;

    public final static String SIZE_SELECTED = "30px";

    public final static String COLOR_SELECTED = "#000000";

    public final static String WIDTH_SIZE = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerSize = (Spinner)findViewById(R.id.dispoSize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);

        spinnerColor = (Spinner)findViewById(R.id.dispoColor);
        ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(this,
                R.array.Color, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
    }

    // When user click on button

    public void sendMessage(View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay();
        int pixel;
        View v = findViewById(R.id.main_activity_width);
        pixel = v.getWidth();
        intent.putExtra(WIDTH_SIZE, pixel+"px");

        message = cleanSting(message);
        if (checkString(message) == 0)
        {
            intent.putExtra(EXTRA_MESSAGE, message);
            intent.putExtra(SIZE_SELECTED, spinnerSize.getSelectedItem().toString());
            intent.putExtra(COLOR_SELECTED, convertStringToRGB());

            startActivity(intent);
        }
    }

    private int checkString(String message)
    {
        if (message.isEmpty() || message.matches("[^a-zA-Z0-9_;-]")) {
            Toast.makeText(getApplicationContext(), "Message is empty or contains characters no accepted", Toast.LENGTH_LONG).show();
            return 1;
        }
        else
            return 0;
    }

    private static String cleanSting(String s)
        {
        final String accents = "ÀÁÂÃÄÅàáâãäåÈÉÊËèéêë";
        final String letters = "AAAAAAaaaaaaEEEEeeee";

        StringBuffer buffer = null;
        for(int i = s.length()-1 ; i >= 0; i--) {
            int index = accents.indexOf(s.charAt(i));
            if (index >= 0) {
                if (buffer == null) {
                    buffer = new StringBuffer(s);
                }
                buffer.setCharAt(i, letters.charAt(index));
            }
        }
        return buffer==null ? s : buffer.toString();
    }

    private String convertStringToRGB()
    {

        Map<String, String> corresColor = new HashMap<String, String>();

        corresColor.put("Black", "#000000");
        corresColor.put("Blue", "#3399FF");
        corresColor.put("Red", "#FF0033");
        corresColor.put("Green", "#33FF66");
        corresColor.put("Pink", "#FF33CC");
        return corresColor.get(spinnerColor.getSelectedItem().toString());
    }
}

