package com.example.misgastos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button buttonNewSpend;
	private Button buttonReportsLink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.bootstrapActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	private void bootstrapActivity() {
		/**
		 * Bootstrap browser button
		 */
		this.buildButtonNewSpend();
		this.buildButtonReportsLink();
	}

	private void buildButtonNewSpend() {
		buttonNewSpend = (Button) findViewById(R.id.button_new_spend_id);
		buttonNewSpend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), NewSpendActivity.class));
			}
		});
	}

	private void buildButtonReportsLink() {
		buttonReportsLink = (Button) findViewById(R.id.button_reports_link_id);
		buttonReportsLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), ReportsActivity.class));
			}
		});
	}
}
