package com.example.svilupposw.barcototurin;

import android.app.Application;

import com.firebase.client.Firebase;


/**
 * Created by svilupposw on 14/03/16.
 */
public class MyApplication extends Application {

    private static final String FIREBASE_URL = "https://testlogandroidengim.firebaseio.com/";
    private static Firebase myFirebaseRef;

    private static String myUid;
    private static String mail;
    private static String name;
    private static int age;

    public static Firebase getMyFirebaseRef() {
        return myFirebaseRef;
    }

    public static Firebase getNoticesRef() {
        return MyApplication.getMyFirebaseRef().child("notices");
    }

    public static Firebase getUsersRef() {
        return MyApplication.getMyFirebaseRef().child("users");
    }

    public static Firebase getUserRefByKey(String key) {
        if (key != null)
            return MyApplication.getMyFirebaseRef().child("users").child(key);
        else
            return null;
    }

    public static String getMyUid() {
        return myUid;
    }

    public static void setMyUid(String myUid) {
        MyApplication.myUid = myUid;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        MyApplication.mail = mail;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        MyApplication.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        MyApplication.age = age;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase(FIREBASE_URL);

    }

}
