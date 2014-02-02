package com.meher.clocky;


import java.util.Calendar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AlarmAt extends ListActivity {
	final static int RQS_1 = 1;
	String[] values = new String[] { "Set Time", "Repeat", "Alarm Tone", "Alarm Volume"  };
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
			        		
			        		Intent rpicker = new Intent(getApplicationContext(),Repeat.class);
			        		startActivity(rpicker);
			        				break;
			        	case 2: 
			        		Intent mpicker = new Intent(getApplicationContext(),ToneSelect.class);
			        		startActivity(mpicker);
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

	            setAlarm(calSet);
    	  }

    };
    private void setAlarm(Calendar targetCal) {

        //
        Toast.makeText(AlarmAt.this,"Current time is" +Calendar.HOUR+ "Alarm is set at " + targetCal.getTime(),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }
   
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_at, menu);
		return true;
	}

}
