package com.meher.clocky;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

public class Meeting extends ListActivity {
	 String[] values = new String[] { "From:", "To:", "Time before change into silent",
     "Vibrate or Normal" };
TimePicker TimePicker;

TimePickerDialog timePickerDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_at);

			    // use your own layout
			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        R.layout.settings_item, R.id.settings_item_name, values);
			    setListAdapter(adapter);

			    ListView l = getListView();
			    l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			        @Override
			        public void onItemClick(AdapterView<?> parent, final View view,
			            int position, long id){
			        	
			        	switch(position){
			        	case 0:
			        		openTimePickerDialog(false);
			        		//Intent tpicker = new Intent(getApplicationContext(),PickTime.class);
			        		//startActivity(tpicker);
			        		break;
			        		
			        	case 1: 
			        		openTimePickerDialog(false);
			        				break;
			        	case 2: 
			        		break;
			        	case 3: break;
			        	}
			        }
			
			    });
	}

			    private void openTimePickerDialog(boolean is24r) {
			        Calendar calendar = Calendar.getInstance();
			        timePickerDialog = new TimePickerDialog(this, onTimeSetListener,
			                calendar.get(Calendar.HOUR_OF_DAY),
			                calendar.get(Calendar.MINUTE), is24r);
			        timePickerDialog.setTitle("Set Alarm");

			        timePickerDialog.show();

			    }
			    OnTimeSetListener onTimeSetListener = new OnTimeSetListener(){
			    	  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			    		  Calendar calNow = Calendar.getInstance();
				            Calendar calSet = (Calendar) calNow.clone();

				            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
				            calSet.set(Calendar.MINUTE, minute);
				            calSet.set(Calendar.SECOND, 0);
				            calSet.set(Calendar.MILLISECOND, 0);

				            if (calSet.compareTo(calNow) <= 0) {

				                calSet.add(Calendar.DATE, 1);
				            }

			    	  }
	};


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_at, menu);
		return true;
	}

}
