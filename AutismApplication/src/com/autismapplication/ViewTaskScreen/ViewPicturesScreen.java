package com.autismapplication.ViewTaskScreen;

import java.util.ArrayList;
import java.util.List;

import com.autismapplication.ActivitySwipeDetector;
import com.autismapplication.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPicturesScreen extends Activity {

	private Button closeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pictures_screen);
		
		ActivitySwipeDetector swipeDetector = new ActivitySwipeDetector(this);
		LinearLayout baseLayout = (LinearLayout) this.findViewById(R.id.BaseLayout);
		baseLayout.setOnTouchListener(swipeDetector);
		
		closeButton = (Button) findViewById(R.id.CloseButton);
		
		closeButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(ViewPicturesScreen.this, ViewTask.class);
				startActivity(i);
			}
		});
	}
	
	public void onRightToLeftSwipe(){
		
	}
	
	public void onLeftToRightSwipe(){
		
	}
}