
package com.ort.misgastos;

import java.util.List;

import com.example.misgastos.R;
import com.ort.misgastos.spend.Category;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

public class ReportsActivity extends Activity {
	
	private Spinner monthSpinner;
	private Spinner yearSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		
		monthSpinner = (Spinner) findViewById(R.id.report_spinner_month_filter);
		yearSpinner = (Spinner) findViewById(R.id.report_spinner_year_filter);
		
		List<Integer> monthList = new ArrayList<>();
		
		monthSpinner.setAdapter(new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void buttonBackOnClick(View view) {
		finish();
	}
}
