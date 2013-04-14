package com.autismapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.autismapplication.ViewTaskScreen.ViewTask;
import com.db.Data;
import com.db.DataSource;
import com.db.ReminderData;
import com.db.TaskData;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeScreen extends Activity {

	private ListView mListView;
	private ImageView mImageView;
	private RelativeLayout mRelativeLayoutHeader;
	private TextView mTitleAllTasks;

	private HomeScreen mHomeScreen;
	private DataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);

		mListView = (ListView) findViewById(R.id.listView1);
		mImageView = (ImageView) findViewById(R.id.new_task_plus_img);
		mTitleAllTasks = (TextView) findViewById(R.id.textView1);

		// These are just some example list values these will be replaced by
		// actual tasks
		mDataSource = new DataSource(this);
		mDataSource.openWritableDB();
		
		List<TaskData> allTasks = mDataSource.getAllTaskData();
		
		for(int i=0; i<allTasks.size(); i++) {
			mDataSource.deleteData(allTasks.get(i));
		}
		
		mDataSource.createTaskData("Vacume the house");
		mDataSource.createTaskData("Cook an egg");
		
		allTasks = mDataSource.getAllTaskData();
		
		long id_vacume = allTasks.get(0).getId();
		long id_egg = allTasks.get(1).getId();
		
		mDataSource.createReminderData(id_vacume, new Date(), 0L, "Canada");
		mDataSource.createReminderData(id_egg, new Date(), 0L, "US");
		
		setup();

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
				Intent intent = new Intent(HomeScreen.this,
						SingleTaskActivity.class);
				HomeScreen.this.startActivity(intent);
			}
		});
	}

	public void onResume() {
		super.onResume();
		setup();
	}

	private void setup() {
		List<TaskData> list = mDataSource.getAllTaskData();

		if (list.isEmpty()) {
			mTitleAllTasks.setText("NO Tasks");
		} else {
			mTitleAllTasks.setText("All Tasks");
		}

		final ArrayAdapterImpl adapter = new ArrayAdapterImpl(this,
				android.R.layout.simple_list_item_1, list);

		mHomeScreen = this;
		mListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	/**
	 * Our List Adapter
	 */
	private class ArrayAdapterImpl extends ArrayAdapter<TaskData> {

		HashMap<TaskData, Integer> mIdMap = new HashMap<TaskData, Integer>();
		private final Context mContext;
		private final List<TaskData> mItems;

		public ArrayAdapterImpl(Context context, int textViewResourceId,
				List<TaskData> objects) {

			super(context, textViewResourceId, objects);
			mContext = context;
			mItems = objects;
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			TaskData item = getItem(position);
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

			TaskData taskData = mItems.get(position);
			String task_name = taskData.getName();

			List<ReminderData> reminders = mDataSource
					.queryReminderDataByFk(taskData.getId());
			String reminder_str;
			if (reminders.isEmpty()) {
				reminder_str = "Reminder not set";
			} else {
				long time = reminders.get(0).getDate();
				SimpleDateFormat df = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm:ss a");
				reminder_str = df.format(new Date(time * 1000));
			}

			TextView tt = (TextView) v.findViewById(R.id.textView1);
			TextView bt = (TextView) v.findViewById(R.id.textView3);

			if (tt != null) {
				tt.setText(task_name);
			}
			if (bt != null) {
				bt.setText(reminder_str);
			}

			return v;
		}
	}
}
