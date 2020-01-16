package com.android.teamnovelvn.sakuranovel.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Activity.RootActivity;
import com.android.teamnovelvn.sakuranovel.Entity.DanhSachTruyen;
import com.android.teamnovelvn.sakuranovel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitHelper;
import me.grantland.widget.AutofitTextView;

/**
 * Created by Akito on 21/11/2016.
 */

public class AdapterTruyen extends ArrayAdapter<DanhSachTruyen>{
    Activity context;
    int resource;
    List<DanhSachTruyen> objects;
    public AdapterTruyen(Activity context, int resource, List<DanhSachTruyen> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = this.context.getLayoutInflater();
            View view = inflater.inflate(this.resource,null);
            ImageView hinh = (ImageView) view.findViewById(R.id.imghinhtruyen);
            AutofitTextView ten = (AutofitTextView) view.findViewById(R.id.txtTenTruyenChinh);

            DanhSachTruyen truyen = this.objects.get(position);
            ten.setText(truyen.getTenTruyen());
            AutofitHelper.create(ten);
            // dùng picasso để load ảnh từ firebase lên, đối tượng truyền vào phải là 1 URL
            Picasso.with(context).load(truyen.getLinkHinh()).into(hinh);
        return view;
    }
}
