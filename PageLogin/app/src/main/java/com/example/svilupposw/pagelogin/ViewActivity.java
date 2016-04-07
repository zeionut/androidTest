package com.example.svilupposw.pagelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();

        String mail = intent.getStringExtra("mail");

        TextView textView = (TextView) findViewById(R.id.textView5);

            textView.setText(mail);
    }

}
