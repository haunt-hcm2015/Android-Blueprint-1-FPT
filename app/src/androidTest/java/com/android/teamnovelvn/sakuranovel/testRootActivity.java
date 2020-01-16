package com.android.teamnovelvn.sakuranovel;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;

import com.android.teamnovelvn.sakuranovel.Activity.RootActivity;
import com.android.teamnovelvn.sakuranovel.Activity.SearchActivity;
import com.android.teamnovelvn.sakuranovel.Activity.ThongTinNovel;
import com.robotium.solo.Solo;

import me.grantland.widget.AutofitTextView;

/**
 * Created by Akito on 10/12/2016.
 */

public class testRootActivity extends ActivityInstrumentationTestCase2<RootActivity>{
    private RootActivity rootActivity;
    private Solo solo;
    public testRootActivity() {
        super(RootActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
        rootActivity = getActivity();
    }

    public void testAllGUINotNull(){
        solo.sleep(5000);
        assertNotNull(solo.getView(R.id.drawer_layout));
        assertNotNull(solo.getView(R.id.materialViewPager));
        assertNotNull(solo.getView(R.id.gridViewTruyen));
        assertNotNull(solo.getView(R.id.left_drawer));
        assertNotNull(solo.getView(R.id.boom));
        assertNotNull(solo.getView(R.id.progressBar2));
        solo.goBack();
    }
    public void testImageandText(){
        solo.sleep(3000);
        AutofitTextView autofitTextView = (AutofitTextView) solo.getView(R.id.txtTenTruyenChinh);
        if(!autofitTextView.equals("Loading...")){
            assertNotNull(autofitTextView.getText().toString());
        }
        assertNotSame(getActivity().getResources().getDrawable(R.drawable.loading_image).getConstantState(),
                ((ImageView) solo.getView(R.id.imghinhtruyen)).getDrawable().getConstantState());
        solo.goBack();
    }
    public void testBoomMenu(){
        solo.sleep(2000);
        View view = (View) solo.getView(R.id.boom);
        solo.clickOnView(view);
        solo.clickOnMenuItem("Search");
        solo.assertCurrentActivity("Can't see SearchActivity", SearchActivity.class);
        assertTrue(solo.waitForActivity(SearchActivity.class));
        solo.goBack();
    }
    public void testGridView(){
        solo.sleep(3000);
        solo.clickInList(2);
        solo.assertCurrentActivity("Can't see ThongTinNovel", ThongTinNovel.class);
        assertTrue(solo.waitForActivity(ThongTinNovel.class));
        solo.goBack();
    }
}
