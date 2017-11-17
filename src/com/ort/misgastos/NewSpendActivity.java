package com.ort.misgastos;

import java.util.List;

import com.example.misgastos.R;
import com.ort.misgastos.db.CategoryDAO;
import com.ort.misgastos.file.FileManager;
import com.ort.misgastos.spend.Category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewSpendActivity extends Activity {
	private static final String TAG = "NewSpendActivity";

	private EditText editTextSpendValue;
	private EditText editTextSpendDescription;
	private Spinner spinnerSpendCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_spend);

		editTextSpendValue = (EditText) findViewById(R.id.edit_text_spend_value);
		editTextSpendDescription = (EditText) findViewById(R.id.edit_text_spend_description);
		spinnerSpendCategory = (Spinner) findViewById(R.id.spinner_spend_categories);

		try {
			if ((new CategoryDAO(this)).getList().isEmpty()) {
				FileManager.importCategories(this);
			}

			spinnerSpendCategory.setAdapter(new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item,
					(new CategoryDAO(this)).getList()));
		} catch (Exception exception) {
			Log.d(TAG, exception.getMessage(), exception);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_spend, menu);
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

	public void buttonSubmitOnClick(View view) {
		startActivity(new Intent(this, MainActivity.class));
	}

	public void buttonCategoriesManagementOnClick(View view) {
		startActivity(new Intent(this, CategoriesActivity.class));
	}

	public void buttonCancelOnClick(View view) {
		startActivity(new Intent(this, MainActivity.class));
	}
}
