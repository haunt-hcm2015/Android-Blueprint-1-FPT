package com.android.teamnovelvn.sakuranovel.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.teamnovelvn.sakuranovel.Others.TypeAnime;
import com.android.teamnovelvn.sakuranovel.R;

import java.util.ArrayList;
import java.util.Random;

public class TypeActivity extends AppCompatActivity {
    TypeAnime typeAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        typeAnime = new TypeAnime(TypeActivity.this,this);
        typeAnime.IdType();
        randomColor();
        typeAnime.EventClick();
    }
    public void randomColor(){
        ArrayList arrayList = new ArrayList();
        arrayList = typeAnime.arrayListView();
        for(int i = 0; i <arrayList.size();i++){
            View view = (View) arrayList.get(i);
            view.setBackgroundColor(getRandomColor());
        }
    }
    private String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B",
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#2EFEC8",
            "#F7FE2E",
            "#4CAF50",
            "#FA5858",
            "#6E6E6E",
            "#FE2E9A",
            "#819FF7",
            "#071910",
            "#610B21",
            "#3ADF00",
            "#0080FF",
            "#8A0808",
            "#AC58FA",
            "#FA5882"
    };
    public int getRandomColor(){
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }
}
