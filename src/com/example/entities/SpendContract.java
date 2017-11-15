package com.example.entities;

import android.provider.BaseColumns;

public class SpendContract {
	private SpendContract() {}

	/* Inner class that defines the table contents */
	public static class SpendEntry implements BaseColumns {
		protected static final String TABLE_NAME = "spends";
		protected static final String COLUMN_NAME_DESCRIPCION = "name";
		protected static final String COLUMN_NAME_DATE = "created";
		protected static final String COLUMN_NAME_IMPORTE = "value";
		protected static final String COLUMN_NAME_CATEGORIA_ID = "category_id";
		
		protected static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
				+ " INTEGER PRIMARY KEY," + COLUMN_NAME_DESCRIPCION + " TEXT," + COLUMN_NAME_DATE
				+ " TEXT," + COLUMN_NAME_IMPORTE + " FLOAT," + COLUMN_NAME_CATEGORIA_ID
				+ " INTEGER" + ")";
		protected static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
		
		protected static final String[] COLUMNS = { _ID, COLUMN_NAME_DESCRIPCION, COLUMN_NAME_DATE, COLUMN_NAME_IMPORTE, COLUMN_NAME_CATEGORIA_ID };
	}
}
