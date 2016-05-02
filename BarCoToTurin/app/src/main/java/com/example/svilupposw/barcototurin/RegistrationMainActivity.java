package com.example.svilupposw.barcototurin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class RegistrationMainActivity extends AppCompatActivity {

    private EditText email_editText;
    private EditText password_editText;
    private EditText nickname_editText;
    private EditText age_editText;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main);
        email_editText = (EditText) findViewById(R.id.email);
        password_editText = (EditText) findViewById(R.id.password);
        nickname_editText = (EditText) findViewById(R.id.nickname);
        age_editText = (EditText) findViewById(R.id.age);
        signUp = (Button) findViewById(R.id.sign_up_button);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_editText.setError(null);
                password_editText.setError(null);

                final String email = email_editText.getText().toString();
                final String password = password_editText.getText().toString();
                final String nickname = nickname_editText.getText().toString();
                final int age = Integer.parseInt(age_editText.getText().toString());

                boolean cancel = false;
                View focusView = null;

                if (TextUtils.isEmpty(password) || !(password.length() > 4)) {
                    password_editText.setError(getString(R.string.error_invalid_password));
                    focusView = password_editText;
                    cancel = true;
                }

                if (TextUtils.isEmpty(email) || !email.contains("@")) {
                    email_editText.setError(getString(R.string.error_invalid_email));
                    focusView = email_editText;
                    cancel = true;
                }

                if (TextUtils.isEmpty(nickname)) {
                    email_editText.setError(getString(R.string.error_invalid_nickname));
                    focusView = nickname_editText;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    Log.i("registration", "ok");
                    MyApplication.getMyFirebaseRef().createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            System.out.println("Successfully created user account with uid: " + result.get("uid"));
                            MyApplication.setMyUid((String) result.get("uid"));
                            MyApplication.setMail(email);

                            MyApplication.getMyFirebaseRef().authWithPassword(email, password, new Firebase.AuthResultHandler() {
                                @Override
                                public void onAuthenticated(AuthData authData) {
                                    System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                                    // Authentication just completed successfully :)
                                    Map<String, String> map = new HashMap<>();
                                    map.put("provider", authData.getProvider());
                                    if (authData.getProviderData().containsKey("displayNickName")) {
                                        map.put("displayNickName", authData.getProviderData().get("displayNickName").toString());
                                    } else {
                                        map.put("displayNickName", nickname);
                                    }
                                    if (authData.getProviderData().containsKey("displayAge")) {
                                        map.put("displayAge", authData.getProviderData().get("displayAge").toString());
                                    } else {
                                        map.put("displayAge", new Integer(age).toString());
                                    }
                                    MyApplication.getMyFirebaseRef().child("users").child(authData.getUid()).setValue(map, new Firebase.CompletionListener() {
                                        @Override
                                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                            MyApplication.setName(nickname);
                                            Toast.makeText(RegistrationMainActivity.this, "Ok!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), ListLocalActivity.class));
                                        }
                                    });
                                }

                                @Override
                                public void onAuthenticationError(FirebaseError firebaseError) {
                                    // there was an error
                                    Log.e("Login", "error");
                                    Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                            Log.e("creation", "error");
                            Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}