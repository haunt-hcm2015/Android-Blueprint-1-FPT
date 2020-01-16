package com.android.teamnovelvn.sakuranovel.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Adapters.AdapterSetting;
import com.android.teamnovelvn.sakuranovel.Entity.TextSetting;
import com.android.teamnovelvn.sakuranovel.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lst;
    private ArrayList<TextSetting> arrayList= new ArrayList<>();
    private ArrayList<String> nameSetting;
    private ArrayList<String> titleSetting;
    private AdapterSetting adapterSetting ;
    private TextSetting text;
    private Dialog dialog;
    private RadioButton rdoSang ,rdoToi ;
    private RadioGroup rdog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        lst = (ListView)findViewById(R.id.listview_Setting);
        adapterSetting = new AdapterSetting(SettingActivity.this,R.layout.layout_setting_adapter,arrayList);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    nameSetting = new ArrayList<String>();
                    nameSetting.add("SakuraNovel Account");
                    nameSetting.add("Ngoại tuyến");
                    nameSetting.add("Download với Wifi");
                    nameSetting.add("Hiện thông báo");
                    nameSetting.add("Chủ đề");
                    nameSetting.add("Giấy phép");

                    titleSetting = new ArrayList<String>();
                    titleSetting.add(user.getEmail());
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");

                    for (int i = 0 ; i < nameSetting.size() ; i++){
                        String ten = nameSetting.get(i).toString();
                        String title = titleSetting.get(i).toString();
                        arrayList.add(new TextSetting(ten,title));
                    }

                    adapterSetting.notifyDataSetChanged();
                    lst.setAdapter(adapterSetting);

                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (0 == position) {
                                setContentView(R.layout.infor_user);
                                TextView txt = (TextView)findViewById(R.id.txtGmail);
                                String a = user.getEmail();
                                txt.setText(a);
                                Button btn = (Button)findViewById(R.id.btnSignout);
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                        FirebaseAuth.getInstance().signOut();
                                    }
                                });

                            }else if (2 == position){
                                arrayList.set(position,new TextSetting("Download với Wifi","Tắt"));
                                adapterSetting.notifyDataSetChanged();
                            }else if (4 == position){
                                dialog = new Dialog(SettingActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.dialog_theme);
                                rdoSang = (RadioButton)dialog.findViewById(R.id.rdoLight);
                                rdoToi = (RadioButton)dialog.findViewById(R.id.rdoDark);
                                rdog = (RadioGroup)dialog.findViewById(R.id.rdoG);
                                rdoSang.setChecked(true);
                                dialog.setCanceledOnTouchOutside(true);
                                dialog.show();

                                rdog.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        switch (checkedId){
                                            case R.id.rdoDark:
                                                RelativeLayout relati = (RelativeLayout)findViewById(R.id.activity_setting);
                                                relati.setBackgroundColor(Color.CYAN);

                                                adapterSetting.notifyDataSetChanged();
                                                dialog.dismiss();
                                                break;
                                            case R.id.rdoLight:

                                        }
//                            if (rdoToi.isChecked()){
////                                arrayList.set(position,new TextSetting("Chủ đề","Tối"));
//                                RelativeLayout relati = (RelativeLayout)findViewById(R.id.activity_setting);
//                                relati.setBackgroundColor(Color.CYAN);
//                                adapterSetting.notifyDataSetChanged();
//                            }else {
////                                arrayList.set(position,new TextSetting("Chủ đề","Sáng"));
//                                adapterSetting.notifyDataSetChanged();
//                            }
                                    }
                                });

                            }
                        }
                    });
                } else {
                    // User is signed out
                    nameSetting = new ArrayList<String>();
                    nameSetting.add("Login");
                    nameSetting.add("Ngoại tuyến");
                    nameSetting.add("Download với Wifi");
                    nameSetting.add("Hiện thông báo");
                    nameSetting.add("Chủ đề");
                    nameSetting.add("Giấy phép");

                    titleSetting = new ArrayList<String>();
                    titleSetting.add("");
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");
                    titleSetting.add("Comming soon");

                    for (int i = 0 ; i < nameSetting.size() ; i++){
                        String ten = nameSetting.get(i).toString();
                        String title = titleSetting.get(i).toString();
                        arrayList.add(new TextSetting(ten,title));
                    }

                    adapterSetting.notifyDataSetChanged();
                    lst.setAdapter(adapterSetting);

                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (0 == position) {
                                Intent i = new Intent(SettingActivity.this,LoginLibActivity.class);
                                startActivity(i);
                                finish();
                            }else if (2 == position){
                                arrayList.set(position,new TextSetting("Download với Wifi","Tắt"));
                                adapterSetting.notifyDataSetChanged();
                            }else if (4 == position){
                                dialog = new Dialog(SettingActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.dialog_theme);
                                rdoSang = (RadioButton)dialog.findViewById(R.id.rdoLight);
                                rdoToi = (RadioButton)dialog.findViewById(R.id.rdoDark);
                                rdog = (RadioGroup)dialog.findViewById(R.id.rdoG);
                                rdoSang.setChecked(true);
                                dialog.setCanceledOnTouchOutside(true);
                                dialog.show();

                                rdog.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        switch (checkedId){
                                            case R.id.rdoDark:
                                                RelativeLayout relati = (RelativeLayout)findViewById(R.id.activity_setting);
                                                relati.setBackgroundColor(Color.CYAN);

                                                adapterSetting.notifyDataSetChanged();
                                                dialog.dismiss();
                                                break;
                                            case R.id.rdoLight:

                                        }
//                            if (rdoToi.isChecked()){
////                                arrayList.set(position,new TextSetting("Chủ đề","Tối"));
//                                RelativeLayout relati = (RelativeLayout)findViewById(R.id.activity_setting);
//                                relati.setBackgroundColor(Color.CYAN);
//                                adapterSetting.notifyDataSetChanged();
//                            }else {
////                                arrayList.set(position,new TextSetting("Chủ đề","Sáng"));
//                                adapterSetting.notifyDataSetChanged();
//                            }
                                    }
                                });

                            }
                        }
                    });

                }
            }
        };




        toolBar();
    }
    public void toolBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
