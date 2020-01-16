package com.android.teamnovelvn.sakuranovel;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.android.teamnovelvn.sakuranovel.Activity.SearchActivity;
import com.android.teamnovelvn.sakuranovel.Activity.ThongTinNovel;
import com.robotium.solo.Solo;

import me.grantland.widget.AutofitTextView;

/**
 * Created by Thanh on 12/13/2016.
 */

@SuppressWarnings("ALL")
public class testSearchActivity extends ActivityInstrumentationTestCase2<SearchActivity> {
    Solo solo;

    public testSearchActivity(){
        super(SearchActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testToolbar(){
        assertNotNull(solo.getView(R.id.my_toolbar));
        assertNotNull(solo.getView(R.id.search_view));
        solo.goBack();
    }
    public void testSearchValid(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        //solo.sleep(5000);
        solo.enterText(0,"O");
        assertTrue(solo.searchText("Overlord"));
        solo.clickInList(0);
        solo.assertCurrentActivity("Can't see ThongTinNovel", ThongTinNovel.class);
        assertTrue(solo.waitForActivity(ThongTinNovel.class));

        AutofitTextView ten = (AutofitTextView) solo.getView(R.id.txtTenTruyen);
        assertEquals("Overlord",ten.getText().toString());
        solo.goBack();
    }
    public void testSearchValid1(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        solo.enterText(0,"B");
        assertTrue(solo.searchText("Black Bullet"));
        solo.clickInList(2);
        solo.assertCurrentActivity("Can't see ThongTinNovel", ThongTinNovel.class);
        assertTrue(solo.waitForActivity(ThongTinNovel.class));

        AutofitTextView ten = (AutofitTextView) solo.getView(R.id.txtTenTruyen);
        assertEquals("Black Bullet",ten.getText().toString());
        solo.goBack();
    }
    public void testSearchInvalid(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        solo.enterText(0,"");
        assertTrue(solo.searchText(""));
        solo.goBack();
    }
    public void testSearchInvalid1(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        solo.enterText(0,"12");
        assertTrue(solo.searchText(""));
        solo.goBack();
    }
    public void testSearchInvalid2(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        solo.enterText(0,"a25");
        assertTrue(solo.searchText(""));
        solo.goBack();
    }
    public void testSearchInvalid3(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        solo.enterText(0,"/?");
        assertTrue(solo.searchText(""));
        solo.goBack();
    }
    public void testSearchInvalid4(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        solo.enterText(0,"-152");
        assertTrue(solo.searchText(""));
        solo.goBack();
    }
    public void testSearchInvalid5(){
        View view = (View) solo.getView(R.id.my_toolbar);
        solo.clickOnView(view);
        solo.enterText(0,"a25");
        assertTrue(solo.searchText(""));
        solo.goBack();
    }
}
