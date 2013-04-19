
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class NewReminderActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        CheckBox onRemindMeOnDay = (CheckBox) findViewById(R.id.remindOnDay);

        CheckBox onRemindMeAtLocation = (CheckBox) findViewById(R.id.remindOnLocation);

        onRemindMeOnDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RelativeLayout enterReminderDaySection = (RelativeLayout) findViewById(R.id.enterReminderDaySection);

                View enterReminderDayLine = (View) findViewById(R.id.enterReminderDayLine);
                RelativeLayout enterReminderDayRepeatSection = (RelativeLayout) findViewById(R.id.enterReminderDayRepeatSection);

                RelativeLayout enterReminderDayFooter = (RelativeLayout) findViewById(R.id.enterReminderDayFooter);

                int visible = View.GONE;
                if (isChecked)
                    visible = View.VISIBLE;
                enterReminderDaySection.setVisibility(visible);
                enterReminderDayLine.setVisibility(visible);
                enterReminderDayRepeatSection.setVisibility(visible);
                enterReminderDayFooter.setVisibility(visible);
            }
        });

        onRemindMeAtLocation
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        RelativeLayout enterReminderLocationSection = (RelativeLayout) findViewById(R.id.enterReminderLocationSection);

                        View enterReminderLocationLine1Section = (View) findViewById(R.id.enterReminderLocationLine1Section);
                        RelativeLayout enterReminderLocationLeaveSection = (RelativeLayout) findViewById(R.id.enterReminderLocationLeaveSection);
                        View enterReminderLocationLine2Section = (View) findViewById(R.id.enterReminderLocationLine2Section);
                        RelativeLayout enterReminderLocationArriveSection = (RelativeLayout) findViewById(R.id.enterReminderLocationArriveSection);

                        RelativeLayout enterReminderLocationFooter = (RelativeLayout) findViewById(R.id.enterReminderLocationFooter);

                        int visible = View.GONE;
                        if (isChecked)
                            visible = View.VISIBLE;
                        enterReminderLocationSection.setVisibility(visible);
                        enterReminderLocationLine1Section.setVisibility(visible);
                        enterReminderLocationLeaveSection.setVisibility(visible);
                        enterReminderLocationLine2Section.setVisibility(visible);
                        enterReminderLocationArriveSection.setVisibility(visible);
                        enterReminderLocationFooter.setVisibility(visible);
                    }
                });

    }

    public void onSave(View view) {
        Intent intent = new Intent(NewReminderActivity.this, NewTaskActivity.class);

        /*
         * EditText reminderDate = (EditText) findViewById(R.id.reminderDate);
         * EditText reminderLocation = (EditText)
         * findViewById(R.id.reminderLocation); CheckBox onLeaveRemind =
         * (CheckBox) findViewById(R.id.checkbox_leave); CheckBox onArriveRemind
         * = (CheckBox) findViewById(R.id.checkbox_arrive);
         */

        CheckBox onRemindMeOnDay = (CheckBox) findViewById(R.id.remindOnDay);

        CheckBox onRemindMeAtLocation = (CheckBox) findViewById(R.id.remindOnLocation);

        intent.putExtra("remindOnDay", false);
        intent.putExtra("remindAtLocation", false);

        // dont bother if not checked
        if (onRemindMeOnDay.isChecked()) {

            EditText reminderDate = (EditText) findViewById(R.id.reminderDate);
            Spinner remindFrequency = (Spinner) findViewById(R.id.repeat_spinner);

            intent.putExtra("remindOnDay", true);
            intent.putExtra("remindDate", reminderDate.getText().toString());
            intent.putExtra("remindFrequency", remindFrequency.getSelectedItem().toString());
        }

        // dont bother if not checked
        if (onRemindMeAtLocation.isChecked()) {

            intent.putExtra("remindAtLocation", true);
            EditText reminderLocation = (EditText) findViewById(R.id.reminderLocation);
            CheckBox onLeaveRemind = (CheckBox) findViewById(R.id.checkbox_leave);
            CheckBox onArriveRemind = (CheckBox) findViewById(R.id.checkbox_arrive);

            intent.putExtra("remindLocation", reminderLocation.getText().toString());
            intent.putExtra("remindOnLeave", onLeaveRemind.isChecked());
            intent.putExtra("remindOnArrive", onArriveRemind.isChecked());
        }

        NewReminderActivity.this.startActivity(intent);
    }
}
