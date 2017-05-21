package com.mylancerix;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;


public class SettingsActivity extends Activity {
	
	DatabaseHelper dbHelper;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		dbHelper = new DatabaseHelper(this);  //создаем доступ к базе
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.directory, menu);
		return true;
	}
	
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menuService:
			startActivity(new Intent(getApplicationContext(), ServiceActivity.class));
			break;

		case R.id.menuDirectory:
			startActivity(new Intent(getApplicationContext(), DirectoryActivity.class));
			break;
			
		case R.id.menuAbout:
			startActivity(new Intent(getApplicationContext(), AboutActivity.class));
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

	
	public void onClick(View v) {
		
		SQLiteDatabase database = dbHelper.getWritableDatabase();
	}
	
	
	
	

}



