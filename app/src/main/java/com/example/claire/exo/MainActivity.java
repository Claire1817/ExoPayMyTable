package com.example.claire.exo;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.larswerkman.holocolorpicker.ColorPicker;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity  implements ColorPicker.OnColorChangedListener {

    public final static String EXTRA_MESSAGE = "message";

    private Spinner spinnerSize;

    public final static String SIZE_SELECTED = "30px";

    public final static String COLOR_SELECTED = "#000000";

    public static ColorPicker picker;

    @Override
    public void onColorChanged(int color) {
        //gives the color when it's changed.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerSize = (Spinner) findViewById(R.id.dispoSize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);

        picker = (ColorPicker)findViewById(R.id.picker);
        picker.setOldCenterColor(picker.getColor());
        picker.getColor();
        picker.setOnColorChangedListener(MainActivity.this);
        picker.setShowOldCenterColor(false);
    }

    // When user click on button

    public void sendMessage(View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        message = cleanSting(message);
        if (checkString(message) == 0)
        {
            intent.putExtra(EXTRA_MESSAGE, message);
            intent.putExtra(SIZE_SELECTED, spinnerSize.getSelectedItem().toString());
            intent.putExtra(COLOR_SELECTED, String.format("#%06X", (0xFFFFFF & picker.getColor())));
            startActivity(intent);
        }
    }

    private int checkString(String message)
    {
        if (message.isEmpty() || message.matches("[^a-zA-Z0-9_;-]")) {
            Toast.makeText(getApplicationContext(), "Message is empty", Toast.LENGTH_LONG).show();
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

}

