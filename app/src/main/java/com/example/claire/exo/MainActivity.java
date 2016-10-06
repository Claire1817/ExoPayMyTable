package com.example.claire.exo;

import android.content.Intent;
import android.icu.text.Normalizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // When user click on button

    public void sendMessage(View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        if (checkString(message) == 0)
        {
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }

    public int checkString(String message)
    {
        if (message.isEmpty() || message.matches("[^a-zA-Z0-9_;-]")) {
            Toast.makeText(getApplicationContext(), "Message is empty or contains characters no accepted", Toast.LENGTH_LONG).show();
            return 1;
        }
        else if (message.length() > 6) {
            Toast.makeText(getApplicationContext(), "Message is too long", Toast.LENGTH_LONG).show();
            return 1;
        }
        else
            return 0;
    }
}

