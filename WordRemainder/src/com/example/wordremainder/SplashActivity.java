package com.example.wordremainder;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		
		Log.i("Splash Screen", "splash screen started");
		
		Timer timer=new Timer();
		timer.schedule(holdActivity, 3000);
	}
	
	TimerTask holdActivity =new TimerTask(){

		@Override
		public void run()
		{
			finish();
			Intent intent=new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);         
		
		}
		
	};


}
