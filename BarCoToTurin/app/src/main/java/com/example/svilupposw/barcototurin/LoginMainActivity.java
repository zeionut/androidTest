package com.example.svilupposw.barcototurin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        final EditText mail = (EditText) findViewById(R.id.editText2);
        final EditText pwd = (EditText) findViewById(R.id.editText);

        Button buttonEnter = (Button) findViewById(R.id.buttonLogin);
        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);


        buttonEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Firebase ref = MyApplication.getMyFirebaseRef();
                ref.authWithPassword(mail.getText().toString(), pwd.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Intent intent = new Intent(getApplicationContext(), ListLocalActivity.class);

                        intent.putExtra("mail", ((String) authData.getProviderData().get("email")));

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

        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationMainActivity.class);
                startActivity(intent);
            }

        });

    }
}
