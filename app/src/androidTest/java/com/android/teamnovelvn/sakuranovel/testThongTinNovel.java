package com.android.teamnovelvn.sakuranovel;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Activity.SearchActivity;
import com.android.teamnovelvn.sakuranovel.Activity.ThongTinNovel;
import com.robotium.solo.Solo;

import me.grantland.widget.AutofitTextView;

/**
 * Created by Akito on 11/12/2016.
 */

public class testThongTinNovel extends ActivityInstrumentationTestCase2<ThongTinNovel>{
    private Solo solo;
    ThongTinNovel thongTinNovel;
    public testThongTinNovel() {
        super(ThongTinNovel.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
        thongTinNovel = getActivity();
    }
    @Override
    public ThongTinNovel getActivity() {
        Intent intent = new Intent();
        intent.putExtra("TRUYEN","Date a Live");
        setActivityIntent(intent);
        return super.getActivity();
    }
    public void testAllGUI() {
        assertNotNull(solo.getView(R.id.txt1));
        assertNotNull(solo.getView(R.id.txt2));
        assertNotNull(solo.getView(R.id.txt3));
        assertNotNull(solo.getView(R.id.txt4));
        assertNotNull(solo.getView(R.id.txtTenTruyen));
        assertNotNull(solo.getView(R.id.imgHinhTTTruyen));
        assertNotNull(solo.getView(R.id.txtTenTacGia));
        assertNotNull(solo.getView(R.id.txtTheLoaiTruyen));
        assertNotNull(solo.getView(R.id.txtNhanSuDich));
        assertNotNull(solo.getView(R.id.txtThongTinTruyen));
        assertNotNull(solo.getView(R.id.expandViewTap));
        assertNotNull(solo.getView(R.id.boom_tt));
        solo.goBack();
    }
    public void testText(){
        TextView textView = (TextView) solo.getView(R.id.txt1);
        TextView textView2 = (TextView) solo.getView(R.id.txt2);
        TextView textView3 = (TextView) solo.getView(R.id.txt3);
        TextView textView4 = (TextView) solo.getView(R.id.txt4);

        assertTrue(solo.searchText("Tác giả"));
        assertTrue(solo.searchText("Thể loại"));
        assertTrue(solo.searchText("Nhân sự"));
        assertTrue(solo.searchText("Tóm tắt"));

        assertEquals("Tác giả", textView.getText().toString());
        assertEquals("Thể loại", textView2.getText().toString());
        assertEquals("Nhân sự", textView3.getText().toString());
        assertEquals("Tóm tắt", textView4.getText().toString());
        solo.goBack();
    }
    public void testNovelNotNull(){

         //thongTinNovel.addEvent("Date a Live");
         AutofitTextView tenTruyen = (AutofitTextView) solo.getView(R.id.txtTenTruyen);
         AutofitTextView tacgia = (AutofitTextView) solo.getView(R.id.txtTenTacGia);
         AutofitTextView theloai = (AutofitTextView) solo.getView(R.id.txtTheLoaiTruyen);
         AutofitTextView nhansu = (AutofitTextView) solo.getView(R.id.txtNhanSuDich);
         AutofitTextView thongtintruyen = (AutofitTextView) solo.getView(R.id.txtThongTinTruyen);

         if(!(tenTruyen.getText().toString().equals("TextView")) && !(tacgia.getText().toString().equals("TextView"))
                 && !(theloai.getText().toString().equals("TextView")) && !(nhansu.getText().toString().equals("TextView"))
                 && !(thongtintruyen.getText().toString().equals("Loading...")))
         {
             assertNotNull(tenTruyen.getText().toString());
             assertNotNull(tacgia.getText().toString());
             assertNotNull(theloai.getText().toString());
             assertNotNull(nhansu.getText().toString());
             assertNotNull(thongtintruyen.getText().toString());
         }
         assertNotSame(getActivity().getResources().getDrawable(R.drawable.loading_image).getConstantState(),
                            ((ImageView) solo.getView(R.id.imgHinhTTTruyen)).getDrawable().getConstantState());
         solo.goBack();
    }
    public void testBoomMenu(){
        solo.sleep(3000);
        View view = (View) solo.getView(R.id.boom_tt);
        solo.clickOnView(view);
        solo.clickOnMenuItem("Search");
        solo.assertCurrentActivity("Can't see SearchActivity", SearchActivity.class);
        assertTrue(solo.waitForActivity(SearchActivity.class));
        solo.goBack();
    }
    public void testNovel(){
        solo.sleep(3000);
        solo.pressMenuItem(1);
//        solo.clickOnText("Chương 1 Tập 0 Gặp gỡ");
//        solo.assertCurrentActivity("Can't see TruyenNovelActivity",TruyenNovel.class);
//        assertTrue(solo.waitForActivity(TruyenNovel.class));
        solo.goBack();
    }

}
