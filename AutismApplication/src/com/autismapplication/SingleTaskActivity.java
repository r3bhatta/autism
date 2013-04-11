package com.autismapplication;


import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autismapplication.R;


import com.animation.ActivitySwitcher;
import com.widgets.AccordionView;


public class SingleTaskActivity extends Activity {
	
	public static List<Bitmap> images ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_task);
		
	    final AccordionView v = (AccordionView) findViewById(R.id.accordion_view);

	    LinearLayout ll = (LinearLayout) v.findViewById(R.id.example_get_by_id);
	    TextView tv = new TextView(this);
	    tv.setText("Added in runtime...");
	    //FontUtils.setCustomFont(tv, getAssets());
	    ll.addView(tv);
	    
	    ImageButton addNewNotes = (ImageButton) findViewById(R.id.newNotesButton);
		addNewNotes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				animatedNoteActivity();
			}
		});
		
		ImageButton addNewPhotos = (ImageButton) findViewById(R.id.newPhotosButton);
		addNewPhotos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				animatedPicturesActivity();
			}
		});
		
		ImageButton addNewContacts = (ImageButton) findViewById(R.id.newContactsButton);
		addNewContacts.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				animatedVideoActivity();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_task, menu);
		return true;
	}
	

    @Override 
    protected void onSaveInstanceState (Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	//savedInstanceState.putBoolean("MyBoolean", true);
		//savedInstanceState.putDouble("myDouble", 1.9);
		//savedInstanceState.putInt("MyInt", 1);
		//savedInstanceState.putString("MyString", "Welcome back to Android");
    	/*
		for(int i = 0 ; i < images.size() ; i++){
			Bitmap image = images.get(i);
			savedInstanceState.putParcelable("bitmap" + i, image);
		}
		*/
	   // Context context = getApplicationContext();
	    //  CharSequence text = "Hello toast!";
	    //  int duration = Toast.LENGTH_SHORT;

	      //Toast toast = Toast.makeText(context, text, duration);
	      //toast.show(); 
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
      // Restore UI state from the savedInstanceState.
      // This bundle has also been passed to onCreate.
      //boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
      //double myDouble = savedInstanceState.getDouble("myDouble");
     // int myInt = savedInstanceState.getInt("MyInt");
     // String myString = savedInstanceState.getString("MyString");
      /*
      for(int i = 0 ; i < images.size() ; i++){
			Bitmap image = savedInstanceState.getParcelable("bitmap" + i);
			images.add(image);
		}
      */
    //  Context context = getApplicationContext();
     // CharSequence text = "Hello toast return!";
     // int duration = Toast.LENGTH_SHORT;

      //Toast toast = Toast.makeText(context, text, duration);
      //toast.show(); 
    }

	@Override
	protected void onResume() {
		// animateIn this activity
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
		super.onResume();
		Intent data = getIntent();
		
		if(data.getParcelableExtra("image")!=null)
		{
			Bitmap bitmap = (Bitmap) data.getParcelableExtra("image");
			AccordionView v = (AccordionView) findViewById(R.id.accordion_view);
		    LinearLayout ll = (LinearLayout) v.findViewById(R.id.example_get_by_id);
		    ImageView img = new ImageView(this);
		    img.setImageBitmap(bitmap);
		    ll.addView(img);
		}
	}
 
	private void animatedNoteActivity() {
		// we only animateOut this activity here.
		// The new activity will animateIn from its onResume() - be sure to implement it.
		final Intent intent = new Intent(getApplicationContext(), NewNoteActivity.class);
		// disable default animation for new intent
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				//saveLocalInfoToBundle();
				startActivity(intent);
			}
		});
	}
	
	private void animatedPicturesActivity() {
		// we only animateOut this activity here.
		// The new activity will animateIn from its onResume() - be sure to implement it.
		final Intent intent = new Intent(getApplicationContext(), PictureActivity.class);
		// disable default animation for new intent
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				startActivity(intent);
			}
		});
	}
	
	private void animatedVideoActivity() {
		// we only animateOut this activity here.
		// The new activity will animateIn from its onResume() - be sure to implement it.
		final Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
		// disable default animation for new intent
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				startActivity(intent);
			}
		});
	}
	

}
