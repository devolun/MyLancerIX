package com.mylancerix;

import java.util.ArrayList;
import java.util.List;
import com.mylancerix.pojo.Part;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
//	private Context context;


	private String[] allNameParts = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.PART_NAME_COLUMN};
	
//	private String[] allColumns = { DatabaseHelper.TABLE_NAME, DatabaseHelper.PART_NAME_COLUMN, DatabaseHelper.PART_CATALOG_COLUMN,
//    		DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, DatabaseHelper.PART_OLD_CHANGE_COLUMN, DatabaseHelper.PART_NEXT_CHANGE_COLUMN};
	
	
	
	
    public DBManager(Context context) {
        dbHelper = new DatabaseHelper(context);
}


	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Part createPart(String part) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TABLE_NAME, part);
		long insertId = database
				.insert(DatabaseHelper.TABLE_NAME, null, values);
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, allNameParts,
				DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Part newPart = cursorToPart(cursor);
		cursor.close();
		return newPart;
	}

	public void deletePart(Part part) {
		long id = part.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Part> getAllParts() {
		List<Part> parts = new ArrayList<Part>();

		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, allNameParts,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Part part = cursorToPart(cursor);
			parts.add(part);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		
		cursor.close();
		
		return parts;
	}
    
    
    
    
  
//	
//    public void insert(String part_id, String part_name, String part_catalog, String service_change, String old_change, String next_change) {
//        ContentValues contentValue = new ContentValues();
//        contentValue.put(DatabaseHelper.PART_NAME_COLUMN, part_name);
//        contentValue.put(DatabaseHelper.PART_CATALOG_COLUMN, part_catalog);
//        contentValue.put(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, service_change);
//        contentValue.put(DatabaseHelper.PART_OLD_CHANGE_COLUMN, old_change);
//        contentValue.put(DatabaseHelper.PART_NEXT_CHANGE_COLUMN, next_change);
//        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
//    }
//    
//    
//    public Cursor fetch() {
//        String[] columns = new String[] {DatabaseHelper.TABLE_NAME, DatabaseHelper.PART_NAME_COLUMN, DatabaseHelper.PART_CATALOG_COLUMN,
//        		DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, DatabaseHelper.PART_OLD_CHANGE_COLUMN, DatabaseHelper.PART_NEXT_CHANGE_COLUMN};
//        
//        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }
//    
//    
//    
    public Cursor fromBD() {
        String[] columns = new String[] {DatabaseHelper.TABLE_NAME, DatabaseHelper.PART_NAME_COLUMN};
        
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
//    
//    

//    
//    public int update(String part_name, String part_catalog, int j, int k, int l) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.PART_NAME_COLUMN, part_name);
//        contentValues.put(DatabaseHelper.PART_CATALOG_COLUMN, part_catalog);
//        contentValues.put(DatabaseHelper.PART_SERVICE_CHANGE_COLUMN, j);
//        contentValues.put(DatabaseHelper.PART_OLD_CHANGE_COLUMN, k);
//        contentValues.put(DatabaseHelper.PART_NEXT_CHANGE_COLUMN, l);
//        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID, null);
//        return i;
//    }
//    
//    
//    public void delete(long _id) {
//        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
//    }
    
	
    
	private Part cursorToPart(Cursor cursor) {
		Part part = new Part();
		part.setId(cursor.getLong(0));
		part.setPart(cursor.getString(1));
		return part;
	}

}
