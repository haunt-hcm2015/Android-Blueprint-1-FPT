package com.android.teamnovelvn.sakuranovel.Activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.teamnovelvn.sakuranovel.Adapters.DanhsachTapTruyen;
import com.android.teamnovelvn.sakuranovel.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import me.grantland.widget.AutofitTextView;

import static com.android.teamnovelvn.sakuranovel.R.id.boom_truyen;
import static com.android.teamnovelvn.sakuranovel.R.id.rdoLon;
import static com.android.teamnovelvn.sakuranovel.R.id.rdoNho;
import static com.android.teamnovelvn.sakuranovel.R.id.rdoTbinh;

public class TruyenNovel extends AppCompatActivity {
    private boolean init = false;
    private BoomMenuButton boomMenuButton;
    private TextView imgb;
    private Dialog dialog;
    private RadioButton rdonho ,rdotb,rdolon ;
    private RadioGroup rdogr;
    private AutofitTextView autofNho,autofLon,autofTbinh;
    private SharedPreferences shared;
    private RelativeLayout rl;
    private LinearLayout ll,hideAll;
    private static int z =0;
    private Snackbar snackbar;

    AutofitTextView novel;
    TextView chaptruoc, TTtruyen, chapsau;
    DatabaseReference mData;
    int count = 0;
    private ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_novel);

        rl = (RelativeLayout)findViewById(R.id.activity_truyen_novel);
        snackbar = Snackbar.make(rl,"Chưa có tập mới",Snackbar.LENGTH_LONG);
        hideAll = (LinearLayout)findViewById(R.id.test);
        ll = (LinearLayout)findViewById(R.id.hidetext);
        dialog = new Dialog(TruyenNovel.this);
        //pg = (ProgressBar)findViewById(R.id.progressBar3);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogfont);
        rdonho = (RadioButton)dialog.findViewById(rdoNho);
        rdotb = (RadioButton)dialog.findViewById(R.id.rdoTbinh);
        rdolon = (RadioButton)dialog.findViewById(rdoLon);
        rdotb.setChecked(true);
        rdogr = (RadioGroup)dialog.findViewById(R.id.rdoGfont);
        autofNho = (AutofitTextView)findViewById(R.id.LightNovel);
        autofTbinh = (AutofitTextView)findViewById(R.id.LightNovel);
        autofLon = (AutofitTextView)findViewById(R.id.LightNovel);
        imgb = (TextView) findViewById(R.id.txtimgBtn);
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();


                rdogr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case rdoNho:
                                shared = getSharedPreferences("Share", MODE_APPEND);
                                SharedPreferences.Editor edit = shared.edit();
                                autofNho.setTextSize(15);
                                edit.putInt("test",15);
                                edit.putBoolean("CHK",rdonho.isChecked());
                                edit.putInt("luu",rdoNho);
                                rdonho.setChecked(true);
                                edit.commit();
                                dialog.dismiss();
                                break;
                            case rdoTbinh:
                                shared = getSharedPreferences("Share", MODE_APPEND);
                                SharedPreferences.Editor editTb = shared.edit();
                                autofTbinh.setTextSize(20);
                                editTb.putInt("test",20);
                                editTb.putBoolean("CHK",rdotb.isChecked());
                                editTb.putInt("luu",rdoTbinh);
                                rdotb.setChecked(true);
                                editTb.commit();
                                dialog.dismiss();
                                break;

                            case R.id.rdoLon:
                                shared = getSharedPreferences("Share",MODE_APPEND);
                                SharedPreferences.Editor editL = shared.edit();
                                autofLon = (AutofitTextView)findViewById(R.id.LightNovel);
                                autofLon.setTextSize(25);
                                editL.putInt("test",25);
                                editL.putBoolean("CHK",rdolon.isChecked());
                                editL.putInt("luu",rdoLon);
                                rdolon.setChecked(true);
                                editL.commit();
                                dialog.dismiss();
                                break;
                        }
                    }
                });
            }
        });
        addControls();
        addEvent();

        initViews();
        hide();
    }
    public void addControls(){
        chaptruoc = (TextView) findViewById(R.id.txtVeChapTruoc);
        chapsau = (TextView) findViewById(R.id.txtToiChapSau);
        TTtruyen = (TextView) findViewById(R.id.txtQuayVeTTNovel);
        novel = (AutofitTextView) findViewById(R.id.LightNovel);
        mData = FirebaseDatabase.getInstance().getReference();
    }
    public void addEvent(){
        final String urlink = getIntent().getExtras().getString("URL");
        new Thread() {
            @Override
            public void run() {
                URL u = null;
                try {
                    //lấy đường dẫn
                    u = new URL(urlink);
                    // tạo 1 luồng để đọc file từ đường dẫn
                    final BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
                    // biến str dùng để lấy tất cả dữ liệu ở 1 dòng
                    String str = "";
                    // nd nhận nội dung từ str
                    String nd = "";
                    while((str = in.readLine()) != null){
                        nd +=str.toString()+"\n";
                    }
                    // vì do textview chỉ setText đc trong 1 luồng con nên tạo thêm 1 biến để nhận tất cả từ nd
                    final String ndc = nd.toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            novel.setText(ndc.toString());

                            SharedPreferences shared = getSharedPreferences("Share",MODE_APPEND);
                            Boolean chk = shared.getBoolean("CHK", false);
                            if (chk){
                                //int nhot = shared.getInt("Nho",15);
                                int fontsize = shared.getInt("test",20);
                                autofNho.setTextSize(fontsize);
                                int as = shared.getInt("luu",0);
                                if (rdoNho == as){
                                    rdonho.setChecked(true);
                                }else if (rdoLon == as){
                                    rdolon.setChecked(true);
                                }else{
                                    rdotb.setChecked(true);
                                }
                            }else {
                                novel.setTextSize(20);
                            }


                            try {
                                in.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        final String ten = getIntent().getExtras().getString("LNOVEL");
        mData.child(ten).child("tapTruyen").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ArrayList<String> arrayURL = new ArrayList<String>();

                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    DanhsachTapTruyen ds = dsp.getValue(DanhsachTapTruyen.class);
                    arrayURL.add(String.valueOf(ds.getLinkChuong()));

                }
                loadNovel(arrayURL, ten, urlink);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void loadNovel(ArrayList<String> arrayURL, final String tenNovel, final String URL){
        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.addAll(arrayURL);
        TTtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TruyenNovel.this,ThongTinNovel.class);
                i.putExtra("TRUYEN",tenNovel.toString());
                startActivity(i);
                finish();
            }
        });

        chaptruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<arrayList.size();i++){
                    String url = arrayList.get(i);
                            if(URL.equals(url)){
                                if(i ==0){
                                    // tạo 1 custom Toast
                                    Snackbar.make(rl,"Không thể quay lại tập trước",Snackbar.LENGTH_LONG).show();
//                                    Toast toast = Toast.makeText(TruyenNovel.this,"Không thể quay lại tập trước",Toast.LENGTH_LONG);
//                                    View view = toast.getView();
//                                    TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
//                                    toastMessage.setTextColor(Color.RED);
//                                    toastMessage.setGravity(Gravity.CENTER);
//                                    toastMessage.setCompoundDrawablePadding(16);
//                                    view.setBackgroundColor(Color.CYAN);
//                                    toast.show();
                                }else {
                                    String getChaptruoc = arrayList.get(i - 1);
                                    Intent reload = new Intent(TruyenNovel.this, TruyenNovel.class);
                                    reload.putExtra("URL", getChaptruoc);
                                    reload.putExtra("LNOVEL", tenNovel);
                                    overridePendingTransition(0, 0);
                                    reload.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(reload);
                                }
                            }
                        }
            }
        });
        chapsau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<arrayList.size();i++){
                    String url = arrayList.get(i);
                            if(URL.equals(url)){
                                // tạo 1 custom Toast
                                if(i+1 == arrayList.size()) {
                                    Snackbar.make(rl,"Chưa có tập mới",Snackbar.LENGTH_LONG).show();
//                                    Toast toast = Toast.makeText(TruyenNovel.this, "Chưa có tập mới", Toast.LENGTH_LONG);
//                                    View view = toast.getView();
//                                    TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
//                                    toastMessage.setTextColor(Color.RED);
//                                    toastMessage.setGravity(Gravity.CENTER);
//                                    view.setBackgroundColor(Color.CYAN);
//                                    toast.show();
                                }else{
                                    String getChapsau = arrayList.get(i + 1);
                                    Intent reload = new Intent(TruyenNovel.this, TruyenNovel.class);
                                    reload.putExtra("URL", getChapsau);
                                    reload.putExtra("LNOVEL", tenNovel);
                                    overridePendingTransition(0, 0);
                                    reload.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(reload);
                                }
                            }
                }
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        if (init) return;
        init = true;

        initBoom();
    }

    private void initBoom(){
        int number = 8;
        Drawable[] drawables = new Drawable[number];
        int[] drawablesResource = new int[]{
                R.drawable.home,
                R.drawable.copy,
                R.drawable.heart,
                R.drawable.search,
                R.drawable.settings,
                R.drawable.faq,
                R.drawable.feedback,
                R.drawable.info

        };
        for (int i = 0; i < number ; i++){
            drawables[i]  = ContextCompat.getDrawable(this,drawablesResource[i]);
        }
        String[] STRINGS = new String[]{
                "Home",
                "Type",
                "My Favorites",
                "Search",
                "Setting",
                "FAQ",
                "Feedback",
                "Info"
        };
        String[] strings = new String[number];
        for (int i = 0; i < number ; i++){
            strings[i]  = STRINGS[i];
        }
        int[][] colors = new int[number][2];
        for (int i = 0 ;i < number; i++){
            colors[i][1] = getRandomColor();
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

        new BoomMenuButton.Builder()
                .subButtons(drawables,colors,strings)
                .button(ButtonType.CIRCLE)
                .boom(BoomType.HORIZONTAL_THROW_2)
                .place(PlaceType.CIRCLE_8_1)
                .frames(80)
                .duration(500)
                .rotateDegree(0)
                .autoDismiss(true)
                .boomButtonShadow(Util.getInstance().dp2px(2),Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(2),Util.getInstance().dp2px(2))
                .shareStyle(3f,getRandomColor(),getRandomColor())
                .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                    @Override
                    public void onClick(int buttonIndex) {
                        if (buttonIndex == 0){
                            Intent intentRoot =new Intent(TruyenNovel.this,RootActivity.class);
                            startActivity(intentRoot);
                            finish();
                        }else if (buttonIndex == 1){
                            Intent intentType = new Intent(TruyenNovel.this,TypeActivity.class);
                            startActivity(intentType);
                        }else if (buttonIndex == 2){
                            Intent intentFavorites = new Intent(TruyenNovel.this,FavoriteActivity.class);
                            startActivity(intentFavorites);
                        }else if (buttonIndex == 3){
                            Intent intentSearch = new Intent(TruyenNovel.this,SearchActivity.class);
                            startActivity(intentSearch);
                        }else if (buttonIndex == 4){
                            Intent intentSetting = new Intent(TruyenNovel.this, SettingActivity.class);
                            startActivity(intentSetting);
                        }else if (buttonIndex == 5){
                            Intent intentFaq = new Intent(TruyenNovel.this,FaqActivity.class);
                            startActivity(intentFaq);

                        }else if (buttonIndex == 6){
                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            String[] to = {"sakuranovelfpt@gmail.com"};
                            emailIntent.setData(Uri.parse("mailto:"));
                            emailIntent.setType("message/rfc822");
                            emailIntent.setPackage("com.google.android.gm");
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, to );
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from Sakura Novel");
                            try
                            {
                                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                                Log.i("Finished sending ema...", "");
                            }
                            catch (ActivityNotFoundException localActivityNotFoundException)
                            {
                                Toast.makeText(TruyenNovel.this, "There is no email client installed.", Toast.LENGTH_LONG).show();
                            }
                        }else if (buttonIndex == 7){
                            Intent intentInfo = new Intent(TruyenNovel.this,InfoActivity.class);
                            startActivity(intentInfo);
                        }
                    }
                })
                .init(boomMenuButton);
    }

    private void initViews(){
        boomMenuButton = (BoomMenuButton)findViewById(boom_truyen);
        initBoom();
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
            "#607D8B"
    };

    public int getRandomColor(){
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }

    public void hide(){
        final GestureDetector gd = new GestureDetector(TruyenNovel.this, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (z == 0){
                    ll.setVisibility(View.INVISIBLE);
                    boomMenuButton.setVisibility(View.INVISIBLE);
                    z = 1;
                }else {
                    ll.setVisibility(View.VISIBLE);
                    boomMenuButton.setVisibility(View.VISIBLE);
                    z = 0;
                }

                return super.onDoubleTap(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

        });

        hideAll.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });
    }
}
