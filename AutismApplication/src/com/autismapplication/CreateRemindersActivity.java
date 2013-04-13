package com.autismapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.widgets.AccordionView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ToggleButton;

public class CreateRemindersActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_reminders);
	}
	
	public void onToggleClicked(View view) {
	    final AccordionView v = (AccordionView) findViewById(R.id.createRemindersAccordionView);

	    boolean on = ((ToggleButton) view).isChecked();
	    
	    if (on) {
	    	v.getWrappedChildren()[0].setVisibility(AccordionView.VISIBLE);
	    } else {
	    	v.getWrappedChildren()[0].setVisibility(AccordionView.GONE);
	    }
	    
	}
	
	public void onToggleRepeatClicked(View view) {
	    final AccordionView v = (AccordionView) findViewById(R.id.createRemindersRepeatAccordionView);

	    boolean on = ((ToggleButton) view).isChecked();
	    
	    if (on) {
	    	v.getWrappedChildren()[0].setVisibility(AccordionView.VISIBLE);
	    } else {
	    	v.getWrappedChildren()[0].setVisibility(AccordionView.GONE);
	    }
	    
	}
	
	public void onSave(View view) {
		Intent intent = new Intent(CreateRemindersActivity.this, SingleTaskActivity.class);
		intent.putExtra("remindOnce", true);
		intent.putExtra("remindOnceDate", new Date());
		CreateRemindersActivity.this.startActivity(intent);
	}
}
