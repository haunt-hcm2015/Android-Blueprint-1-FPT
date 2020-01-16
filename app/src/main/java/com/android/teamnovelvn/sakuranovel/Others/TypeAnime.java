package com.android.teamnovelvn.sakuranovel.Others;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.teamnovelvn.sakuranovel.Activity.GenreAnimeActivity;
import com.android.teamnovelvn.sakuranovel.R;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Thanh on 12/7/2016.
 */

public class TypeAnime {
    Activity activity;
    Context context;
    public FancyButton action,adventure,comedy,drama,fantasy,harem,ecchi,horror,romance,lolicon,schoollife,shounen,supernatural
            ,seinen,scifi,mystery,magic,mecha,mature,psy,tragedy,shoujouSOL;

    public TypeAnime(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    public void IdType(){
        action = (FancyButton) activity.findViewById(R.id.btnFbAction);
        adventure = (FancyButton) activity.findViewById(R.id.btnFbAdventure);
        comedy = (FancyButton) activity.findViewById(R.id.btnFbComedy);
        drama = (FancyButton) activity.findViewById(R.id.btnFbDrama);
        fantasy = (FancyButton) activity.findViewById(R.id.btnFbFantasy);
        harem = (FancyButton) activity.findViewById(R.id.btnFbHarem);
        ecchi = (FancyButton) activity.findViewById(R.id.btnFbEcchi);
        horror = (FancyButton) activity.findViewById(R.id.btnFbHorror);
        romance = (FancyButton) activity.findViewById(R.id.btnFbRomance);
        lolicon = (FancyButton) activity.findViewById(R.id.btnFbLolicon);
        schoollife = (FancyButton) activity.findViewById(R.id.btnFbSchoolLife);
        shounen = (FancyButton) activity.findViewById(R.id.btnFbShounen);
        supernatural = (FancyButton) activity.findViewById(R.id.btnFbSupernatural);
        seinen = (FancyButton) activity.findViewById(R.id.btnFbSeinen);
        scifi = (FancyButton) activity.findViewById(R.id.btnFbSciFi);
        mystery = (FancyButton) activity.findViewById(R.id.btnFbMystery);
        magic = (FancyButton) activity.findViewById(R.id.btnFbMagic);
        mecha = (FancyButton) activity.findViewById(R.id.btnFbMecha);
        mature = (FancyButton) activity.findViewById(R.id.btnFbMature);
        psy = (FancyButton) activity.findViewById(R.id.btnFbPsy);
        tragedy = (FancyButton) activity.findViewById(R.id.btnFbTragedy);
        shoujouSOL = (FancyButton) activity.findViewById(R.id.btnFbShoujouSOL);
    }
    public void EventClick() {
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",action.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",adventure.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",comedy.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",drama.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",fantasy.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        harem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",harem.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        ecchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",ecchi.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",horror.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",romance.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        lolicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",lolicon.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        schoollife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",schoollife.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        shounen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",shounen.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        supernatural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",supernatural.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        seinen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",seinen.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        scifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",scifi.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        mystery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",mystery.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",magic.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        mecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",mecha.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        mature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",mature.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        psy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",psy.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        tragedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",tragedy.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
        shoujouSOL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, GenreAnimeActivity.class);
                i.putExtra("THELOAI",shoujouSOL.getText().toString());
                context.startActivity(i);
                activity.finish();
            }
        });
    }
    public ArrayList arrayListView (){
        ArrayList list = new ArrayList();
        list.add(activity.findViewById(R.id.btnFbAction));
        list.add(activity.findViewById(R.id.btnFbAdventure));
        list.add(activity.findViewById(R.id.btnFbComedy));
        list.add(activity.findViewById(R.id.btnFbDrama));
        list.add(activity.findViewById(R.id.btnFbFantasy));
        list.add(activity.findViewById(R.id.btnFbHarem));
        list.add(activity.findViewById(R.id.btnFbEcchi));
        list.add(activity.findViewById(R.id.btnFbHorror));
        list.add(activity.findViewById(R.id.btnFbRomance));
        list.add(activity.findViewById(R.id.btnFbLolicon));
        list.add(activity.findViewById(R.id.btnFbSchoolLife));
        list.add(activity.findViewById(R.id.btnFbShounen));
        list.add(activity.findViewById(R.id.btnFbSupernatural));
        list.add(activity.findViewById(R.id.btnFbSeinen));
        list.add(activity.findViewById(R.id.btnFbSciFi));
        list.add(activity.findViewById(R.id.btnFbMystery));
        list.add(activity.findViewById(R.id.btnFbMagic));
        list.add(activity.findViewById(R.id.btnFbMecha));
        list.add(activity.findViewById(R.id.btnFbMature));
        list.add(activity.findViewById(R.id.btnFbPsy));
        list.add(activity.findViewById(R.id.btnFbTragedy));
        list.add(activity.findViewById(R.id.btnFbShoujouSOL));
        return list;
    }
}
