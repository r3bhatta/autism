package com.autismapplication;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import com.animation.ActivitySwitcher;

	public class PictureActivity  extends Activity {
		

		private static final int CAMERA_REQUEST = 1888; 
	    private ImageView imageView;
		public static final int MEDIA_TYPE_IMAGE = 1;
		public static final int MEDIA_TYPE_VIDEO = 2;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_picture);
			this.imageView = (ImageView)this.findViewById(R.id.imageView1);
	 /*
			Button switchActivityBtn = (Button) findViewById(R.id.bSwitchActivity);
			switchActivityBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					switchBackToCreateTask();
				}
			});
			*/
			 // create Intent to take a picture and return control to the calling application
		    //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
		   // fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
		   // intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

		    // start the image capture Intent
		    startActivityForResult(cameraIntent,CAMERA_REQUEST);
			
		}
	 
		
		@Override
		protected void onResume() {
			// animateIn this activity
			ActivitySwitcher.animationIn(findViewById(R.id.container), getWindowManager());
			super.onResume();
		}
		
		 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		        
			 Bitmap photo = null;
			 if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
		            photo = (Bitmap) data.getExtras().get("data"); 
		        }  
		        switchBackToCreateTask(photo);
		    } 
		
		private void switchBackToCreateTask(Bitmap photo) {
			// we only animateOut this activity here.
			// The new activity will animateIn from its onResume() - be sure to implement it.
			final Intent intent = new Intent(getApplicationContext(), SingleTaskActivity.class);
			// disable default animation for new intent
			intent.putExtra("image", photo);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
		}

		/** Create a file Uri for saving an image or video */
		/*
		private static Uri getOutputMediaFileUri(int type){
		      return Uri.fromFile(getOutputMediaFile(type));
		}
		*/

		/** Create a File for saving an image or video */
		/*
		private static File getOutputMediaFile(int type){
		    // To be safe, you should check that the SDCard is mounted
		    // using Environment.getExternalStorageState() before doing this.

		    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
		              Environment.DIRECTORY_PICTURES), "MyCameraApp");
		    // This location works best if you want the created images to be shared
		    // between applications and persist after your app has been uninstalled.

		    // Create the storage directory if it does not exist
		    if (! mediaStorageDir.exists()){
		        if (! mediaStorageDir.mkdirs()){
		            Log.d("MyCameraApp", "failed to create directory");
		            return null;
		        }
		    }

		    // Create a media file name
		    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date(type));
		    File mediaFile;
		    if (type == MEDIA_TYPE_IMAGE){
		        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		        "IMG_"+ timeStamp + ".jpg");
		    } else if(type == MEDIA_TYPE_VIDEO) {
		        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		        "VID_"+ timeStamp + ".mp4");
		    } else {
		        return null;
		    }

		    return mediaFile;
		}
		*/
 
	
	}
	
