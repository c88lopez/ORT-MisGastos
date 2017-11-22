package com.ort.misgastos;

import com.example.misgastos.R;
import com.ort.misgastos.db.CategoryDAO;
import com.ort.misgastos.spend.Category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoryFormActivity extends Activity {
	private static final String TAG = "CategoryFormActivity";

	private String categoryId;
	private String categoryName;

	private EditText editTextCategoryName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_form);

		categoryId = getIntent().getStringExtra("categoryId");
		categoryName = getIntent().getStringExtra("categoryName");
		
		if (categoryId == null) {
			setTitle("Agregar Categoria");
		} else {
			setTitle("Editar Categoria");
		}

		editTextCategoryName = (EditText) findViewById(R.id.edit_text_new_category);
		editTextCategoryName.setText(categoryName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category_form, menu);
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
		if (validarCampos()) {
			try {
				CategoryDAO categoryDAO = new CategoryDAO(this);
				if (categoryId == null) {
					categoryDAO.insert(new Category(String.valueOf(editTextCategoryName.getText())));
					
					mostrarMensaje("Categoria registrada correctamente");
				} else {					
					categoryDAO.update(new Category(Long.parseLong(categoryId), editTextCategoryName.getText().toString()));
					
					mostrarMensaje("Categoria actualizada correctamente");
				}
				
				startActivity(new Intent(this, CategoriesActivity.class));
			} catch (Exception ex) {
				mostrarMensaje(ex.getMessage(), Toast.LENGTH_LONG);
			}
		}
	}

	private boolean validarCampos() {
		final int LARGO_DESCRIPCION = 50;
		Boolean valido = true;

		if (editTextCategoryName.getText().toString() == "") {
			mostrarMensaje("Debe ingresar un nombre");
			
			valido = false;
		} else {
			if (editTextCategoryName.getText().length() > LARGO_DESCRIPCION) {
				mostrarMensaje("El nombre no puede superar " + String.valueOf(LARGO_DESCRIPCION) + " caracteres");
				
				valido = false;
			}
		}

		return valido;
	}

	private void mostrarMensaje(String msj) {
		mostrarMensaje(msj, Toast.LENGTH_SHORT);
	}

	private void mostrarMensaje(String msj, int largo) {
		Toast toast = Toast.makeText(this, msj, largo);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}

	public void buttonCancelOnClick(View view) {
		finish();
	}
}
