package com.example.localeactivity;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {

	private static final int REQUEST_CHANGE_LOCALE = 0;
	
	public static final String sLocaleSPKey = "AppLocale";
	public static final String sLocale = "Locale";
	public static final String sLogin = "LOGIN";
	
	private String mCurrentLocale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Locale l = getResources().getConfiguration().locale;
		mCurrentLocale = l.getISO3Language() + "_" + l.getISO3Country();
		
		final SharedPreferences sp = getSharedPreferences(sLocale, MODE_PRIVATE);
		
		if (!mCurrentLocale.equals(sp.getString(sLocaleSPKey, ""))) {
			
			final Intent intent = new Intent(this, this.getClass());
			//of course you also want to pass state of this activity via Intent
			startActivityForResult(intent, REQUEST_CHANGE_LOCALE);
			
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == REQUEST_CHANGE_LOCALE) {
			if(data.getBooleanExtra(sLogin, false)) {
				
			}
			setResult(resultCode, data);
			finish();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
