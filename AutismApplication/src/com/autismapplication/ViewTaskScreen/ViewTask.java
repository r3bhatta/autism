package com.autismapplication.ViewTaskScreen;

import com.autismapplication.R;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewTask extends Activity {

	private TextView textView;
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_task_screen);
		textView = (TextView) findViewById(R.id.truncatedTextView);
		
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(ViewTask.this, ViewNoteScreen.class);
				startActivity(i);
			}
		});
		
		image = (ImageView) findViewById(R.id.ImageView05);
		image.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//Launch Andriod contacts
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, 1);
				
				//Call phone number
//				Intent callIntent = new Intent(Intent.ACTION_CALL);
//				callIntent.setData(Uri.parse("tel:6502234713"));
//				startActivity(callIntent);
			}
		});
	}
	
	
	//Code for handling contact selected
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	    super.onActivityResult(reqCode, resultCode, data);

	    try {
	        if (resultCode == Activity.RESULT_OK) {
	            Uri contactData = data.getData();
	            Cursor cur = managedQuery(contactData, null, null, null, null);
	            ContentResolver contect_resolver = getContentResolver();

	            if (cur.moveToFirst()) {
	                String id = cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
	                String name = "";
	                String no = "";

	                Cursor phoneCur = contect_resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
	                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);

	                if (phoneCur.moveToFirst()) {
	                    name = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
	                    no = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	                }

	                Log.e("Phone no & name :***: ", name + " : " + no);
	                //txt.append(name + " : " + no + "\n");

	                id = null;
	                name = null;
	                no = null;
	                phoneCur = null;
	            }
	            contect_resolver = null;
	            cur = null;
	            //                      populateContacts();
	        }
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        Log.e("IllegalArgumentException :: ", e.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	        Log.e("Error :: ", e.toString());
	    }
	}
}
