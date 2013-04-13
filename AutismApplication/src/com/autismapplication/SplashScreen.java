package com.autismapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;

public class SplashScreen extends Activity {

	protected int mSplashTimeoutMs = 3500;
	private Thread mSplashThread;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		final SplashScreen mSplashScreen = this;

		mSplashThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(mSplashTimeoutMs);
					}
				} catch (InterruptedException e) {
				} finally {
					finish();
					Intent i = new Intent();
					i.setClass(mSplashScreen, HomeScreen.class);
					startActivity(i);
				}
			}
		};

		mSplashThread.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			synchronized (mSplashThread) {
				mSplashThread.notifyAll();
			}
		}
		return true;
	}

}
