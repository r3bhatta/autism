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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.autismapplication.R;

import com.animation.ActivitySwitcher;
import com.widgets.AccordionView;


public class SingleTaskActivity extends Activity {
	
	private LinearLayout picturesList;
	ArrayList<String> listOfPictureNames= new ArrayList<String>();

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
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
    }

	@Override
	protected void onResume() {
		// animateIn this activity
		ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
		super.onResume();
		Intent data = getIntent();
		
		// some images were added in by the user
		if(data.getStringArrayListExtra("namesOfPictures")!=null) {
			
			listOfPictureNames.addAll(data.getStringArrayListExtra("namesOfPictures"));
			buildPicturesList();
		}
		
		getIntent().removeExtra("namesOfPictures"); 
	}
	
	private void buildPicturesList( ) {
		
		picturesList = (LinearLayout) findViewById(R.id.imagesList);
		picturesList.removeAllViews();
		String[] numberOfPictures = new String[listOfPictureNames.size()];
		final PictureArrayAdapterImpl adapter = new PictureArrayAdapterImpl(this,
				android.R.layout.simple_list_item_1, listOfPictureNames);
		
		for (int j = 0; j < adapter.getCount(); j++) {
			  View item = adapter.getView(j, null, null);
			  picturesList.addView(item);
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
	
	/**
	 * Our Picture List Adapter
	 */
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
						Toast.makeText(getApplicationContext(), (String)v.getTag() + " deleted from file structure", Toast.LENGTH_SHORT).show();
						
						File file = new File((String)v.getTag());
						if(file.exists())
						{
							  file.delete();
							  sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
							            Uri.parse("file://" +  Environment.getExternalStorageDirectory())));
							  
							  for(int j = 0 ; j < listOfPictureNames.size() ; j++) {
								  if(listOfPictureNames.get(j).equals((String)v.getTag()) ) {
									  listOfPictureNames.remove(j);
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
