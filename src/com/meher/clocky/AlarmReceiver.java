package com.meher.clocky;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver  {
	@Override
    public void onReceive(Context arg0, Intent arg1) {
		        MediaPlayer ourAlarm;
		        Toast.makeText(arg0, "Your Time is up!!!!!", Toast.LENGTH_LONG).show();
		        System.out.println("alarm!");
		        ourAlarm = MediaPlayer.create(arg0,R.raw.xperia_alarm);
				ourAlarm.start();
				ourAlarm.setLooping(true);
		        
		    }
    }