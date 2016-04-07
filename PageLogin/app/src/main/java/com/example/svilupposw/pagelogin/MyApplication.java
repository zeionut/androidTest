package com.example.svilupposw.pagelogin;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.svilupposw.pagelogin.R;

/**
 * Created by svilupposw on 14/03/16.
 */
public class MyApplication extends Application {

    //private String mail = "";
    // attraverso i get e i set lo vedo in tutta l'appliazione

    @Override
    public void onCreate() {
        super.onCreate();

        //Context context = getActivity();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("mail", "filippococchia@hotmail.it");
        editor.putString("name", "Filippo");
        editor.putString("surname", "Cocchia");
        editor.putString("pwd", "ciao");
        editor.putInt("birthYear", 1985);

        editor.commit();

    }
}
