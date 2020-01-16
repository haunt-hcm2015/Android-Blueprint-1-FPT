package com.android.teamnovelvn.sakuranovel.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Entity.TextSetting;
import com.android.teamnovelvn.sakuranovel.R;

import java.util.List;

/**
 * Created by Thanh on 11/25/2016.
 */

public class AdapterSetting extends ArrayAdapter<TextSetting> {
    Activity context;
    int resource;
    List<TextSetting> objects;
    public AdapterSetting(Activity context, int resource , List<TextSetting> objects){
        super(context,resource,objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view =inflater.inflate(this.resource,null);
        TextView name = (TextView) view.findViewById(R.id.txtvNameSetting);
        TextView title = (TextView) view.findViewById(R.id.txtvTitleSetting);

        TextSetting setting = this.objects.get(position);
        name.setText(setting.getName());
        title.setText(setting.getTitle());

        return  view;

    }
}
