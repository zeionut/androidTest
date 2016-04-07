package com.example.svilupposw.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        String mail = intent.getStringExtra("mail");
        TextView textView = (TextView) findViewById(R.id.benvenuto);

        if (mail != null)
            textView.setText(mail);

    }
}
