package com.example.ysi4ek.taskandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ysi4ek.taskandroid.R;
import com.example.ysi4ek.taskandroid.model.Rates;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RatesAdapter extends ArrayAdapter<Rates> {
    List<Rates> ratesList;
    Context context;
    private LayoutInflater mInflater;


    public RatesAdapter(Context context, List<Rates> objects){
        super(context, 0 , objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        ratesList = objects;
    }

    @Override
    public Rates getItem(int position){
        return ratesList.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Rates item = getItem(position);

        vh.textView.setText(item.getCurName());
        vh.textView4.setText(item.getCurID());
        Picasso.with(context).load(item.getCurScale()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }
    private static class ViewHolder{
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textView;
        public final TextView textView4;



        public ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textView, TextView textView41) {
            this.rootView = rootView;
            this.imageView= imageView;
            this.textView = textView;
            this.textView4 = textView41;

        }

        public static ViewHolder create(RelativeLayout rootView){
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textView = (TextView) rootView.findViewById(R.id.textView);
            TextView textView41 = (TextView) rootView.findViewById(R.id.textView4);
            return new ViewHolder(rootView, imageView, textView, textView41);
        }
    }
}
