package com.autismapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AllTasksCollapsableViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_tasks_collapsable_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.all_tasks_collapsable_view, menu);
		return true;
	}

}
