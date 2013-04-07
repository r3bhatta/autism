package com.autismapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeScreen extends Activity {
	
	Button mNewTaskButton, mViewAllTasksButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        
        mNewTaskButton = (Button)findViewById(R.id.new_task_button_id);
        mViewAllTasksButton = (Button)findViewById(R.id.view_all_tasks_button_id);
        setButtonListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }
    
    private void setButtonListeners() {
    	mNewTaskButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(HomeScreen.this, SingleTaskActivity.class);
			    HomeScreen.this.startActivity(intent);
			}
    	});
    	
    	mViewAllTasksButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(HomeScreen.this, AllTasksCollapsableViewActivity.class);
			    HomeScreen.this.startActivity(intent);
			}
    	});
    }
    
}
