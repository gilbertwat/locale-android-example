package com.example.localeactivity;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingActivity extends Activity {
	
	private final static int REQUEST_CHANGE_LOCALE = 0;
	
	private final static Locale zhTW = new Locale("zh_TW");
	private final static Locale enUS = new Locale("en_US");
	
	private Button mBtnToggle;
	private Button mBtnLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		mBtnToggle = (Button)findViewById(R.id.b_toggle_language);
		mBtnLogout = (Button)findViewById(R.id.b_logout);
		
		mBtnToggle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				final Locale l = getResources().getConfiguration().locale;
				SharedPreferences.Editor e = getSharedPreferences(MainActivity.sLocaleSPKey, MODE_PRIVATE).edit();
				e.putString(MainActivity.sLocale, l.getISO3Language() + "_" + l.getISO3Language());
				e.commit();
				
				if (l.equals(zhTW)) {
					changeLocale(enUS);
				} else {
					changeLocale(zhTW);
				}
				
				final Intent intent = new Intent(SettingActivity.this, SettingActivity.this.getClass());
				//of course you also want to pass state of this activity via Intent
				startActivityForResult(intent, REQUEST_CHANGE_LOCALE);
			}
		});
		
		mBtnLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Here is the tricky part, using the Locale.getDefault() to retrieve the "system" locale
				Locale locale = Locale.getDefault();
				changeLocale(locale);
				
				SharedPreferences sp = getSharedPreferences(LoginActivity.sLoggedSP, MODE_PRIVATE);
				sp.edit().clear().commit();
				
				Intent intent = new Intent();
				intent.putExtra(MainActivity.sLogin, true);
				setResult(RESULT_CANCELED, intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_setting, menu);
		return true;
	}
	
	public void changeLocale(Locale locale) {
		//replace the existing "app" locale
		getResources().getConfiguration().locale = locale;
		getResources().updateConfiguration(
				getResources().getConfiguration(),
				getResources().getDisplayMetrics());
	}

}
