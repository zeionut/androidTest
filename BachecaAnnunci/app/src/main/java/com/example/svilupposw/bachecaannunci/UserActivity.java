package com.example.svilupposw.bachecaannunci;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        //Intent intent = getIntent();

        String mail = sharedPref.getString("mail", "");
        String name = sharedPref.getString("name", "");
        String surname = sharedPref.getString("surname", "");

        int birthYear = sharedPref.getInt("birthYear", 0);

        //String.valueOf(birthYear);
        //Trasformare un int in una stringa

        Firebase ref = MyApplication.getMyFirebaseRef();

        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {

                    //Intent intent = getIntent();
                    //
                    //String mail = intent.getStringExtra("mail");

                    String mail = ((String) authData.getProviderData().get("email"));

                    TextView textViewMailInport = (TextView) findViewById(R.id.textViewMailInport);
                    textViewMailInport.setText(mail);
                }

                else {
                    // user is not logged in
                }
            }
        });


        //TextView textViewMailInport = (TextView) findViewById(R.id.textViewMailInport);
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        TextView textViewSurname = (TextView) findViewById(R.id.textViewSurname);
        TextView textViewBirthYearInport = (TextView) findViewById(R.id.textViewbirthYearInport);

        //textViewMailInport.setText(mail);
        textViewName.setText(name);
        textViewSurname.setText(surname);
        textViewBirthYearInport.setText(String.valueOf(birthYear));


    }
}
