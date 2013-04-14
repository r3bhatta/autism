package com.autismapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.autismapplication.ViewTaskScreen.ViewTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreen extends Activity {

	private ListView mListView;
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);

		mListView = (ListView) findViewById(R.id.listView1);
		mImageView = (ImageView) findViewById(R.id.new_task_plus_img);

		// These are just some example list values these will be replaced by
		// actual tasks
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
				"OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"Android", "iPhone", "WindowsMobile" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}

		final ArrayAdapterImpl adapter = new ArrayAdapterImpl(this,
				android.R.layout.simple_list_item_1, list);
		
		final HomeScreen mHomeScreen = this;
		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				// here we start the new screen for the specific task clicked
				Context context = getApplicationContext();
				Intent i = new Intent();
				i.setClass(mHomeScreen, ViewTask.class);
				startActivity(i);
			}
		});

		mImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// here we start the add new task screen
				Intent intent = new Intent(HomeScreen.this, SingleTaskActivity.class);
			    HomeScreen.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	/**
	 * Our List Adapter
	 */
	private class ArrayAdapterImpl extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		private final Context mContext;
		private final List<String> mItems;

		public ArrayAdapterImpl(Context context, int textViewResourceId,
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
				v = vi.inflate(R.layout.home_screen_list_row, null);
			}
			String str = mItems.get(position);
			if (str != null) {
				TextView tt = (TextView) v.findViewById(R.id.textView1);
				TextView mt = (TextView) v.findViewById(R.id.textView2);
				TextView bt = (TextView) v.findViewById(R.id.textView3);
				
				if (tt != null) {
					tt.setText(str + " top text");
				}
				if (mt != null) {
					mt.setText(str + " mid text");
				}
				if (bt != null) {
					bt.setText(str + " bottom text");
				}
			}
			return v;
		}
	}
}
