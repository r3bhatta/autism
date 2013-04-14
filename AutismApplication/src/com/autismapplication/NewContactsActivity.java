package com.autismapplication;

import java.util.ArrayList;

import com.animation.ActivitySwitcher;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewContactsActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, 1);
	}
	
	//Code for handling contact selected
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	    super.onActivityResult(reqCode, resultCode, data);
	    String name = "";
	    String no = "";
	    try {
	        if (resultCode == Activity.RESULT_OK) {
	            Uri contactData = data.getData();
	            Cursor cur = managedQuery(contactData, null, null, null, null);
	            ContentResolver contect_resolver = getContentResolver();
	            
	            if (cur.moveToFirst()) {
	                String id = cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
	               
	                Cursor phoneCur = contect_resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
	                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);

	                if (phoneCur.moveToFirst()) {
	                    name = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
	                    no = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	                }

	                //Log.e("Phone no & name :***: ", name + " : " + no);
	                id = null;
	                //no = null;
	                phoneCur = null;
	                
	            }
	            contect_resolver = null;
	            cur = null;
	            switchBackToCreateTask(name,no);
	        }
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        Log.e("IllegalArgumentException :: ", e.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	        Log.e("Error :: ", e.toString());
	    } 
	}

	private void switchBackToCreateTask(final String ContactName, final String ContactPhone) {
		// we only animateOut this activity here.
		// The new activity will animateIn from its onResume() - be sure to implement it.
		final Intent intent = new Intent(getApplicationContext(), SingleTaskActivity.class);
		// disable default animation for new intent
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		ActivitySwitcher.animationOut(findViewById(R.id.container), getWindowManager(), new ActivitySwitcher.AnimationFinishedListener() {
			@Override
			public void onAnimationFinished() {
				Toast.makeText(getApplicationContext(), ContactName + "added", Toast.LENGTH_SHORT).show();
				
				ArrayList<String> contact = new ArrayList<String>();
				contact.add(ContactName);
				contact.add(ContactPhone);
				SingleTaskActivity.contactsContainer.add(contact);
				
				startActivity(intent);
			}
		});
	}
}
