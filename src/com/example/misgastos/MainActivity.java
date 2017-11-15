package com.example.misgastos;

import com.example.entities.SpendInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			SpendInfo spendInfo = new SpendInfo(this);
			ListView spendCustomListView = (ListView) findViewById(R.id.spends_list_view_id);
		
			SpendCustomAdapter spendAdapter = new SpendCustomAdapter(getBaseContext(), spendInfo.getSpends());
			
			Log.v(TAG, spendInfo.getSpends().get(0).getDescription());
			
			spendCustomListView.setAdapter(spendAdapter);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
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

    public void buttonNewSpendOnClick(View view) {
        startActivity(new Intent(this, NewSpendActivity.class));
    }

    public void buttonReportsOnClick(View view) {
        startActivity(new Intent(this, ReportsActivity.class));
    }
}
