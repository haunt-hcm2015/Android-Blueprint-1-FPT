package com.android.teamnovelvn.sakuranovel;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.android.teamnovelvn.sakuranovel.Activity.SearchActivity;
import com.android.teamnovelvn.sakuranovel.Activity.ThongTinNovel;
import com.android.teamnovelvn.sakuranovel.Activity.TruyenNovel;
import com.robotium.solo.Solo;

import me.grantland.widget.AutofitTextView;

/**
 * Created by Akito on 11/12/2016.
 */

public class testTruyenNovel extends ActivityInstrumentationTestCase2<TruyenNovel>{
    Solo solo;
    TruyenNovel truyenNovel;

    public testTruyenNovel() {
        super(TruyenNovel.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        truyenNovel = getActivity();
    }

    public void testAllGUI(){
        assertNotNull(solo.getView(R.id.LightNovel));
        assertNotNull(solo.getView(R.id.txtimgBtn));
        assertNotNull(solo.getView(R.id.txtVeChapTruoc));
        assertNotNull(solo.getView(R.id.txtQuayVeTTNovel));
        assertNotNull(solo.getView(R.id.txtToiChapSau));
        assertNotNull(solo.getView(R.id.boom_truyen));
        solo.goBack();
    }

    public void testTextNovel(){
        assertTrue(solo.searchText("FontSize"));
        assertTrue(solo.searchText("Quay về tập trước"));
        assertTrue(solo.searchText("Thông tin Novel"));
        assertTrue(solo.searchText("Tập kế tiếp"));
        AutofitTextView ten = (AutofitTextView) solo.getView(R.id.LightNovel);
        if(!ten.equals("Loading...")){
            assertNotNull(ten.getText().toString());
        }
        solo.goBack();
    }

    public void testFunctionTextSize(){
        solo.clickOnText("FontSize");
        assertTrue(solo.waitForDialogToOpen());
        assertTrue(solo.searchText("Nhỏ"));
        assertTrue(solo.searchText("Trung bình"));
        assertTrue(solo.searchText("Lớn"));

        if(solo.isRadioButtonChecked("Trung bình")){
            solo.clickOnRadioButton(2);
        }

        solo.clickOnText("FontSize");
        assertTrue(solo.isRadioButtonChecked("Lớn"));
        solo.clickOnRadioButton(1);
        solo.goBack();
    }

    public void testFuntionBackToNovel(){
        solo.clickOnText("Thông tin Novel");
        solo.assertCurrentActivity("Can't see ThongTinNovel", ThongTinNovel.class);
        assertTrue(solo.waitForActivity(ThongTinNovel.class));
    }

    public void testFunctionChapterNovel(){
        solo.clickOnText("Quay về tập trước");
        assertTrue(solo.waitForText("Không thể quay lại tập trước"));

        solo.clickOnText("Tập kế tiếp");
        solo.assertCurrentActivity("Can't see ThongTinNovel", TruyenNovel.class);
        assertTrue(solo.waitForActivity(TruyenNovel.class));
    }

    public void testBoomMenu(){
        View view = (View) solo.getView(R.id.boom_truyen);
        solo.clickOnView(view);
        solo.clickOnMenuItem("Search");
        solo.assertCurrentActivity("Can't see SearchActivity", SearchActivity.class);
        assertTrue(solo.waitForActivity(SearchActivity.class));
        solo.goBack();
    }

    @Override
    public TruyenNovel getActivity() {
        Intent intent = new Intent();
        intent.putExtra("URL","https://firebasestorage.googleapis.com/v0/b/sakura-novel.appspot.com/o/Date%20a%20Live%2FT%E1%BA%ADp%201-0%20G%E1%BA%B7p%20g%E1%BB%A1.txt?alt=media&token=4bb2855e-3626-40bf-9e65-a6d72afc5158");
        intent.putExtra("LNOVEL","Date a Live");
        setActivityIntent(intent);
        return super.getActivity();
    }
}
