package com.example.vcard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class Custom_admin_view_manuf_feedbck extends BaseAdapter {
    String[]uname,rating,feedback,date;
    private Context context;


    public Custom_admin_view_manuf_feedbck(Context appcontext, String[] uname, String[] rating, String[] feedback, String[] date) {
        this.context = appcontext;
        this.uname = uname;
        this.rating = rating;
        this.feedback = feedback;
        this.date = date;


    }

    @Override
    public int getCount() {
        return uname.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_admin_view_manuf_feedback, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.manufname);
        TextView tv2 = (TextView) gridView.findViewById(R.id.feedbackdate);
        TextView tv3 = (TextView) gridView.findViewById(R.id.feedback);
        RatingBar tb = (RatingBar) gridView.findViewById(R.id.ratingBar2);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(uname[i]);
        tv2.setText(date[i]);
        tv3.setText(feedback[i]);


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");


        return gridView;
    }
}
