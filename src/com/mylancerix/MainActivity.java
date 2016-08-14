package com.mylancerix;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private DatabaseHelper mDatabaseHelper;
	private SQLiteDatabase mSqLiteDatabase;

	
    @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    setContentView(R.layout.activity_main);

	    mDatabaseHelper = new DatabaseHelper(this, "database.db", null, 1);

	    mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

	    ContentValues newValues = new ContentValues();
	    // Задайте значения для каждого столбца
	    newValues.put(DatabaseHelper.PART_NAME_COLUMN, "Фильтр салона");
	    newValues.put(DatabaseHelper.PART_CATALOG_COLUMN, "MR45781244");
	    newValues.put(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, "10000");
	    newValues.put(DatabaseHelper.PART_OLD_CHANGE_COLUMN, "125000");
	    
	    newValues.put(DatabaseHelper.PART_NAME_COLUMN, "Фильтр масла");
	    newValues.put(DatabaseHelper.PART_CATALOG_COLUMN, "MR47821244");
	    newValues.put(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, "10000");
	    newValues.put(DatabaseHelper.PART_OLD_CHANGE_COLUMN, "125000");
	    
	    // Add data in table
	    mSqLiteDatabase.insert("service", null, newValues);
	}
	
	
    
    public void onClick(View view) {
        Cursor cursor = mSqLiteDatabase.query("service", new String[] {DatabaseHelper.PART_NAME_COLUMN, DatabaseHelper.PART_CATALOG_COLUMN,
                        DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, DatabaseHelper.PART_OLD_CHANGE_COLUMN},
                null, null,
                null, null, null) ;

        cursor.moveToFirst();

        String partName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PART_NAME_COLUMN));
        String partCatalog = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PART_CATALOG_COLUMN));
        int serviceChange = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN));
        int oldChange = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PART_OLD_CHANGE_COLUMN));
        int nextChange = oldChange + serviceChange;
        

        TextView infoTextView = (TextView)findViewById(R.id.textView);
		infoTextView.setText("Деталь " + partName + " номер по каталогу: " + partCatalog
				+ " рекомендуемая замена при пробеге " + serviceChange
				+ " последняя замена "
				+ oldChange + "\nследующая замена на пробеге: " + nextChange);

        // не забываем закрывать курсор
        cursor.close();
    }
    
    

    
    
   /////////////////////////////////// 
    
    
    
    public void onClickbtnMain(View view) {
	//	Toast.makeText(MainActivity.this, "Нажмите здесь", Toast.LENGTH_LONG).show();
		startActivity(new Intent (getApplicationContext(), ServiceActivity.class));
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
