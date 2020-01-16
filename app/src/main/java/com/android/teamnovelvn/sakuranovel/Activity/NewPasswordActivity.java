package com.android.teamnovelvn.sakuranovel.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.android.teamnovelvn.sakuranovel.R;

/**
 * Created by Thanh on 11/23/2016.
 */

public class NewPasswordActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText edtOldPassword, edtNewPassword, edtConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        findViewByIds();
        setUpActionBar();
    }

    private void findViewByIds(){
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        edtOldPassword = (EditText) findViewById(R.id.edittext_old_password);
        edtNewPassword = (EditText) findViewById(R.id.edittext_new_password);
        edtConfirmPassword  =(EditText) findViewById(R.id.edittext_confirm_new_password);
    }
    private void setUpActionBar(){
        setSupportActionBar(toolbar);
        ActionBar toForward = getSupportActionBar();
        toForward.setDisplayHomeAsUpEnabled(true);
    }
}
