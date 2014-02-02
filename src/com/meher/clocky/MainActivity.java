package com.meher.clocky;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button at,after,meeting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		at = (Button) findViewById(R.id.bat);
		after = (Button) findViewById(R.id.bafter);
		meeting = (Button)findViewById(R.id.bmeeting);
		at.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 //TODO Auto-generated method stub
				Intent open = new Intent(getApplicationContext(), AlarmAt.class);
				startActivity (open);
			}
		});
		
		after.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent open = new Intent(getApplicationContext(), AlarmAfter.class);
				startActivity (open);
			}
		});
		meeting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent open = new Intent(getApplicationContext(), Meeting.class);
				startActivity (open);
			}
		});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
