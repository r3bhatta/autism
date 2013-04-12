package com.autismapplication.ViewTaskScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.autismapplication.ActivitySwipeDetector;
import com.autismapplication.R;
import com.autismapplication.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTask extends Activity {

	private TextView textView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_task_screen);
		textView = (TextView) findViewById(R.id.truncatedTextView);
		
		textView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(ViewTask.this, ViewNoteScreen.class);
				startActivity(i);
			}
		});
	}
}
