package com.example.svilupposw.pagelogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                String mailInport = sharedPref.getString("mail", "");
                String pwdInport = sharedPref.getString("pwd", "");

                String m = mail.getText().toString();
                String p = pwd.getText().toString();

                if (m.equals(mailInport) && p.equals(pwdInport)) {

                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);

                    //intent.putExtra("mail", m);
                    //intent.putExtra("pwd", p);

                    // String s = getApplicationContext().getResources().getString(R.string.app_name);
                    // come chiamare una stringa nel file String all'interno del bottone devo specificare il contesto
                    // con getApplicationContext()

                    startActivity(intent);

                }

                else {

                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.InvalidLogin),
                            Toast.LENGTH_LONG).show();

                }

            }
        });


    }
}
