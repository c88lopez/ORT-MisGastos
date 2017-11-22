package com.ort.misgastos.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ort.misgastos.db.CategoryContract.CategoryEntry;
import com.ort.misgastos.db.SpendContract.SpendEntry;
import com.ort.misgastos.spend.Category;
import com.ort.misgastos.spend.Spend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

public class SpendDAO {

	private static Helper handler;
	private static Context context;

	public SpendDAO(Context context) {
		handler = Helper.getInstance(context);
		this.context = context;
	}

	public static List<Spend> getList() {
		List<Category> categories = (new CategoryDAO(context)).getList();
		
		List<Spend> spends = new LinkedList<Spend>();
		SQLiteDatabase db = handler.getDb();

		Cursor cursor = db.query(SpendEntry.TABLE_NAME, // The table to query
				SpendEntry.COLUMNS, // The columns to return
				null,
				// CategoriaEntry.COLUMN_NAME_DATE + " = ?", // The columns for
				// the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // sortOrder // The sort order
		);

		Category spendCategory = null;
		
		while (cursor.moveToNext()) {
			for (Category category : categories) {
				if (category.getId() == cursor.getLong(cursor.getColumnIndexOrThrow(SpendEntry.COLUMN_NAME_CATEGORY_ID))) {
					spendCategory = category;
					break;
				}
			}
		
			Spend spend = new Spend(
				spendCategory,
				cursor.getFloat(cursor.getColumnIndexOrThrow(SpendEntry.COLUMN_NAME_VALUE)),
				cursor.getString(cursor.getColumnIndexOrThrow(SpendEntry.COLUMN_NAME_DESCRIPTION)),
				cursor.getString(cursor.getColumnIndexOrThrow(SpendEntry.COLUMN_NAME_CREATED))
			);
			
			spends.add(spend);
		}

		cursor.close();
		db.close();
		
		return spends;
	}

	public void insert(Spend spend) throws Exception {
		SQLiteDatabase db = handler.getDb();

		ContentValues values = new ContentValues();

		values.put(SpendEntry.COLUMN_NAME_CATEGORY_ID, spend.getCategory().getId());
		values.put(SpendEntry.COLUMN_NAME_VALUE, spend.getValue());
		values.put(SpendEntry.COLUMN_NAME_DESCRIPTION, spend.getDescription());

		values.put(SpendEntry.COLUMN_NAME_CREATED,
				new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));

		db = handler.getDb();
		spend.setId(db.insert(SpendEntry.TABLE_NAME, null, values));

		db.close();
	}
}
