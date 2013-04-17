
package com.autismapplication.newtask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.autismapplication.R;
import com.autismapplication.NewTaskActivity;
import com.autismapplication.R.id;
import com.autismapplication.R.layout;
import com.widgets.AccordionView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ToggleButton;

public class NewReminderActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
    }

    public void onSave(View view) {
        Intent intent = new Intent(NewReminderActivity.this, NewTaskActivity.class);
        intent.putExtra("remindOnce", true);
        intent.putExtra("remindOnceDate", new Date());
        NewReminderActivity.this.startActivity(intent);
    }
}
