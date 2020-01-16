package com.android.teamnovelvn.sakuranovel;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;

import com.android.teamnovelvn.sakuranovel.Activity.InfoActivity;
import com.robotium.solo.Solo;

/**
 * Created by Akito on 11/12/2016.
 */

@SuppressWarnings("ALL")
public class testInfoApp extends ActivityInstrumentationTestCase2<InfoActivity>{
    Solo solo;
    public testInfoApp() {
        super(InfoActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }
    public void testALLGUI(){
        assertNotNull(solo.getView(R.id.imghinhtruyenInfo));
        assertNotNull(solo.getView(R.id.txtTenTruyenChinh));
        assertNotNull(solo.getView(R.id.textView5));
        assertNotNull(solo.getView(R.id.textView4));
        assertNotNull(solo.getView(R.id.textView2));
        assertNotNull(solo.getView(R.id.textView3));
        solo.goBack();
    }
    public void testImageSame(){
        assertEquals(getActivity().getResources().getDrawable(R.drawable.logo).getConstantState(),
                ((ImageView) solo.getView(R.id.imghinhtruyenInfo)).getDrawable().getConstantState());
        solo.goBack();
    }
    public void testText(){
        assertTrue(solo.searchText("App Name: Sakura Novel "));
        assertTrue(solo.searchText("Team: Nhóm 5"));
        assertTrue(solo.searchText("Yêu cầu thiết bị: Android 4.4+"));
        assertTrue(solo.searchText("Version: 1.0.0"));
        assertTrue(solo.searchText("Copyright 2016"));
        solo.goBack();
    }
}
