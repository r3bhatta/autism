package com.autismapplication;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


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
	
	private LinearLayout picturesList;
	private LinearLayout NotesList;
	private LinearLayout ContactsList;
	ArrayList<String> listOfNoteNames= new ArrayList<String>();
	ArrayList<String> listOfNoteDescription= new ArrayList<String>();
	ArrayList<String> listOfPictureNames= new ArrayList<String>();
	ArrayList<String> listOfContactsNames= new ArrayList<String>();
	ArrayList<String> listOfContactsPhones= new ArrayList<String>();
	
	
	/*new ones*/
	public static ArrayList<ArrayList<String>> notesContainer = new ArrayList<ArrayList<String>>();
	
	public static ArrayList<ArrayList<String>> contactsContainer = new ArrayList<ArrayList<String>>();
	
	public static ArrayList<String> picturesContainerList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_task);
		
	    final AccordionView v = (AccordionView) findViewById(R.id.accordion_view);

	    /*
	    LinearLayout ll = (LinearLayout) v.findViewById(R.id.example_get_by_id);
	    TextView tv = new TextView(this);
	    tv.setText("Added in runtime...");
	    ll.addView(tv);
	    */
	    
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
				animatedContactsActivity();
			}
		});
		
		Button addRemindersBtn = (Button) findViewById(R.id.addReminders);
		addRemindersBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//switchBackToCreateTask("Save");
		
				Intent intent = new Intent(SingleTaskActivity.this, CreateRemindersActivity.class);
				SingleTaskActivity.this.startActivity(intent);
			}
		});
		
		Button saveNoteBtn = (Button) findViewById(R.id.saveText);
		saveNoteBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// save to DB here
				Intent intent = new Intent(SingleTaskActivity.this, HomeScreen.class);
				SingleTaskActivity.this.startActivity(intent);
				
				
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
	protected void onResume() {
		// animateIn this activity
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
		super.onResume();
		Intent data = getIntent();
	

		buildNotesList();
		buildPicturesList();
		buildContactsList();
		
		
	}
	
	private void buildPicturesList( ) {
		
		picturesList = (LinearLayout) findViewById(R.id.imagesList);
		picturesList.removeAllViews();
		
		
		
		//String[] numberOfPictures = new String[listOfPictureNames.size()];
		final PictureArrayAdapterImpl adapter = new PictureArrayAdapterImpl(this,
				android.R.layout.simple_list_item_1, picturesContainerList);
		
		for (int j = 0; j < adapter.getCount(); j++) {
			  View item = adapter.getView(j, null, null);
			  picturesList.addView(item);
		}
	}
	
	private void buildNotesList( ) {
		
		NotesList = (LinearLayout) findViewById(R.id.notesList);
		NotesList.removeAllViews();
		listOfNoteNames.clear();
		listOfNoteDescription.clear();
		for(int i = 0 ; i < notesContainer.size() ; i++) {
		
			//Toast.makeText(getApplicationContext(), "in loop", Toast.LENGTH_SHORT).show();
			List<String> note = notesContainer.get(i);
			listOfNoteNames.add(note.get(0));
			listOfNoteDescription.add(note.get(1));
		}
		
		
		String[] numberOfNotes = new String[listOfNoteNames.size()];
		final NotesArrayAdapterImpl adapter = new NotesArrayAdapterImpl(this,
				android.R.layout.simple_list_item_1, listOfNoteNames);
		
		for (int j = 0; j < adapter.getCount(); j++) {
			 
			  View item = adapter.getView(j, null, null);
			  NotesList.addView(item);
		}
		
	}
	
	private void buildContactsList() {
		
		ContactsList = (LinearLayout) findViewById(R.id.contactsList);
		ContactsList.removeAllViews();
		listOfContactsNames.clear();
		listOfContactsPhones.clear();
		
		for(int i = 0 ; i < contactsContainer.size() ; i++) {
			
			//Toast.makeText(getApplicationContext(), "in loop", Toast.LENGTH_SHORT).show();
			List<String> contact = contactsContainer.get(i);
			listOfContactsNames.add(contact.get(0));
			listOfContactsPhones.add(contact.get(1));
		}
		
		
		final ContactsArrayAdapterImpl adapter = new ContactsArrayAdapterImpl(this,
				android.R.layout.simple_list_item_1, listOfContactsNames);
		
		for (int j = 0; j < adapter.getCount(); j++) {
		
			//Toast.makeText(getApplicationContext(), "adding contacts in array", Toast.LENGTH_SHORT).show();
			  View item = adapter.getView(j, null, null);
			  ContactsList.addView(item);
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
				startActivity(intent);//animatedVideoActivity();
			}
		});
	}
	
	private void animatedContactsActivity() {
		// we only animateOut this activity here.
		// The new activity will animateIn from its onResume() - be sure to implement it.
		final Intent intent = new Intent(getApplicationContext(), NewContactsActivity.class);
		// disable default animation for new intent
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				startActivity(intent);
			}
		});
	}
	
	private class NotesArrayAdapterImpl extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		private final Context mContext;
		private final List<String> mItems;
		
		public NotesArrayAdapterImpl(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			mContext = context;
			mItems = objects;
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.new_task_screen_notes_list_row, null);
			}
			String str = mItems.get(position);
			if (str != null) {
				
				TextView noteName = (TextView) v.findViewById(R.id.noteName);
				noteName.setText(str);
				
				ImageButton deleteNoteButton = (ImageButton) v.findViewById(R.id.deleteNoteButton);
				/*set the name of the note to corresponds to as a tag*/
				deleteNoteButton.setTag(str);
				deleteNoteButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(),"Note deleted", Toast.LENGTH_SHORT).show();
						
						
						  for(int j = 0 ; j < notesContainer.size() ; j++) {
							  
							//  Toast.makeText(getApplicationContext(), notesContainer.get(j).get(0), Toast.LENGTH_SHORT).show();
							//  Toast.makeText(getApplicationContext(), (String)v.getTag(), Toast.LENGTH_SHORT).show();
							  
							  if(notesContainer.get(j).get(0).equals((String)v.getTag()) ) {
								  notesContainer.remove(j);
								  
								  buildNotesList();
							  }
							  
						  }
						  						  
					}
				});

			}
			return v;
		}

	}
	
	private class ContactsArrayAdapterImpl extends ArrayAdapter<String> {
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		private final Context mContext;
		private final List<String> mItems;
		
		public ContactsArrayAdapterImpl(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			mContext = context;
			mItems = objects;
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.new_task_screen_contacts_list_row, null);
			}
			
			String str = mItems.get(position);
			//Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
			if (str != null) {
				
				TextView contactName = (TextView) v.findViewById(R.id.contactName);
				contactName.setText(str);
				
				ImageButton deleteNoteButton = (ImageButton) v.findViewById(R.id.deleteContactsButton);
				/*set the name of the note to corresponds to as a tag*/
				deleteNoteButton.setTag(str);
				deleteNoteButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(),"Contact deleted", Toast.LENGTH_SHORT).show();
						for(int j = 0 ; j < contactsContainer.size() ; j++) {
							  if(contactsContainer.get(j).get(0).equals((String)v.getTag()) ) {
								  contactsContainer.remove(j);
								 
								  buildContactsList();
							  }
						}	
					
					}
				});

			}
			return v;
		}

	}
	
	private class PictureArrayAdapterImpl extends ArrayAdapter<String> {
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		private final Context mContext;
		private final List<String> mItems;

		public PictureArrayAdapterImpl(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			mContext = context;
			mItems = objects;
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.new_task_screen_picture_list_row, null);
			}
			String str = mItems.get(position);
			if (str != null) {
				File imageFile = new File(str);
				// file was saved properly
				if(imageFile.exists()) {
					Bitmap myBitmap = BitmapFactory.decodeFile(str);
					
					ImageView myImage = (ImageView) v.findViewById(R.id.imageView1);
					myImage.setImageBitmap(myBitmap);
					myImage.getLayoutParams().height = 150;
					myImage.getLayoutParams().width = 150;
					
				} else {
					// give some stock image ?
				}
				
				ImageButton deleteButton = (ImageButton) v.findViewById(R.id.deletePhotoButton);
				/*set the path to the image it corresponds to as a tag*/
				deleteButton.setTag(str);
				deleteButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "Picture deleted", Toast.LENGTH_SHORT).show();
						
						File file = new File((String)v.getTag());
						if(file.exists())
						{
							  file.delete();
							  sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
							            Uri.parse("file://" +  Environment.getExternalStorageDirectory())));
							  
							  for(int j = 0 ; j < picturesContainerList.size() ; j++) {
								  if(picturesContainerList.get(j).equals((String)v.getTag()) ) {
									  picturesContainerList.remove(j);
									  buildPicturesList();
								  }
							  }
							  
						}
					}
				});

			}
			return v;
		}
	}
}
