package com.meher.clocky;

import java.util.ArrayList;
import java.util.Calendar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmAfter extends ListActivity {
	
	int hour, minutes;
	 String[] values = new String[] { "Set Time",  "Alarm Tone", "Alarm Volume"  };
	 TimePicker TimePicker;
	 TimePickerDialog timePickerDialog;
	 final static int RQS_1 = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_after);
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
		        	openTimePickerDialog(true);	
		        	}
	}
		    });}
		        private void openTimePickerDialog(boolean is24r) {
		           
		            timePickerDialog = new TimePickerDialog(this, onTimeSetListener,
		                    0,
		                    0, is24r);
		            timePickerDialog.setTitle("Set Alarm");

		            timePickerDialog.show();

		        }
		        OnTimeSetListener onTimeSetListener = new OnTimeSetListener(){
		        	  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		        		  hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		        		  minutes = Calendar.getInstance().get(Calendar.MINUTE);
		        		  Calendar calNow = Calendar.getInstance();
			            Calendar calSet = (Calendar) calNow.clone();

			            calSet.set(Calendar.HOUR_OF_DAY,hour+hourOfDay);
			            calSet.set(Calendar.MINUTE, minutes+minute);
			            calSet.set(Calendar.SECOND, 0);
			            calSet.set(Calendar.MILLISECOND, 0);

			            if (calSet.compareTo(calNow) <= 0) {

			                calSet.add(Calendar.DATE, 1);
			            }

			            setAlarm(calSet);}
                    
		        };
		        private void setAlarm(Calendar targetCal) {

		            //
		            Toast.makeText(AlarmAfter.this,"The time now is "+hour+ " Alarm is set at " + targetCal.getTime(),
		                    Toast.LENGTH_LONG).show();
		            Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
		            PendingIntent pendingIntent = PendingIntent.getBroadcast(
		                    getBaseContext(), RQS_1, intent, 0);
		            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
		                    pendingIntent);

		        }
		        public class AlarmReceiver extends BroadcastReceiver  {
		        	@Override
		            public void onReceive(Context arg0, Intent arg1) {
		        		        MediaPlayer ourAlarm;
		        		        Toast.makeText(arg0, "Your Time is up!!!!!", Toast.LENGTH_LONG).show();
		        		        System.out.println("alarm!");
		        		        ourAlarm = MediaPlayer.create(AlarmAfter.this, R.raw.xperia_alarm);
		        				ourAlarm.start();
		        		    }
		            }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_after, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
	
