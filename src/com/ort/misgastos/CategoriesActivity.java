package com.ort.misgastos;

import com.example.misgastos.R;
import com.ort.misgastos.db.CategoryDAO;
import com.ort.misgastos.file.FileManager;
import com.ort.misgastos.spend.Category;
import com.ort.misgastos.spend.CategoryCustomAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoriesActivity extends Activity {
	private static final String TAG = "CategoriesActivity";

	private ListView categoriesListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		
		categoriesListView = (ListView) findViewById(R.id.list_view_categories);

		try {
			categoriesListView.setAdapter(new CategoryCustomAdapter(getBaseContext(), (new CategoryDAO(this)).getList()));
		} catch (Exception exception) {
			Log.d(TAG, exception.getMessage(), exception);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categories, menu);
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

	public void buttonNewCategoryOnClick(View view) {
        startActivity(new Intent(this, CategoryFormActivity.class));
	}

	public void buttonBackOnClick(View view) {
        startActivity(new Intent(this, NewSpendActivity.class));
	}
}
