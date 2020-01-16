package com.android.teamnovelvn.sakuranovel;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.android.teamnovelvn.sakuranovel.Activity.GenreAnimeActivity;
import com.android.teamnovelvn.sakuranovel.Activity.ThongTinNovel;
import com.android.teamnovelvn.sakuranovel.Activity.TypeActivity;
import com.robotium.solo.Solo;

/**
 * Created by Thanh on 12/13/2016.
 */

public class testTypeActivity extends ActivityInstrumentationTestCase2<TypeActivity> {
    Solo solo;
    public testTypeActivity(){
        super(TypeActivity.class);
    }

    protected void setUp() throws Exception{
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void testButtonTontai(){
        assertNotNull(solo.getView(R.id.btnFbAction));
        assertNotNull(solo.getView(R.id.btnFbAdventure));
        assertNotNull(solo.getView(R.id.btnFbComedy));
        assertNotNull(solo.getView(R.id.btnFbDrama));
        assertNotNull(solo.getView(R.id.btnFbEcchi));
        assertNotNull(solo.getView(R.id.btnFbFantasy));
        assertNotNull(solo.getView(R.id.btnFbHarem));
        assertNotNull(solo.getView(R.id.btnFbHorror));
        assertNotNull(solo.getView(R.id.btnFbLolicon));
        assertNotNull(solo.getView(R.id.btnFbMagic));
        assertNotNull(solo.getView(R.id.btnFbTragedy));
        assertNotNull(solo.getView(R.id.btnFbSupernatural));
        assertNotNull(solo.getView(R.id.btnFbShounen));
        assertNotNull(solo.getView(R.id.btnFbShoujouSOL));
        assertNotNull(solo.getView(R.id.btnFbSeinen));
        assertNotNull(solo.getView(R.id.btnFbSciFi));
        assertNotNull(solo.getView(R.id.btnFbSchoolLife));
        assertNotNull(solo.getView(R.id.btnFbRomance));
        assertNotNull(solo.getView(R.id.btnFbMature));
        assertNotNull(solo.getView(R.id.btnFbPsy));
        assertNotNull(solo.getView(R.id.btnFbMystery));
        assertNotNull(solo.getView(R.id.btnFbMecha));
        solo.goBack();
    }
    public void testButtonNoiDung(){
        assertTrue(solo.searchText("Action"));
        assertTrue(solo.searchText("Adventure"));
        assertTrue(solo.searchText("Comedy"));
        assertTrue(solo.searchText("Drama"));
        assertTrue(solo.searchText("Ecchi"));
        assertTrue(solo.searchText("Fantasy"));
        assertTrue(solo.searchText("Harem"));
        assertTrue(solo.searchText("Horror"));
        assertTrue(solo.searchText("Lolicon"));
        assertTrue(solo.searchText("Magic"));
        assertTrue(solo.searchText("Tragedy"));
        assertTrue(solo.searchText("Supernatural"));
        assertTrue(solo.searchText("Shounen"));
        assertTrue(solo.searchText("Shoujo Slice Of Life"));
        assertTrue(solo.searchText("Seinen"));
        assertTrue(solo.searchText("Sci-Fi"));
        assertTrue(solo.searchText("School Life"));
        assertTrue(solo.searchText("Romance"));
        assertTrue(solo.searchText("Mature"));
        assertTrue(solo.searchText("Psy"));
        assertTrue(solo.searchText("Mystery"));
        assertTrue(solo.searchText("Mecha"));
        solo.goBack();
    }
    public  void testButton(){
        View v = (View)solo.getView(R.id.btnFbAction);
        solo.clickOnView(v);
        solo.assertCurrentActivity("Can't see GenreActivity", GenreAnimeActivity.class);
        assertTrue(solo.waitForActivity(GenreAnimeActivity.class));
        solo.clickInList(0);
        solo.assertCurrentActivity("Can't see TruyenActivity", ThongTinNovel.class);
        assertTrue(solo.waitForActivity(ThongTinNovel.class));
        solo.goBack();
    }
    public void testButon1(){
        View v = (View)solo.getView(R.id.btnFbHarem);
        solo.clickOnView(v);
        solo.assertCurrentActivity("Can't see GenreActivity", GenreAnimeActivity.class);
        assertTrue(solo.waitForActivity(GenreAnimeActivity.class));
        solo.clickInList(0);
        solo.assertCurrentActivity("Can't see TruyenActivity", ThongTinNovel.class);
        assertTrue(solo.waitForActivity(ThongTinNovel.class));
        solo.goBack();
    }
    public void testButton2(){
        View v = (View)solo.getView(R.id.btnFbEcchi);
        solo.clickOnView(v);
        solo.assertCurrentActivity("Can't see GenreActivity", GenreAnimeActivity.class);
        assertTrue(solo.waitForActivity(GenreAnimeActivity.class));
        solo.clickInList(0);
        solo.assertCurrentActivity("Can't see TruyenActivity", ThongTinNovel.class);
        assertTrue(solo.waitForActivity(ThongTinNovel.class));
        solo.goBack();
    }
}
