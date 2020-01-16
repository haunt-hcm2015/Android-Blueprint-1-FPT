package com.android.teamnovelvn.sakuranovel.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.teamnovelvn.sakuranovel.Adapters.DanhsachTapTruyen;
import com.android.teamnovelvn.sakuranovel.Adapters.ExpandableAdapters;
import com.android.teamnovelvn.sakuranovel.Entity.LightNovel;
import com.android.teamnovelvn.sakuranovel.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.grantland.widget.AutofitTextView;

import static com.android.teamnovelvn.sakuranovel.R.id.boom_tt;

public class ThongTinNovel extends AppCompatActivity {
    private boolean init = false;
    private BoomMenuButton boomMenuButton;

    AutofitTextView tenTruyen,tomtat,tacgia,theloai,nhansu;
    DatabaseReference mData;
    ImageView hinhtruyen;
    private ExpandableListView expandableListView;
    public HashMap<String, List<String>> childData;
    List<String> listTruyen;
    ExpandableAdapters adaptersExpan;
    public ArrayList<String> linkTruyen = new ArrayList<String>();
    ArrayList<String> linkTruyen2 = new ArrayList<String>();
    List<String> listHead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_novel);

        addControls();
        addEvent();
        initViews();
    }
    public void addControls(){
        mData = FirebaseDatabase.getInstance().getReference();
        tenTruyen = (AutofitTextView) findViewById(R.id.txtTenTruyen);
        tomtat = (AutofitTextView) findViewById(R.id.txtThongTinTruyen);
        tacgia = (AutofitTextView) findViewById(R.id.txtTenTacGia);
        theloai = (AutofitTextView) findViewById(R.id.txtTheLoaiTruyen);
        nhansu = (AutofitTextView) findViewById(R.id.txtNhanSuDich);

        hinhtruyen = (ImageView) findViewById(R.id.imgHinhTTTruyen);
        expandableListView = (ExpandableListView) findViewById(R.id.expandViewTap);

        // vẽ mũi tên
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expandableListView.setIndicatorBounds(width - dp2px(100), width - dp2px(20));

        // khai báo 1 Group Head
        listHead = new ArrayList<>();
        listHead.add("Chương truyện");
        // Khai báo HashMap chứa Group Head và Group Child
        childData = new HashMap<>();
        // Khai báo Group chứa child
        listTruyen = new ArrayList<>();
        childData.put(listHead.get(0), listTruyen);

        adaptersExpan = new ExpandableAdapters(this, listHead, childData);
        expandableListView.setAdapter(adaptersExpan);
    }
    public  void  addEvent(){
        final String ten = getIntent().getExtras().getString("TRUYEN");
        mData.child(ten).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // convert values lại thành object
                LightNovel danhSachTruyen = dataSnapshot.getValue(LightNovel.class);
                // lấy link hình
                String hinh = danhSachTruyen.getHinh();
                // load hình
                Picasso.with(getApplicationContext()).load(hinh).into(hinhtruyen);
                tenTruyen.setText(ten.toString());
                tacgia.setText(danhSachTruyen.getTacGia().toString());
                theloai.setText(danhSachTruyen.getTheLoai().toString());
                nhansu.setText(danhSachTruyen.getTeamDich());
                tomtat.setText(danhSachTruyen.getTomTat().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mData.child(ten).child("tapTruyen").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    DanhsachTapTruyen tapTruyen = dsp.getValue(DanhsachTapTruyen.class);
                    listTruyen.add(tapTruyen.getTenChuong().toString());
                    adaptersExpan.notifyDataSetChanged();
                    linkTruyen.add(tapTruyen.getLinkChuong().toString());
                }
                linkTruyenNovel(linkTruyen);
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
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // khi click vào Group Head của Chương Truyện thì ta sẽ truyền 2 tham số là cái Expandable listView đó và vị trí
                // của Expandable ListView đó ( Vì ta có thể tạo ra nhiều ExpandableListView khác mà ko cần thêm 1 ListView khác
                // để chứa dữ liệu khác, chỉ cần thêm 1 listHead như trên )
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });
    }
    public void linkTruyenNovel(ArrayList<String> link){
        final String ten = getIntent().getExtras().getString("TRUYEN");
        linkTruyen2.addAll(link);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    //Toast.makeText(ThongTinNovel.this,String.valueOf(linkTruyen2.get(childPosition)), Toast.LENGTH_LONG).show();
                Intent i = new Intent(ThongTinNovel.this,TruyenNovel.class);
                i.putExtra("URL",linkTruyen2.get(childPosition).toString());
                i.putExtra("LNOVEL",ten.toString());
                startActivity(i);
                return false;
            }
        });
    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        // Lấy Adapter của Expandable
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 35;
        // lấy đúng 9 xác width của Expan ( Vì từng máy có width khác nhau, bên layout ta đã set Width = Match_parent )
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        // đếm số Expand hiện tại đang xài Adapters đó ( Vì 1 Adapter có thể xài nhiều cho Expandable #
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            // Khúc này chưa hiểu lắm, hên xui
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                // Đếm số lượng Child và set Height
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
//        if (height < 10)
//            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
    public int dp2px(float dp) {
        // Get the screen's density scale
        final float density = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * density + 0.5f);
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
                            Intent intentRoot = new Intent(ThongTinNovel.this, RootActivity.class);
                            startActivity(intentRoot);
                            finish();
//                            Toast.makeText(RootActivity.this,"Home",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 1){
                            Intent intentType = new Intent(ThongTinNovel.this,TypeActivity.class);
                            startActivity(intentType);
                            //Toast.makeText(ThongTinNovel.this,"Type",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 2){
                            Intent intentFavorites = new Intent(ThongTinNovel.this,FavoriteActivity.class);
                            startActivity(intentFavorites);
                            //Toast.makeText(ThongTinNovel.this,"My Favorites",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 3){
                            Intent intentSearch = new Intent(ThongTinNovel.this,SearchActivity.class);
                            startActivity(intentSearch);
                            //Toast.makeText(ThongTinNovel.this,"Search",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 4){
                            Intent intentSetting = new Intent(ThongTinNovel.this, SettingActivity.class);
                            startActivity(intentSetting);
                            //Toast.makeText(ThongTinNovel.this,"Setting",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 5){
                            Intent intentFaq = new Intent(ThongTinNovel.this,FaqActivity.class);
                            startActivity(intentFaq);
                            //Toast.makeText(ThongTinNovel.this,"FAQ",Toast.LENGTH_LONG).show();
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
                                Toast.makeText(ThongTinNovel.this, "There is no email client installed.", Toast.LENGTH_LONG).show();
                            }
//                            Intent i = new Intent(ThongTinNovel.this,FeedbackActivity.class);
//                            startActivity(i);
//                            Toast.makeText(RootActivity.this,"Feedback",Toast.LENGTH_LONG).show();
                        }else if (buttonIndex == 7){
                            Intent intentInfo = new Intent(ThongTinNovel.this,InfoActivity.class);
                            startActivity(intentInfo);
//                            Toast.makeText(RootActivity.this,"Info",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .init(boomMenuButton);
    }


    private void initViews(){
        boomMenuButton = (BoomMenuButton)findViewById(boom_tt);
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
}
