package com.autismapplication;



import java.util.ArrayList;

import com.autismapplication.R;
import com.animation.ActivitySwitcher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NewNoteActivity extends Activity {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_note_view);
 
		Button switchActivityBtn = (Button) findViewById(R.id.bSwitchActivity);
		switchActivityBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switchBackToCreateTask("Exit");
			}
		});
		
		Button saveNoteBtn = (Button) findViewById(R.id.SaveNote);
		saveNoteBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				switchBackToCreateTask("Save");
			}
		});
	}
 
	@Override
	protected void onResume() {
		// animateIn this activity
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
		super.onResume();
	}
	/*
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  
	  savedInstanceState.putParcelable("notes", (Parcelable) SingleTaskActivity.notesContainer);
	  /*
	  savedInstanceState.putBoolean("MyBoolean", true);
	  savedInstanceState.putDouble("myDouble", 1.9);
	  savedInstanceState.putInt("MyInt", 1);
	  savedInstanceState.putString("MyString", "Welcome back to Android");
		
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  
	  notesContainer = savedInstanceState.getParcelable("notes");
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
	  /*
	  boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
	  double myDouble = savedInstanceState.getDouble("myDouble");
	  int myInt = savedInstanceState.getInt("MyInt");
	  String myString = savedInstanceState.getString("MyString");
	  
	}
*/
 
	private void switchBackToCreateTask(final String action) {
		// we only animateOut this activity here.
		// The new activity will animateIn from its onResume() - be sure to implement it.
		final Intent intent = new Intent(getApplicationContext(), SingleTaskActivity.class);
		// disable default animation for new intent
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				if(action == "Save") {
					
					EditText noteNameWidget   = (EditText)findViewById(R.id.noteName);
					EditText noteDescriptionWidget   = (EditText)findViewById(R.id.noteDescription);
					String noteNameValue = "" , noteDescriptionValue = "";
					if(noteNameWidget != null ) {
						noteNameValue = noteNameWidget.getText().toString();
						if(noteNameValue.length()==0) {
							noteNameValue = "Untitled Note";
						}
						//intent.putExtra("noteName", noteNameValue);
						
					}
					if(noteDescriptionWidget != null) {
						noteDescriptionValue = noteDescriptionWidget.getText().toString();
						if(noteDescriptionValue.length()==0) {
							noteDescriptionValue = "No Description";
						}
						//intent.putExtra("noteDescription", noteDescriptionValue);
					}
					
					// nothing really went wrong and user hit save
					if(noteNameValue.length()>0 || noteDescriptionValue.length()>0) {
						
						ArrayList<String> note = new ArrayList<String>();
						note.add(noteNameValue);
						note.add(noteDescriptionValue);
						SingleTaskActivity.notesContainer.add(note);
						
						
					}
					
				}
				startActivity(intent);
			}
		});
	}
}

