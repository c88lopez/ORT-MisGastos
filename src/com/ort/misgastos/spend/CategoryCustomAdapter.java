package com.ort.misgastos.spend;

import java.math.BigDecimal;
import java.util.List;

import com.example.misgastos.R;
import com.ort.misgastos.CategoriesActivity;
import com.ort.misgastos.CategoryFormActivity;
import com.ort.misgastos.db.CategoryDAO;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CategoryCustomAdapter extends ArrayAdapter<Category> {
	private static final String TAG = "CategoryCustomAdapter";
	
	private List<Category> categories;

	public CategoryCustomAdapter(Context context, List<Category> categories) {
		super(context, R.layout.custom_spend_listview, categories);

		this.categories = categories;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());

		View item = inflater.inflate(R.layout.custom_categories_listview, parent, false);

		TextView textViewCategoryName = (TextView) item.findViewById(R.id.text_view_category_name);

		Button buttonEditCategory = (Button) item.findViewById(R.id.button_category_edit);
		Button buttonDeleteCategory = (Button) item.findViewById(R.id.button_category_delete);

		textViewCategoryName.setText(categories.get(position).getName());
		
		buttonEditCategory.setTag(categories.get(position));
		buttonDeleteCategory.setTag(categories.get(position));

		buttonEditCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String id = Long.toString(((Category) v.getTag()).getId());
				String name = v.getTag().toString();
				
				Intent intent = new Intent(v.getContext(), CategoryFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				intent.putExtra("categoryId", id);
				intent.putExtra("categoryName", name);
				
				getContext().startActivity(intent);
			}
		});

		buttonDeleteCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					(new CategoryDAO(getContext())).delete(new Category(((Category) v.getTag()).getId()));					
				} catch (Exception e) {
					Log.v(TAG, e.getMessage());
				}
				
				Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				getContext().startActivity(intent);
			}
		});

		return item;
	}

	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

		return bd.floatValue();
	}
}
