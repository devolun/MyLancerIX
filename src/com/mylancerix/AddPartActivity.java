package com.mylancerix;

import com.mylancerix.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPartActivity extends Activity {
	
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_part);
		
		dbHelper = new DatabaseHelper(this, "database.db", null, 1);
		database = dbHelper.getWritableDatabase();
	}
	
	
	public void onClick(View view) {
		
		EditText textNameOfPart = (EditText) findViewById(R.id.nameOfPartAdd);
		String nameOfPart = textNameOfPart.getText().toString();

		EditText oldChange = (EditText) findViewById(R.id.oldChangeAdd);
		int valOldChange = Integer.parseInt(oldChange.getText().toString());	

		
		EditText serviceChange = (EditText) findViewById(R.id.serviceChangeAdd);
		int valServiceChange = Integer.parseInt(serviceChange.getText().toString());	
		
		EditText textCatalogeNameOfPart = (EditText) findViewById(R.id.catalogeNameOfPartAdd);
		String catalogeNameOfPart = textCatalogeNameOfPart.getText().toString();
		
			
		
		if (nameOfPart.length() == 0)	{
			Toast.makeText(getApplicationContext(), string.hint_name_of_part, Toast.LENGTH_SHORT).show();
		
		} if (oldChange.getText().length() == 0) {
			Toast.makeText(getApplicationContext(), string.hint_old_change, Toast.LENGTH_SHORT).show();
		
		} if (serviceChange.getText().length() == 0) {
			Toast.makeText(getApplicationContext(), string.hint_service_change, Toast.LENGTH_SHORT).show();
		
		} if (textCatalogeNameOfPart.getText().length() == 0) {
			Toast.makeText(getApplicationContext(), "Please fill in ", Toast.LENGTH_SHORT).show();
		
		} else {
	
			
			ContentValues newValues = new ContentValues();
			newValues.put(DatabaseHelper.PART_NAME_COLUMN, nameOfPart);
			newValues.put(DatabaseHelper.PART_CATALOG_COLUMN, catalogeNameOfPart);
			newValues.put(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, valServiceChange);
			newValues.put(DatabaseHelper.PART_OLD_CHANGE_COLUMN, valOldChange);
			newValues.put(DatabaseHelper.PART_NEXT_CHANGE_COLUMN, valOldChange + valServiceChange);
			
			database.insert("service", null, newValues);
			
			textNameOfPart.setText("");
			oldChange.setText("");
			oldChange.setText("");
			serviceChange.setText("");
			textCatalogeNameOfPart.setText("");

			AlertDialog.Builder builder = new AlertDialog.Builder(AddPartActivity.this);
			builder.setTitle(nameOfPart)
					.setMessage(nameOfPart + " Added!")
			//		.setIcon(R.drawable.ic_android_cat)
					.setCancelable(false)
					.setNegativeButton("Œ ",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();

		}
		

	}
	


}
