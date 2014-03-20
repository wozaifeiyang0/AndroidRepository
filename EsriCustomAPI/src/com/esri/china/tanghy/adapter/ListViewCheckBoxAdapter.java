package com.esri.china.tanghy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.esri.china.tanghy.R;

public class ListViewCheckBoxAdapter extends BaseAdapter {
	
	private String[] names;
	private LayoutInflater mLayoutInflater;
	

	public ListViewCheckBoxAdapter(Context context ,String[] names) {
		super();
		this.names = names;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return names[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.esri_list_item_check, null);
			holder.title = (TextView)convertView.findViewById(R.id.esri_list_item_text);
			holder.checkBox = (CheckBox)convertView.findViewById(R.id.esri_list_item_check);
			convertView.setTag(holder);
			
			
		}else
			holder = (ViewHolder)convertView.getTag();
		holder.title.setText(names[position]);
		return convertView;
	}
	
	
	
	public class ViewHolder{
		public TextView title;
		public CheckBox checkBox;
	}

}
