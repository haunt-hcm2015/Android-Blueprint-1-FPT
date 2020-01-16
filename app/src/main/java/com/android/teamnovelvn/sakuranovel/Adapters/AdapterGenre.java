package com.android.teamnovelvn.sakuranovel.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Entity.GenreAnime;
import com.android.teamnovelvn.sakuranovel.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Thanh on 12/7/2016.
 */

public class AdapterGenre extends ArrayAdapter<GenreAnime> {
    Activity context;
    int resource;
    List<GenreAnime> objects;
    public AdapterGenre(Activity context, int resource, List<GenreAnime> objects) {
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

        ImageView hinh = (ImageView) view.findViewById(R.id.imgGenreAnime);
        TextView ten = (TextView) view.findViewById(R.id.txtTenAnime);
        TextView theloai = (TextView) view.findViewById(R.id.txtTenTheLoaiAnime);
        TextView nd = (TextView) view.findViewById(R.id.txtNoiDungAnime);

        GenreAnime lightNovel = this.objects.get(position);
        Picasso.with(context).load(lightNovel.getHinh()).into(hinh);
        ten.setText(lightNovel.getTen());
        theloai.setText("Thể loại: "+lightNovel.getTheLoai(), TextView.BufferType.SPANNABLE);
        nd.setText("Nội dung: "+lightNovel.getNoiDung(), TextView.BufferType.SPANNABLE);

        String subTL = theloai.getText().toString().substring(0,9);
        String subND = nd.getText().toString().substring(0,9);

        nd.setText(nd.getText().toString()+"...");

        Spannable span = (Spannable) theloai.getText();
        span.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.white)), 0, subTL.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        Spannable span1 = (Spannable) nd.getText();
        span1.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.white)), 0, subND.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return view;
    }
}

