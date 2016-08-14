package com.mylancerix;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mylancerix.adapter.DirectoryPartsAdapter;
import com.mylancerix.pojo.DirectoryParts;

public class DirectoryActivity extends Activity {
	
	private ListView directoryList;

	private List<DirectoryParts> initData() {
		List<DirectoryParts> list = new ArrayList<DirectoryParts>();
		list.add(new DirectoryParts("Колодки", "WZTG56784"));
		list.add(new DirectoryParts("Масло", "3717610"));
		list.add(new DirectoryParts("Диски", "LSD833G56784"));
		list.add(new DirectoryParts("Диски тормозные передние", ""));
		list.add(new DirectoryParts("Диски тормозные задние", ""));
		list.add(new DirectoryParts("Диски сцепления", ""));
		list.add(new DirectoryParts("Корзина сцепления", ""));
		list.add(new DirectoryParts("Ремень ГРМ", "UKSG56784"));
		list.add(new DirectoryParts("Резина", "WZTG56784"));
		list.add(new DirectoryParts("Фильтр воздушный", ""));
		list.add(new DirectoryParts("Фильтр масляный", ""));
		list.add(new DirectoryParts("Фильтр салона", ""));

		return list;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_directory);
		
		directoryList = (ListView) findViewById(R.id.directory_listView);
		DirectoryPartsAdapter adapter = new DirectoryPartsAdapter(this, initData());
		directoryList.setAdapter(adapter);
		
		
		directoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
//				Toast.makeText(getApplicationContext(), "itemClick: position = " +
//						position + ", id = " + id + ", " + parent.getAdapter().getItem(position),
//						Toast.LENGTH_LONG).show();
				

				Toast.makeText(getApplicationContext(), "itemClick: position = " +
						position + ", id = " + id,
						Toast.LENGTH_LONG).show();
				
			}
		});
		

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
