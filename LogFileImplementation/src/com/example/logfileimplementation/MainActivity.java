package com.example.logfileimplementation;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static final String SDCARD = String.valueOf(Environment
			.getExternalStorageDirectory());
	TextView mTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
		deleteLog();
		setContentView(R.layout.activity_main);
		createAppDirectories();
		mTextView = (TextView) findViewById(R.id.action_settings);
		mTextView.setText("Android crash Reporter");
		} catch(Exception ex) {
			Utilities.writeIntoLog(Log.getStackTraceString(ex));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Creating App Directory For Log File
	private void createAppDirectories() {
		System.out.println("hiii");
		File dir1 = new File(Utilities.APP_DIR);
		if (!dir1.exists()) {
			dir1.mkdir();
		}
	}

	// Deleting App Directory which is used for Log File
	private void deleteLog() {

		File log = new File(Utilities.LOG_FILE_PATH);
		if (log.exists()) {
			log.delete();
		}
	}
}
