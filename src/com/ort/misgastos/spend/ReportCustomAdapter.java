package com.ort.misgastos.spend;

import java.math.BigDecimal;
import java.util.List;

import com.example.misgastos.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ReportCustomAdapter extends ArrayAdapter<Spend> {
	private static final String TAG = "ReportCustomAdapter";
	
	private List<Spend> spends;

	public ReportCustomAdapter(Context context, List<Spend> categoriesReport) {
		super(context, R.layout.custom_spend_listview, spends);

		Log.v(TAG, spends.get(0).getDescription());
		
		this.spends = spends;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());

		View item = inflater.inflate(R.layout.custom_spend_listview, parent, false);

		TextView textViewCategoryDescription = (TextView) item.findViewById(R.id.text_view_spend_category);
		TextView textViewCategoryPercetage = (TextView) item.findViewById(R.id.text_view_spend_created);

//		textViewValue.setText("$ " + String.valueOf(round(spends.get(position).getValue(), 2)));
//		textViewCategory.setText(
//				spends.get(position).getCategory() != null ? spends.get(position).getCategory().getName() : "");
//		textViewDescription.setText(spends.get(position).getDescription());
//		textViewCreated.setText(spends.get(position).getCreated());

		return item;
	}

	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

		return bd.floatValue();
	}
}
