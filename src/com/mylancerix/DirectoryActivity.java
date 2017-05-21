package com.mylancerix;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mylancerix.pojo.Part;

public class DirectoryActivity extends Activity {
	
	private DBManager dbManager;
	private ListView directoryList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_directory);
		
		dbManager = new DBManager(this);
		dbManager.open();

        List<Part> values = dbManager.getAllParts();
        
		Collections.sort(values, new Comparator<Part>() {
			public int compare(Part o1, Part o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		
        ArrayAdapter<Part> adapter = new ArrayAdapter<Part>(this, android.R.layout.simple_list_item_multiple_choice, values);
		directoryList = (ListView) findViewById(R.id.directory_listView);
		directoryList.setAdapter(adapter);
		
		directoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "itemClick: position = " + position + 
						", id = " + id + " " + 
						"Деталь " + parent.getAdapter().getItem(position),
						Toast.LENGTH_LONG).show();
			}
		});

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.directory, menu);
		return true;
	}


	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menuService:
			startActivity(new Intent(getApplicationContext(), ServiceActivity.class));
			break;
	
		case R.id.menuSettings:
			startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
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
	
}