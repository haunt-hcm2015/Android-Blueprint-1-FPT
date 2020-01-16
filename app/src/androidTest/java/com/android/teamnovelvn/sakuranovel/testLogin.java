package com.android.teamnovelvn.sakuranovel;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.Activity.LoginLibActivity;
import com.google.android.gms.common.SignInButton;
import com.robotium.solo.Solo;

import shem.com.materiallogin.MaterialLoginView;

/**
 * Created by TRUNGNGUYEN on 12/15/2016.
 */
public class testLogin extends ActivityInstrumentationTestCase2<LoginLibActivity> {
    private Solo solo;
    private LoginLibActivity loginActivity;
    public testLogin() {
        super(LoginLibActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
        loginActivity = getActivity();
    }

    public void testButtonGoogleNotNull() {
        assertNotNull(solo.getView(R.id.button_google));
    }
    public void testButtonGoogleContent() {
        SignInButton btnSignInGoogle = (SignInButton) solo.getView(R.id.button_google);
        assertTrue(btnSignInGoogle.isEnabled());
    }

    public void testTextViewTitleNotNull() {
        assertNotNull(solo.getView(R.id.textview_title));
    }
    public void testTextViewTitleContent() {
        assertTrue(solo.searchText("JOIN US"));
    }

    public void testMaterialLoginViewNotNull() {
        assertNotNull(solo.getView(R.id.login));
    }
    public void testMaterialLoginViewContent() {
        final MaterialLoginView login
                = (MaterialLoginView) solo.getView(R.id.login);
        assertTrue(login.isEnabled());
    }

    public void testTextViewLoginNotNull() {
        assertTrue(solo.searchText("Login"));
    }


    public void testLoginEvent1() {
        solo.enterText(0, "");
        solo.enterText(1, "123");
        solo.clickOnText("Sign In");
        solo.waitForActivity(getInstrumentation().toString());
        assertTrue(solo.searchText("User name can't be empty"));
    }

    public void testLoginEvent2() {
        solo.enterText(0, "123");
        solo.enterText(1, "");
        solo.clickOnText("Sign In");
        solo.waitForActivity(getInstrumentation().toString());
        assertTrue(solo.searchText("Password can't be empty"));
    }

    public void testLoginEvent3() {
        solo.clickOnImage(0);
        solo.enterText(2, "haunt@gmail.com");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        solo.clickOnImage(0);

        solo.enterText(0, "haunt@gmail.com");
        solo.enterText(1, "123456");
        solo.clickOnText("Sign In");
        assertTrue(solo.searchText("Login success!"));
        solo.waitForActivity(getInstrumentation().toString());
    }

    public void testLoginEvent4() {
        solo.enterText(0, "haun.com");
        solo.enterText(1, "123456");
        solo.clickOnText("Sign In");

        assertTrue(solo.searchText("Login failed. You can wrong password or email.\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
    }

    public void testSignUpEvent1() {
        solo.clickOnImage(0);
        solo.enterText(2, "");
        solo.enterText(3, "123");
        solo.enterText(2, "123");
        solo.clickOnText("SIGN UP");
      //  solo.waitForActivity(getInstrumentation().toString());
        assertTrue(solo.searchText("User name can't be empty"));
        solo.goBack();
    }

    public void testSignUpEvent2() {
        solo.clickOnImage(0);
        solo.enterText(2, "abc@gmail.com");
        solo.enterText(3, "");
        solo.enterText(2, "123");
        solo.clickOnText("SIGN UP");
        solo.waitForActivity(getInstrumentation().toString());
        assertTrue(solo.searchText("Password can't be empty"));
        solo.goBack();
    }

    public void testSignUpEvent3() {
        solo.clickOnImage(0);
        solo.enterText(2, "abc@gmail.com");
        solo.enterText(3, "123");
        solo.enterText(2, "");
        solo.clickOnText("SIGN UP");
        solo.waitForActivity(getInstrumentation().toString());
        assertTrue(solo.searchText("Passwords are different"));
        solo.goBack();
    }

    public void testSignUpEvent4() {
        solo.clickOnImage(0);
        solo.enterText(2, "abc@gmail.com");
        solo.enterText(3, "123");
        solo.enterText(2, "123");
        solo.clickOnText("SIGN UP");
        assertTrue(solo.waitForText("This email was exist or transmission error and the password more 6 character" + ".\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }

    public void testSignUpEvent5() {
        solo.clickOnImage(0);
        solo.enterText(2, "abc@gmail.com");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");
        assertTrue(solo.waitForText("Register success!"));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();

    }

    public void testSignUpEvent6() {
        solo.clickOnImage(0);
        solo.enterText(2, "abcd@gmail.com");
        solo.enterText(3, "12345678");
        solo.enterText(2, "12345678");
        solo.clickOnText("SIGN UP");
        assertTrue(solo.waitForText("Register success!"));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();

    }

    public void testSignUpEventTrungLap1() {
        solo.clickOnImage(0);
        solo.enterText(2, "a1@gmail.com");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        assertTrue(solo.searchText("Register success!"));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }

    public void testSignUpEventTrungLap1_1() {
        solo.clickOnImage(0);
        solo.enterText(2, "a1@gmail.com");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        assertTrue(solo.searchText("This email was exist or transmission error and the password more 6 character" + ".\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }

    public void testSignUpEventTrungLap2() {
        solo.clickOnImage(0);
        solo.enterText(2, "aaaaaaa1@gmail.com");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        assertTrue(solo.searchText("Register success!"));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }

    public void testSignUpEventTrungLap2_1() {
        solo.clickOnImage(0);
        solo.enterText(2, "aaaaaaa1@gmail.com");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        assertTrue(solo.searchText("This email was exist or transmission error and the password more 6 character" + ".\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }



    public void testSignUpEvent9() {
        solo.clickOnImage(0);
        solo.enterText(2, "abc@gmail.");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        assertTrue(solo.searchText("This email was exist or transmission error and the password more 6 character" + ".\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }

    public void testSignUpEvent10() {
        solo.clickOnImage(0);
        solo.enterText(2, "abc@");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");
        assertTrue(solo.searchText("This email was exist or transmission error and the password more 6 character" + ".\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }

    public void testSignUpEvent11() {
        solo.clickOnImage(0);
        solo.enterText(2, "abc");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        assertTrue(solo.searchText("This email was exist or transmission error and the password more 6 character" + ".\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }

    public void testSignUpEvent12() {
        solo.clickOnImage(0);
        solo.enterText(2, "123");
        solo.enterText(3, "123456");
        solo.enterText(2, "123456");
        solo.clickOnText("SIGN UP");

        assertTrue(solo.searchText("This email was exist or transmission error and the password more 6 character" + ".\nPlease try again."));
        solo.waitForActivity(getInstrumentation().toString());
        solo.goBack();
    }



    /*--------------- Test password -----------------*/
    public void testTextViewForgotPasswordNotNull() {
        assertNotNull(solo.getView(R.id.textview_forgot_password));
    }
    public void testTextViewForgotPasswordContent() {
        assertTrue(solo.searchText("Forgot password"));
    }

    public void testTextViewForgotPasswordEvent1() {

        TextView txtForgotPassword
                = (TextView) solo.getView(R.id.textview_forgot_password);
        solo.clickOnView(txtForgotPassword);
        solo.enterText(0, "nguyntrung6789@gmail.com");
        solo.clickOnButton("Reset password");
        assertTrue(solo.searchText("We have sent you instructions to reset your password!"));
    }


    public void testTextViewForgotPasswordEvent2() {

        TextView txtForgotPassword
                = (TextView) solo.getView(R.id.textview_forgot_password);
        solo.clickOnView(txtForgotPassword);
        solo.enterText(0, "123");
        solo.clickOnButton("Reset password");

        assertTrue(solo.searchText("Failed to send reset email!"));
    }

    public void testTextViewForgotPasswordEvent3() {

        TextView txtForgotPassword
                = (TextView) solo.getView(R.id.textview_forgot_password);
        solo.clickOnView(txtForgotPassword);
        solo.enterText(0, "-123");
        solo.clickOnButton("Reset password");
      //  solo.waitForActivity(getInstrumentation().toString());
        assertTrue(solo.searchText("Failed to send reset email!"));
    }

    public void testTextViewForgotPasswordEvent4() {

        TextView txtForgotPassword
                = (TextView) solo.getView(R.id.textview_forgot_password);
        solo.clickOnView(txtForgotPassword);
        solo.enterText(0, "!@#");
        solo.clickOnButton("Reset password");
      //  solo.waitForActivity(getInstrumentation().toString());
        assertTrue(solo.searchText("Failed to send reset email!"));
    }
}
