package com.mylancerix;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class ServiceActivity extends Activity implements OnClickListener {
	
	    private Button btnOil;
	    private Button btnBrake;
	    private Button btnWheel;
	    private Button btnBelt;
	    private Button btnPrice;
	    private TextView txtService;
	    private int oil—hange;
	    private int oilOld—hange;
	    private int oilNext—hange;
	    
	  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		btnOil = (Button) findViewById(R.id.btn_oil);
		btnBrake = (Button) findViewById(R.id.btn_brake);
		btnWheel = (Button) findViewById(R.id.btn_wheel);
		btnBelt = (Button) findViewById(R.id.btn_belt);
		btnPrice = (Button) findViewById(R.id.btn_price);
		txtService = (TextView) findViewById(R.id.txtService);

		// add all buttons one listener
		btnOil.setOnClickListener(this);
		btnBrake.setOnClickListener(this);
		btnWheel.setOnClickListener(this);
		btnBelt.setOnClickListener(this);
		btnPrice.setOnClickListener(this);
		
		oil—hange = 8000;
		oilOld—hange = 135472;
		oilNext—hange = oilOld—hange + oil—hange;
		
	}
	
	
	
	public void onClickBtnDirectory(View view) {
		startActivity(new Intent (getApplicationContext(), DirectoryActivity.class));
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.service, menu);
        return true;
    }


	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menuDirectory:
			startActivity(new Intent(getApplicationContext(), DirectoryActivity.class));
			break;
			
		case R.id.menuSettings:
			startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
			break;
			
		case R.id.menuExit:
			finish();
	//		System.exit(0);
			break;

		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}
	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_oil:
			txtService.setText("«‡ÏÂÌ‡ Ï‡ÒÎ‡ Í‡Ê‰˚Â " + oil—hange + " ÍÏ."
					+ "\n¬‡¯‡ ÔÓÒÎÂ‰Ìˇˇ Á‡ÏÂÌ‡ " + oilOld—hange
					+ "\n—ÎÂ‰Û˛˘‡ˇ Á‡ÏÂÌ‡ " + oilNext—hange + " ÍÏ");
			break;
		case R.id.btn_brake:
			txtService.setText("Text is Brake service show");
			break;
		case R.id.btn_wheel:
			txtService.setText("Text is Wheel service show");
			break;
		case R.id.btn_belt:
			txtService.setText("Text is Belt service show");
			break;
		case R.id.btn_price:
			Intent browserIntent = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("http://auto.ria.com/search/?marka_id=52&model_id=457&state=0#power_name=1&category_id=1&marka_id[0]=52&model_id[0]=457&s_yers[0]=2007&state[0]=0&currency=1&custom=1&damage=1&auto_repairs=2&gearbox[1]=2&fuelRatesType=city&order_by=2&saledParam=2&with_photo=on&countpage=10"));
			startActivity(browserIntent);
			break;
		}
	}
	
	

}
