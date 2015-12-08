package com.example.pace.parseapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pace on 2/11/15.
 */



public class Adapter extends BaseAdapter {

    private ArrayList<Concert> mConcerList;
    private Activity activity;
    private static LayoutInflater inflater=null;

    public Adapter(Activity a, ArrayList<Concert> d) {
        activity = a;
        mConcerList=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mConcerList.size();
    }

    @Override
    public Object getItem(int position) {
        return mConcerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.row_item, null);

        TextView title = (TextView)vi.findViewById(R.id.tvTitle); // title
        TextView link = (TextView)vi.findViewById(R.id.tvLink); // artist name
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.ivImage); // thumb image

        Concert mConcert = mConcerList.get(position);
        // Setting all values in listview
        title.setText(mConcert.getTitle());
        link.setText(mConcert.getLink());
        String url = mConcert.getImage_url();
        Picasso.with(activity)
                .load(url)
                .error(R.drawable.image_pic)
                .into(thumb_image);
        return vi;

    }
}
