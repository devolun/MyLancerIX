package com.mylancerix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menuService:
			startActivity(new Intent(getApplicationContext(),
					ServiceActivity.class));
			break;

		case R.id.menuDirectory:
			startActivity(new Intent(getApplicationContext(),
					DirectoryActivity.class));
			break;

		case R.id.menuSettings:
			startActivity(new Intent(getApplicationContext(),
					SettingsActivity.class));
			break;

		case R.id.menuExit:
			finish();
			System.exit(0);
			break;

		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

}
