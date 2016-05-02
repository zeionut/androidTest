package com.example.svilupposw.barcototurin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by svilupposw on 17/03/16.
 */

public class MyAdapter extends BaseAdapter {

    private ArrayList <Local> list;
    private Context context;

    public MyAdapter(Context context) {

        this.context = context;
        list = new ArrayList<>();

    }

    public void addItem (Local local) {

        list.add(local);
        // aggiorna vista della lista
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {

        return list.size();

    }

    @Override
    public Object getItem(int position) {

        return list.get(position);

    }

    @Override
    public long getItemId(int position) {

        return 0;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_local, parent, false);
        }

        TextView localName = (TextView) convertView.findViewById(R.id.localName);
        TextView localType = (TextView) convertView.findViewById(R.id.localType);

        localName.setText(((Local) getItem(position)).getName());
        localType.setText(((Local) getItem(position)).getType());



        return convertView;
    }

}
