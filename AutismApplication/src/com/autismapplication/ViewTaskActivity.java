
package com.autismapplication;

import java.util.List;

import com.autismapplication.R;
import com.autismapplication.viewtask.ViewNoteFragmentActivity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewTaskActivity extends Activity {

    private TextView titleText;
    private TextView truncatedText;
    private String titleTextString;
    private String truncatedTextString;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;

    private ImageView contact1;
    private ImageView contact2;
    private ImageView contact3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        InitializeContent();

        truncatedText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(ViewTaskActivity.this, ViewNoteFragmentActivity.class);
                startActivity(i);
            }
        });

        contact1 = (ImageView) findViewById(R.id.contact1);
        contact1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                CallPhone("tel:6502234713");

            }
        });

        contact2 = (ImageView) findViewById(R.id.contact2);
        contact2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                CallPhone("tel:6478615385");

            }
        });

        contact3 = (ImageView) findViewById(R.id.contact3);
        contact3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                CallPhone("tel:4158674722");

            }
        });
    }

    private void InitializeContent() {
        titleText = (TextView) findViewById(R.id.ViewTaskTitle);
        titleTextString = "Vacuuming";
        titleText.setText(titleTextString);
        truncatedTextString = "Vacuum the floor today! Get the stuff from the closet and make sure to get the corners!";
        truncatedText = (TextView) findViewById(R.id.TruncatedText);
        truncatedText.setText(truncatedTextString);
    }

    private void CallPhone(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(number));
        startActivity(callIntent);
    }
}
