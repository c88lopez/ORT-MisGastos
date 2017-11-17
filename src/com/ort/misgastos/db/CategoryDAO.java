package com.ort.misgastos.db;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import com.ort.misgastos.db.CategoryContract.CategoryEntry;
import com.ort.misgastos.spend.Category;
import com.ort.misgastos.spend.Report;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CategoryDAO {

	private static Helper handler;

	public CategoryDAO(Context context) {
		handler = Helper.getInstance(context);
	}

	private static Category get(Cursor cursor) {
		Category category = null;

		return category;
	}

	public static Category get(int id) {
		Category category = null;
		SQLiteDatabase db = handler.getDb();

		Cursor cursor = db.query(CategoryEntry.TABLE_NAME, // a. table
				CategoryEntry.COLUMNS, // b. column names
				CategoryEntry._ID + " = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (cursor.moveToFirst()) {
			category = new Category(cursor.getInt(cursor.getColumnIndexOrThrow(CategoryEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME_NAME)));
		}

		db.close();

		return category;
	}

	public static Category get(String name) {
		Category category = null;
		SQLiteDatabase db = handler.getDb();
		
		Cursor cursor = db.query(CategoryEntry.TABLE_NAME, // a. table
				CategoryEntry.COLUMNS, // b. column names
				CategoryEntry.COLUMN_NAME_NAME + " = ?", // c. selections
				new String[] { name }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (cursor.moveToFirst()) {
			category = new Category(cursor.getInt(cursor.getColumnIndexOrThrow(CategoryEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME_NAME)));
		}

		db.close();

		return category;
	}
	
	public static List<Category> getList() {
		List<Category> categories = new LinkedList<Category>();
		SQLiteDatabase db = handler.getDb();

		Cursor cursor = db.query(CategoryEntry.TABLE_NAME, // The table to query
				CategoryEntry.COLUMNS, // The columns to return
				null,
				// CategoriaEntry.COLUMN_NAME_DATE + " = ?", // The columns for
				// the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // sortOrder // The sort order
		);

		while (cursor.moveToNext()) {
			Category category = new Category(cursor.getInt(cursor.getColumnIndexOrThrow(CategoryEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME_NAME)));
			
			categories.add(category);
		}

		cursor.close();
		db.close();
		
		return categories;
	}

	public void insert(Category category) throws Exception {
		if (get((int) category.getId()) == null) {
			if (get(category.getName()) != null) {
				throw new Exception("La categoria ya existe");
			}

			SQLiteDatabase db = handler.getDb();

			ContentValues values = new ContentValues();
			if (category.getId() != 0) {
				values.put(CategoryEntry._ID, category.getId());
			}

			values.put(CategoryEntry.COLUMN_NAME_NAME, category.getName());
			if (get((int) category.getId()) == null) {
				db = handler.getDb();
				category.setId(db.insert(CategoryEntry.TABLE_NAME, null, values));
			}

			db.close();
		}
	}

	public int update(Category category) {
		SQLiteDatabase db = handler.getDb();
		ContentValues values = new ContentValues();
		values.put(CategoryEntry.COLUMN_NAME_NAME, category.getName());

		int i = db.update(CategoryEntry.TABLE_NAME, // table
				values, // column/value
				CategoryEntry._ID + " = ?", // selections
				new String[] { String.valueOf(category.getId()) });

		db.close();

		return i;
	}

	public void delete(Category category) throws Exception {
		if (inUse(category)) {
			throw new Exception("La categoria esta en uso");
		}

		SQLiteDatabase db = handler.getDb();

		db.delete(CategoryEntry.TABLE_NAME, CategoryEntry._ID + " = ?",
				new String[] { String.valueOf(category.getId()) });

		db.close();
	}

	private boolean inUse(Category category) {
		SQLiteDatabase db = handler.getDb();
		String s = category != null ? Long.toString(category.getId()) : "";

		String sql = "SELECT COUNT(*) FROM spends WHERE spends.categories_id = ?;";
		Cursor cursor = db.rawQuery(sql, new String[] { s });
		cursor.moveToFirst();

		return cursor.getLong(0) > 0;
	}

	public List<Report> getReport(int mes) throws IllegalArgumentException, ParseException {
		List<Report> lista = new LinkedList<Report>();
		SQLiteDatabase db = handler.getDb();
		String s = Integer.toString(mes);
		s = mes > 9 ? "" : "0" + s;

		String sql = "SELECT ROUND(SUM(value),2) total_spend FROM spends WHERE SUBSTR(spends.created, 5, 2) = ?; ";
		Cursor cursor = db.rawQuery(sql, new String[] { s });
		float suma = cursor.moveToFirst() ? cursor.getFloat(cursor.getColumnIndexOrThrow("total_spend")) : 0;

		sql = "SELECT CATEGORIES._ID, CATEGORIES.NAME, ROUND(SUM(value),2) total_spend, ROUND((SUM(value) * 100 / ?), 2) percentage FROM spends LEFT JOIN categories ON categories._ID = spends.category_id WHERE SUBSTR(spend.created, 5, 2) = ? GROUP BY categories._ID, categories.name ORDER BY ROUND(SUM(value), 2) DESC;";
		cursor = db.rawQuery(sql, new String[] { Float.toString(suma), s });

		while (cursor.moveToNext()) {
			Category category = new Category(cursor.getInt(cursor.getColumnIndexOrThrow(CategoryEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME_NAME)));
			lista.add(new Report(category, cursor.getFloat(cursor.getColumnIndexOrThrow("total_spend")),
					cursor.getFloat(cursor.getColumnIndexOrThrow("percentage"))));
		}

		cursor.close();
		db.close();

		return lista;
	}

	public List<Report> getReport(int mes, int ano) throws IllegalArgumentException, ParseException {
		List<Report> lista = new LinkedList<Report>();
		SQLiteDatabase db = handler.getDb();
		String s = Integer.toString(mes);
		s = Integer.toString(ano) + (mes > 9 ? "" : "0") + s;

		String sql = "SELECT ROUND(SUM(IMPORTE),2) suma FROM spends WHERE SUBSTR(spends.created, 1, 6) = ?; ";
		Cursor cursor = db.rawQuery(sql, new String[] { s });
		float suma = cursor.moveToFirst() ? cursor.getFloat(cursor.getColumnIndexOrThrow("suma")) : 0;

		sql = "SELECT CATEGORIES._ID, CATEGORIES.NAME, ROUND(SUM(value),2) total_spend, (SUM(value) * 100 / ?) percent FROM spends LEFT JOIN categories ON CATEGORIES._ID = SPENDS.CATEGORIES_ID WHERE SUBSTR(SPENDS.CREATED, 1, 6) = ? GROUP BY CATEGORIES._ID, CATEGORIES.NAME ORDER BY ROUND(SUM(value), 2) DESC;";
		cursor = db.rawQuery(sql, new String[] { Float.toString(suma), s });

		while (cursor.moveToNext()) {
			Category category = new Category(cursor.getInt(cursor.getColumnIndexOrThrow(CategoryEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME_NAME)));
			lista.add(new Report(category, cursor.getFloat(cursor.getColumnIndexOrThrow("total_spend")),
					cursor.getFloat(cursor.getColumnIndexOrThrow("percentage"))));
		}

		cursor.close();
		db.close();

		return lista;
	}
}
