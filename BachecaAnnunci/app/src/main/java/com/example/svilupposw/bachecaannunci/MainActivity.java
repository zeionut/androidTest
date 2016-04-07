package com.example.svilupposw.bachecaannunci;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.svilupposw.bachecaannunci.UserActivity;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mail = (EditText) findViewById(R.id.editText2);
        final EditText pwd = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Firebase ref = MyApplication.getMyFirebaseRef();
                ref.authWithPassword("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.AuthResultHandler()
                {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);

                        startActivity(intent);

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.InvalidLogin),
                                Toast.LENGTH_LONG).show();
                    }

                });

            }
        });


    }
}
