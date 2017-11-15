package com.ort.misgastos.db;

import android.provider.BaseColumns;

public class SpendContract {
	private SpendContract() {
	}

	/* Inner class that defines the table contents */
	public static class SpendEntry implements BaseColumns {
		protected static final String TABLE_NAME = "spends";
		protected static final String COLUMN_NAME_DESCRIPTION = "description";
		protected static final String COLUMN_NAME_CREATED = "created";
		protected static final String COLUMN_NAME_VALUE = "value";
		protected static final String COLUMN_NAME_CATEGORY_ID = "category_id";

		protected static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY,"
				+ COLUMN_NAME_DESCRIPTION + " TEXT," + COLUMN_NAME_CREATED + " TEXT," + COLUMN_NAME_VALUE + " FLOAT,"
				+ COLUMN_NAME_CATEGORY_ID + " INTEGER" + ")";
		protected static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;

		protected static final String[] COLUMNS = { _ID, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_CREATED, COLUMN_NAME_VALUE,
				COLUMN_NAME_CATEGORY_ID };
	}
}
