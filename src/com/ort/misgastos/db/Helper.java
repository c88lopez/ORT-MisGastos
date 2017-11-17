package com.ort.misgastos.db;

import com.ort.misgastos.db.CategoryContract.CategoryEntry;
import com.ort.misgastos.db.SpendContract.SpendEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {
	private static Helper instance = null;
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "MySpends.db";
	
	private Helper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static Helper getInstance(Context context) {
		if (instance == null) {
			instance = new Helper(context);
		}
		
		return instance;
	}

	public SQLiteDatabase getDb() {
		return this.getWritableDatabase();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SpendEntry.SQL_CREATE);
		db.execSQL(CategoryEntry.SQL_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
