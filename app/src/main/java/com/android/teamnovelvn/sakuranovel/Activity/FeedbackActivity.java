package com.android.teamnovelvn.sakuranovel.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.teamnovelvn.sakuranovel.R;

public class FeedbackActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnSend;
    EditText edtMail;
    EditText edtNoidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        setTitle("FeedBack");
        toolBar();
        btnSend = (Button)findViewById(R.id.btnSend);
        edtNoidung = (EditText)findViewById(R.id.edtNoiDungFeedback);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    public void sendMail(){
        Log.i("Send email", "");
        String nd = edtNoidung.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.setPackage("com.google.android.gm");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "sakuranovelfpt@gmail.com" );
        emailIntent.putExtra(Intent.EXTRA_CC, "Sakura Novel" );
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from Sakura Novel");
        emailIntent.putExtra(Intent.EXTRA_TEXT, nd);
        try
        {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending ema...", "");
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_LONG).show();
        }
    }

    public void toolBar(){
        toolbar = (Toolbar)findViewById(R.id.my_toolbar) ;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
