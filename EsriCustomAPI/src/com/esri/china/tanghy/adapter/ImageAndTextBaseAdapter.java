package com.esri.china.tanghy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageAndTextBaseAdapter extends BaseAdapter {
	
	
	private Context context;//上下文
	private int selResID;//选择的ID号
	private int[] picID;//图片数组
	private LinearLayout[] parents;//
	private LinearLayout currentView;
	
	
	
	/**
	 * 图片与文字显示的适配器
	 * @param context 上下文对象
	 * @param picID 图片id的数组
	 * @param txtID 文字id的数组
	 * @param selResID 被选中的选项背景
	 */
	public ImageAndTextBaseAdapter(Context context,int[] picID,String[]txtID,int selResID) {
		super();
		this.context = context;
		this.selResID = selResID;
		this.picID = picID;
		this.parents = new LinearLayout[picID.length];
		
		
		for(int i=0;i<picID.length;i++){
			parents[i] = new LinearLayout(context);
			parents[i].setLayoutParams(new GridView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			parents[i].setOrientation(LinearLayout.VERTICAL);
			parents[i].setGravity(Gravity.CENTER);
			
			ImageView img = new ImageView(this.context);
			img.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			img.setImageResource(picID[i]);
			parents[i].addView(img);
			if(txtID != null){
				TextView txt = new TextView(context);
				txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				txt.setText(txtID[i]);
				txt.setTextColor(Color.BLACK);
				txt.setTextSize(12);
				txt.setGravity(Gravity.CENTER);
				parents[i].addView(txt);
			}
			
			
		}
		
		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.picID.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return parents[arg0];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout linelay;
		if(convertView == null){
			linelay = this.parents[position];
			
			
		}else{
			linelay = (LinearLayout)convertView;
		}
		return linelay;
	}
	
	/**
	 * 设置选中的效果
	 * @param index
	 */
	public void SetFocus(int index){
		for(int i=0;i<parents.length;i++){
			if(i!=index){
				parents[i].setBackgroundResource(0);//恢复未选中的样式
			}
		}
		currentView = parents[index];
		currentView.setBackgroundResource(selResID);//设置选择的样式
	}
	
	
	public void clearSelector(){
		if(currentView != null)
		currentView.setBackgroundResource(0);
	}

}
