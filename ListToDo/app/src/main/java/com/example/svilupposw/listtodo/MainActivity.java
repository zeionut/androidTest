package com.example.svilupposw.listtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        myAdapter = new MyAdapter(getApplicationContext());

        ListView listView = (ListView) findViewById(R.id.my_list);

        if(listView != null) {

            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();

                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(getApplicationContext(), "Se riesco lo rimuovo", Toast.LENGTH_SHORT).show();

                    return true;
                }
            });

        }

        MyApplication.getMyFirebaseRef().child("whatToDo").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildAdded", dataSnapshot.getKey());
                //viene chiamata per ogni elemento esistente della lista oppure quando un nuovo elemento viene aggiunto
                WhatToDo newPost = dataSnapshot.getValue(WhatToDo.class);
                myAdapter.addItem(newPost);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildChanged", dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("onChildRemoved", dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("onChildMoved", "dataSnapshot.getKey()");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.i("onCancelled", firebaseError.getMessage());
            }
        });

        Button add_button = (Button) findViewById(R.id.buttonSend);

        if(add_button != null)
            add_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    EditText listText = (EditText) findViewById(R.id.listText);

                    String getListText = listText.getText().toString();

                    Firebase whatToDoRef = MyApplication.getMyFirebaseRef().child("whatToDo");
                    Firebase newWhatToDoRefRef = whatToDoRef.push();

                    WhatToDo item = new WhatToDo(getListText);

                    item.setId(newWhatToDoRefRef.getKey());

                    newWhatToDoRefRef.setValue(item);

                    listText.setText("");

                }

            });

    }
}
