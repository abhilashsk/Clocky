package com.meher.clocky;

import java.util.Calendar;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class PickTime extends Activity {
	private TimePicker timePicker;
	private Button setb;
    private int hour=1;
    private int minute=00;
    static final int TIME_DIALOG_ID = 999;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);
        setCurrentTimeOnView();
        setb = (Button) findViewById(R.id.bset);
        setb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "hour="+timePicker.getCurrentHour(),
						   Toast.LENGTH_LONG).show();
			}
		});
    }

    public void setCurrentTimeOnView() {
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        // set current time into timepicker
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setIs24HourView(true);
   }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID:
            // set time picker as current time
            return new TimePickerDialog(this, timePickerListener, hour, minute,true);
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener =  new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            // set current time into timepicker
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
    };
}
