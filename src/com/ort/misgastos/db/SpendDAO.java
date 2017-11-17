package com.ort.misgastos.db;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ort.misgastos.spend.Category;
import com.ort.misgastos.spend.Spend;

import android.content.Context;

public class SpendDAO {

	private Helper handler;

	public SpendDAO(Context context) {
		handler = Helper.getInstance(context);
	}

	public List<Spend> getSpends() throws IllegalArgumentException, ParseException {
		List<Spend> spends = new ArrayList<Spend>();

		spends.add(new Spend(1, "pantalon", new Category(1, "Ropa"), 12, new Date()));
		spends.add(new Spend(2, "remera", new Category(1, "Ropa"), 23, new Date()));
		spends.add(new Spend(3, "paty", new Category(2, "Comida"), 34, new Date()));

		return spends;
	}
}
