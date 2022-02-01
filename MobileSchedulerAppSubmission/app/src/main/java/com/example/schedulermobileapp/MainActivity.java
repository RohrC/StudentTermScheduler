package com.example.schedulermobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schedulermobileapp.UI.TermListActivity;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toTermList(View view) {
        Intent intent = new Intent(MainActivity.this, TermListActivity.class);
        startActivity(intent);
    }
}