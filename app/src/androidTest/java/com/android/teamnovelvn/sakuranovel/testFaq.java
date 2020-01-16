package com.android.teamnovelvn.sakuranovel;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Activity.FaqActivity;
import com.robotium.solo.Solo;

/**
 * Created by Thanh on 12/13/2016.
 */

public class testFaq extends ActivityInstrumentationTestCase2<FaqActivity> {
    Solo solo;

    public testFaq(){
        super(FaqActivity.class);
    }
    protected void setUp() throws Exception{
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
    }
    public void testFaq(){
        assertNotNull(solo.getView(R.id.txtvFAQ));
        assertNotNull(solo.getView(R.id.txtvFAQFOR));
        assertNotNull(solo.getView(R.id.textview1));
        assertNotNull(solo.getView(R.id.textview2));
        assertNotNull(solo.getView(R.id.textview3));
        assertNotNull(solo.getView(R.id.textview4));
        assertNotNull(solo.getView(R.id.textview5));
        assertNotNull(solo.getView(R.id.textview6));
        assertNotNull(solo.getView(R.id.textview7));
        assertNotNull(solo.getView(R.id.textview8));
        solo.goBack();
    }

    public void testText(){
        TextView txt1 = (TextView) solo.getView(R.id.textview1);
        TextView txt2 = (TextView) solo.getView(R.id.textview2);
        TextView txt3 = (TextView) solo.getView(R.id.textview3);
        TextView txt4 = (TextView) solo.getView(R.id.textview4);
        TextView txt5 = (TextView) solo.getView(R.id.textview5);
        TextView txt6 = (TextView) solo.getView(R.id.textview6);
        TextView txt7 = (TextView) solo.getView(R.id.textview7);
        TextView txt8 = (TextView) solo.getView(R.id.textview8);

        assertEquals("1.Tại sao khi tôi mở ứng dụng không thấy hiện gì cả.", txt1.getText());
        assertEquals("-Bạn phải đảm bảo điện thoại có kết nối wifi hoặc 3G, và đợi vài giây nó sẽ hiện lên hình ảnh và tên truyện cho bạn", txt2.getText());
        assertEquals("2.Tôi có thể thay đổi kích cỡ chữ khi đọc truyện không, nó hơi nhỏ so với tôi", txt3.getText());
        assertEquals("-Bạn có thể thay đổi dựa vào 4 lựa chọn chúng tôi đã đề ra,'Nhỏ','Bình thường','Lớn','Cực lớn', ở ngay tai giao diện đọc truyện", txt4.getText());
        assertEquals("3.Tôi có thể Download truyện về để khi tôi không có mạng để xem được không?", txt5.getText());
        assertEquals("-Yes,Bạn có thể download truyện về, nhưng muốn download được bạn phải đăng nhập vào ứng dụng của chúng tôi", txt6.getText());
        assertEquals("4.Truyện tôi đang theo dõi đã bị xóa,tai sao?", txt7.getText());
        assertEquals("-Vì 1 vài lí do, bạn có thể gửi Feedback về cho chúng tôi tên truyện đã bị xóa(hoặc chap nào đó),chúng tôi sẽ cập nhật lại sớm nhất,chúng tôi thành thật xin lỗi vì sự cố này", txt8.getText());

        solo.goBack();
    }
}
