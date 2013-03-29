package com.example.localeactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
	
	public static final String sLoggedSP = "session";
	
	private Button mBtnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		
		mBtnLogin = (Button) findViewById(R.id.b_login);
		
		mBtnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getSharedPreferences(sLoggedSP, MODE_PRIVATE).edit().putBoolean("loggedin", true);
				proceedLogin();
			}

		});
		
		if (getSharedPreferences(sLoggedSP, MODE_PRIVATE).getBoolean("loggedin", false)) {
			proceedLogin();
		}
	}

	private void proceedLogin() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}	
	

}
