package com.android.teamnovelvn.sakuranovel.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.teamnovelvn.sakuranovel.Fragments.RecyclerViewFragment;
import com.android.teamnovelvn.sakuranovel.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;

import static com.android.teamnovelvn.sakuranovel.R.id.boom;


public class RootActivity extends AppCompatActivity {

//    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    private boolean init = false;
    private BoomMenuButton boomMenuButton;
    private MaterialViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

//        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
//        toolbarTextAppernce();
//        dynamicToolbarColor();
        setTitle("");
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        HomeView();
        vPager();
        initViews();

    }

    public void HomeView(){
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }
            @Override
            public int getCount() {
                return 4;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Tất Cả";
                    case 1:
                        return "Đang dịch";
                    case 2:
                        return "Đã Ngưng";
                    case 3:
                        return "Sắp ra";
                }
                return "";
            }
        });
    }

    public void vPager(){
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blackgew,"https://firebasestorage.googleapis.com/v0/b/sakura-novel.appspot.com/o/H%C3%ACnh%20Novel%2Fwhh6OJt.jpg?alt=media&token=07e0cd36-efe1-43f8-9f85-ae39e46245e3");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blackgew,
                                "https://firebasestorage.googleapis.com/v0/b/sakura-novel.appspot.com/o/H%C3%ACnh%20Novel%2FbfBtNgS.png?alt=media&token=ef9eb930-b3c4-4cff-93e1-9b772c428621");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blackgew,
                                "https://firebasestorage.googleapis.com/v0/b/sakura-novel.appspot.com/o/H%C3%ACnh%20Novel%2F_80_wallpaper_by_nanami_yukina-dagfbeq.png?alt=media&token=caf75bd5-354b-4ed8-b3ec-04fb33724ad9");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blackgew,
                                "https://firebasestorage.googleapis.com/v0/b/sakura-novel.appspot.com/o/H%C3%ACnh%20Novel%2FTouwa-Erio-denpa-onna-to-seishun-otoko-25481623-500-281.png?alt=media&token=d11893f1-70a6-4a49-8165-aa479205caa3");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
//                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
                            HomeView();
                            vPager();
//                            Toast.makeText(RootActivity.this,"Home",Toast.LENGTH_LONG).show();
                        } else if (buttonIndex == 1){
                            Intent intentType = new Intent(RootActivity.this,TypeActivity.class);
                            overridePendingTransition(0, 0);
                            intentType.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            overridePendingTransition(0, 0);
                            startActivity(intentType);
                            //Toast.makeText(RootActivity.this,"Type",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 2){
                            Intent intentFavorites = new Intent(RootActivity.this,FavoriteActivity.class);
                            overridePendingTransition(0, 0);
                            intentFavorites.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            overridePendingTransition(0, 0);
                            startActivity(intentFavorites);
                            //Toast.makeText(RootActivity.this,"My Favorites",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 3){
                            Intent intentSearch = new Intent(RootActivity.this, SearchActivity.class);
                            overridePendingTransition(0, 0);
                            intentSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            overridePendingTransition(0, 0);
                            startActivity(intentSearch);
                            //Toast.makeText(RootActivity.this,"Search",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 4){
                            Intent intentSetting = new Intent(RootActivity.this, SettingActivity.class);
                            overridePendingTransition(0, 0);
                            intentSetting.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            overridePendingTransition(0, 0);
                            startActivity(intentSetting);
                            //Toast.makeText(RootActivity.this,"Setting",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 5){
                            Intent intentFaq = new Intent(RootActivity.this,FaqActivity.class);
                            overridePendingTransition(0, 0);
                            intentFaq.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            overridePendingTransition(0, 0);
                            startActivity(intentFaq);
                            //Toast.makeText(RootActivity.this,"FAQ",Toast.LENGTH_LONG).show();
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
                                Toast.makeText(RootActivity.this, "There is no email client installed.", Toast.LENGTH_LONG).show();
                            }
//                            Intent i = new Intent(RootActivity.this,FeedbackActivity.class);
//                            overridePendingTransition(0, 0);
//                            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                            overridePendingTransition(0, 0);
//                            startActivity(i);
//                            Toast.makeText(RootActivity.this,"Feedback",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 7){
                            Intent intentInfo = new Intent(RootActivity.this,InfoActivity.class);
                            overridePendingTransition(0, 0);
                            intentInfo.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            overridePendingTransition(0, 0);
                            startActivity(intentInfo);
//                            Toast.makeText(RootActivity.this,"Info",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .init(boomMenuButton);
    }


    private void initViews(){
        boomMenuButton = (BoomMenuButton)findViewById(boom);
        //boomMenuButton.setShareLineWidth(100* 6f/100);
//        boomMenuButton.setDuration(300);
//        boomMenuButton.setFrames(120);
//        boomMenuButton.setRotateDegree(720);
//        getPlaceType();
        initBoom();
    }

//    private PlaceType getPlaceType(){
//        return PlaceType.;
//    }

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

//    private void dynamicToolbarColor() {
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.lighting);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
//                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark));
//            }
//        });
//    }

//    private void toolbarTextAppernce() {
//        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
//    }
}
