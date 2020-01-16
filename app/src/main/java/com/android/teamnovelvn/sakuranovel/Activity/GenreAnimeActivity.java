package com.android.teamnovelvn.sakuranovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Adapters.AdapterGenre;
import com.android.teamnovelvn.sakuranovel.Entity.GenreAnime;
import com.android.teamnovelvn.sakuranovel.Entity.LightNovel;
import com.android.teamnovelvn.sakuranovel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenreAnimeActivity extends AppCompatActivity {
    String TL;
    TextView genre;
    ArrayList<GenreAnime> arrayAnimes = new ArrayList<GenreAnime>();
    AdapterGenre adapterGenre;
    ListView listView;
    DatabaseReference mData;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_anime);

        TL = getIntent().getExtras().getString("THELOAI");
        addControls();
        addEvent();
    }

    public void addControls(){
        listView = (ListView) findViewById(R.id.lvListTheLoai);
        genre = (TextView) findViewById(R.id.txtGenreAnime);
        genre.setText("Thể loai: "+TL.toString());
        mData = FirebaseDatabase.getInstance().getReference();

        adapterGenre = new AdapterGenre(GenreAnimeActivity.this,R.layout.view_genre_layout,arrayAnimes);
        listView.setAdapter(adapterGenre);
    }
    public void addEvent(){
        arrayList = new ArrayList<String>();
        // lấy tên truyện trên firebase
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    arrayList.add(dsp.getKey().toString());
                }
                timTheLoaiTruyen(arrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GenreAnime genreAnime =  arrayAnimes.get(position);
                Intent i = new Intent(GenreAnimeActivity.this,ThongTinNovel.class);
                i.putExtra("TRUYEN",genreAnime.getTen());
                startActivity(i);

            }
        });
    }
    public void timTheLoaiTruyen(final ArrayList<String> arrayList) {
        // lấy thể loại truyện của từng truyện
        for(int i = 0; i<arrayList.size();i++){
            final String novel = arrayList.get(i);
            mData.child(novel).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    LightNovel lightNovel = dataSnapshot.getValue(LightNovel.class);
                    String theloai = lightNovel.getTheLoai();
                    Pattern pattern = Pattern.compile(TL);
                    Matcher matcher = pattern.matcher(theloai);
                    if(matcher.find()){
                        arrayAnimes.add(new GenreAnime(
                                String.valueOf(lightNovel.getHinh()),
                                novel.toString(),
                                String.valueOf(lightNovel.getTheLoai()),
                                String.valueOf(lightNovel.getTomTat())
                        ));
                    }
                    adapterGenre.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(GenreAnimeActivity.this,TypeActivity.class);
        startActivity(i);
        finish();
    }
}
