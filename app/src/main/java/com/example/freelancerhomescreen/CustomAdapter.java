package com.example.freelancerhomescreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> Title;
    private int imge;

    public CustomAdapter(Context context, ArrayList<String> text1) {
        mContext = context;
        Title = text1;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Title.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        row = inflater.inflate(R.layout.row, parent, false);
        TextView title;
        ImageView i1;
        title = (TextView) row.findViewById(R.id.txtTitle);
        title.setText(Title.get(position));
        return (row);
    }

    public String getPrio(int i){return Title.get(i);}

}
