package com.ort.misgastos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.misgastos.R;
import com.ort.misgastos.entities.Spend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpendCustomAdapter extends ArrayAdapter<Spend> {
	private List<Spend> spends;

	public SpendCustomAdapter(Context context, List<Spend> spends) {
		super(context, R.layout.custom_spend_listview, spends);

		this.spends = spends;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());

		View item = inflater.inflate(R.layout.custom_spend_listview, parent, false);

		TextView textViewValue = (TextView) item.findViewById(R.id.tvListViewSpendValue);
		TextView textViewCategory = (TextView) item.findViewById(R.id.tvListViewSpendCategory);
		TextView textViewDescription = (TextView) item.findViewById(R.id.tvListViewSpendDescription);
		TextView textViewCreated = (TextView) item.findViewById(R.id.tvListViewSpendCreated);

		textViewValue.setText("$ " + String.valueOf(round(spends.get(position).getValue(), 2)));
		textViewCategory.setText(
				spends.get(position).getCategory() != null ? spends.get(position).getCategory().getName() : "");
		textViewDescription.setText(spends.get(position).getDescription());
		textViewCreated.setText(new SimpleDateFormat("dd-MM-yyyy").format(spends.get(position).getCreated()));

		return item;
	}

	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

		return bd.floatValue();
	}
}
