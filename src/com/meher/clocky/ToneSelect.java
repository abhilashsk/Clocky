package com.meher.clocky;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ToneSelect extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tone_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tone_select, menu);
		return true;
	}

}
