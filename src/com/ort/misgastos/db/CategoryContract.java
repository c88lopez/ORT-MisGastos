package com.ort.misgastos.db;

import android.provider.BaseColumns;

public class CategoryContract {
	private CategoryContract() {
	}

	/* Inner class that defines the table contents */
	public static class CategoryEntry implements BaseColumns {
		protected static final String TABLE_NAME = "categories";
		protected static final String COLUMN_NAME_NAME = "name";

		public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + COLUMN_NAME_NAME + " TEXT " + " )";
		public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

		protected static final String[] COLUMNS = { _ID, COLUMN_NAME_NAME };
	}
}
