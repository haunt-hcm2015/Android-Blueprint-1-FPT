package com.android.teamnovelvn.sakuranovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.teamnovelvn.sakuranovel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchActivity extends AppCompatActivity {

    private MaterialSearchView searchView;
    private DatabaseReference mData;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Search");
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        toolBar();
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menu menu = (Menu)findViewById(R.id.action_search);

                searchView.showSearch();
            }
        });

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        addNovel();
    }

    public void toolBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        mData = FirebaseDatabase.getInstance().getReference();
    }

    public void addNovel(){
        final ArrayList arrayList = new ArrayList();
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    String key = dsp.getKey().toString();
                    arrayList.add(key);
                }
                //Chuyển đổi đối tượng ArrayList thành mảng đối tượng(Object[])
                Object[] objects = arrayList.toArray();
                //Chuyển đổi mảng đối tượng(Object[]) thành mảng String.
                String[] arrayStr = Arrays.copyOf(objects,objects.length,String[].class);
                //setSuggestions chỉ nhận mảng string[], không nhận mảng arraylist
                //gợi ý truyện
                searchView.setSuggestions(arrayStr);
                timkiem(arrayList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void timkiem(final ArrayList arrayList){
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                //Do some magic
                for(int i =0; i<arrayList.size();i++){
                    if(arrayList.get(i).equals(newText)){
                        Intent in = new Intent(SearchActivity.this,ThongTinNovel.class);
                        in.putExtra("TRUYEN",String.valueOf(arrayList.get(i).toString()));
                        startActivity(in);
                    }
                }
                return false;
            }
        });
    }


    //Dung thanh dieu huong de thoat khi search neu khong co se thoat khoi layout
    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }
}
