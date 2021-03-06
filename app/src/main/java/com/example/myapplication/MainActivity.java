package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "Bonjour");

        button = findViewById(R.id.button);
        text = findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("C'est parti pour le remplissage de formulaire");
                Intent myIntent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }
}