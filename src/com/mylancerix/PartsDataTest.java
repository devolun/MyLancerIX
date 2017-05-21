package com.mylancerix;

import android.os.Bundle;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.mylancerix.pojo.Part;

import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;

public class PartsDataTest extends ListActivity {
        private DBManager dbManager;

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_part);

                dbManager = new DBManager(this);
                dbManager.open();

                List<Part> values = dbManager.getAllParts();
                
                Collections.sort(values, new Comparator<Part>() {
                    public int compare(Part o1, Part o2) {
                        return o1.toString().compareTo(o2.toString());
                    }
                }
        );     
                
                // use the SimpleCursorAdapter to show the
                // elements in a ListView
                ArrayAdapter<Part> adapter = new ArrayAdapter<Part>(this,
                                android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
        }

        // Will be called via the onClick attribute
        // of the buttons in main.xml
        public void onClick(View view) {
                @SuppressWarnings("unchecked")
                ArrayAdapter<Part> adapter = (ArrayAdapter<Part>) getListAdapter();
                Part part = null;
                switch (view.getId()) {
                case R.id.add:
                        String[] parts = new String[] { "Cool", "Very nice", "Hate it" };
                        int nextInt = new Random().nextInt(3);
                        // save the new part to the database
                        part = dbManager.createPart(parts[nextInt]);
                        adapter.add(part);
                        
                        
                        
                        break;
                case R.id.delete:
                        if (getListAdapter().getCount() > 0) {
                                part = (Part) getListAdapter().getItem(0);
                                dbManager.deletePart(part);
                                adapter.remove(part);
                        }
                        break;
                }
                adapter.notifyDataSetChanged();
        }

        @Override
        protected void onResume() {
                dbManager.open();
                super.onResume();
        }

        @Override
        protected void onPause() {
                dbManager.close();
                super.onPause();
        }

}
