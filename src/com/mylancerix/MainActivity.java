package com.mylancerix;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private DBManager dbManager;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new DatabaseHelper(this, "database.db", null, 1);

		database = dbHelper.getWritableDatabase();

	}
    
    public void onClick(View view) {
        
    	Cursor cursor = database.query("service", new String[] {DatabaseHelper.PART_NAME_COLUMN, DatabaseHelper.PART_CATALOG_COLUMN,
                        DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, DatabaseHelper.PART_OLD_CHANGE_COLUMN, DatabaseHelper.PART_NEXT_CHANGE_COLUMN},
                null, null,
                null, null, null) ;

        cursor.moveToNext();

        String partName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PART_NAME_COLUMN));
        String partCatalog = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PART_CATALOG_COLUMN));
        int serviceChange = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN));
        int oldChange = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PART_OLD_CHANGE_COLUMN));
        int nextChange = oldChange + serviceChange;
  //    int nextChange = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PART_NEXT_CHANGE_COLUMN)); 
        
        
        // рабочий аллерт первой позиции в списке деталей
        
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(partName)
				.setMessage(R.string.next_change_for + nextChange + " км." + "\nCataloge ID: " + partCatalog 
						+ "\n"+ R.string.change_for + serviceChange + " km")
		//		.setIcon(R.drawable.ic_android_cat)
				.setCancelable(false)
				.setNegativeButton("ОК",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();

		
////        TextView infoTextView = (TextView)findViewById(R.id.textView);
////		infoTextView.setText("Деталь " + partName + " номер по каталогу: " + partCatalog
////				+ " рекомендуемая замена при пробеге " + serviceChange
////				+ " последняя замена "
////				+ oldChange + "\nследующая замена на пробеге: " + nextChange);
//
        cursor.close();
    }
    
    
    
    
    public void onClickbtnMain(View view) {
    	
    	EditText textNameOfPart = (EditText)findViewById(R.id.nameOfPart);
    	String nameOfPart = textNameOfPart.getText().toString();
    	
    	EditText textCatalogeNameOfPart = (EditText)findViewById(R.id.catalogeNameOfPart);
    	String catalogeNameOfPart = textCatalogeNameOfPart.getText().toString();
    	
    	EditText serviceChange = (EditText)findViewById(R.id.serviceChange);
    	int valServiceChange = Integer.parseInt( serviceChange.getText().toString() );
    	
    	EditText oldChange = (EditText)findViewById(R.id.oldChange);
    	int valOldChange = Integer.parseInt( oldChange.getText().toString() );
    	
    	ContentValues newValues = new ContentValues();
 	    newValues.put(DatabaseHelper.PART_NAME_COLUMN, nameOfPart);
 	    newValues.put(DatabaseHelper.PART_CATALOG_COLUMN, catalogeNameOfPart);
 	    newValues.put(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, valServiceChange);
 	    newValues.put(DatabaseHelper.PART_OLD_CHANGE_COLUMN, valOldChange);
 	    newValues.put(DatabaseHelper.PART_NEXT_CHANGE_COLUMN, valOldChange + valServiceChange);

 	    database.insert("service", null, newValues);	// Add data in table
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
			
		case R.id.menuParts:
			startActivity(new Intent(getApplicationContext(), PartsDataTest.class));
			break;
			
		case R.id.menuAddPart:
			startActivity(new Intent(getApplicationContext(), AddPartActivity.class));
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
