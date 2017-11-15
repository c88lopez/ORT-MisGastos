package com.example.misgastos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.entities.Spend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpendCustomAdapter extends ArrayAdapter<Spend> {
	private List<Spend> spends;
	
	public SpendCustomAdapter(Context context, List<Spend> spends) {
		super(context,R.layout.listview_spends, spends);
		
		this.spends = spends;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		View item = inflater.inflate(R.layout.listview_spends, parent, false);
		
		TextView tvImporte = (TextView)item.findViewById(R.id.tvListViewGastoImporte);
		TextView tvCategoria = (TextView)item.findViewById(R.id.tvListViewGastoCategoria);
		TextView tvDetalle = (TextView)item.findViewById(R.id.tvListViewGastoDetalle);
		TextView tvfecha = (TextView)item.findViewById(R.id.tvListViewGastoFecha);

		tvImporte.setText(String.valueOf(round(spends.get(position).getValue(), 2)));
		tvCategoria.setText(spends.get(position).getCategory() != null ? spends.get(position).getCategory().getName() : "");
		tvDetalle.setText(spends.get(position).getDescription());
		tvfecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(spends.get(position).getCreated()));
		
		return item;
	}
	
	static class ViewHolder{
		TextView tvValue;
		TextView tvCategory;
		TextView tvDescription;
		TextView tvCreated;
	}
	
	private float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        
        return bd.floatValue();
    }
}
