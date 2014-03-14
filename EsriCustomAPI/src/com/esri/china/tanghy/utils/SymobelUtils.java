package com.esri.china.tanghy.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.Symbol;
import com.esri.core.symbol.SymbolHelper;

public class SymobelUtils {
	
	
	public enum MODE{
		RIGHT,
		LEFT,
		TOP,
		BOTTOM
	}
	
	
	/**
	 * 返回组件的截图
	 * @param vw 传入要显示的布局View
	 * @return PictureMarkerSymbol类
	 */
	public static PictureMarkerSymbol pictureSymobel(Context context,int layoutID){
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View vw = inflater.inflate(layoutID, null);
		Bitmap bt = ArcGISUtils.convertViewToBitmap(vw);
		BitmapDrawable temp = new BitmapDrawable(context.getResources(),bt);
		
		return new PictureMarkerSymbol(temp);
	}
	
	
	/**
	 * @param context
	 * @param view 要标绘的view对象
	 * @return PictureMarkerSymbol类
	 */
	public static PictureMarkerSymbol pictureSymobel(Context context,View view){
		
		Bitmap bt = ArcGISUtils.convertViewToBitmap(view);
		BitmapDrawable temp = new BitmapDrawable(context.getResources(),bt);
		
		return new PictureMarkerSymbol(temp);
	}
	
	/**
	 * 
	 * @param context
	 * @param label 标绘文字内容
	 * @param color 字体颜色
	 * @param size 字体大小
	 * @param imgInt 图片资源
	 * @param mode 显示位置
	 * @return
	 */
	public static PictureMarkerSymbol TextPicSymobel(Context context,CharSequence label,int color ,float size,int imgInt,MODE mode){
		
		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		ImageView imgView  = new ImageView(context);
		imgView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		Drawable db = context.getResources().getDrawable(imgInt);
		imgView.setImageDrawable(db);
		
		TextView txtView = new TextView(context);
		txtView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		txtView.setText(label);
		txtView.setTextColor(color);
		txtView.setTextSize(size);
		
		switch(mode){
		
		case RIGHT:
			layout.addView(txtView);
			layout.addView(imgView);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			break;
		case LEFT:
			layout.addView(imgView);
			layout.addView(txtView);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			break;
		case TOP:
			layout.addView(txtView);
			layout.addView(imgView);
			layout.setOrientation(LinearLayout.VERTICAL);
			break;
		case BOTTOM:
			layout.addView(imgView);
			layout.addView(txtView);
			layout.setOrientation(LinearLayout.VERTICAL);
			break;
		}
		
		Bitmap bt = ArcGISUtils.convertViewToBitmap(layout);
		BitmapDrawable temp = new BitmapDrawable(context.getResources(),bt);
		
		return new PictureMarkerSymbol(temp);
	}
	
	
		
	/**
	 * @param context
	 * @param label 标绘的文字
	 * @param imgInt 标绘的图片资源
	 * @param mode 文字的摆放位置
	 * @return
	 */
	public static PictureMarkerSymbol TextPicSymobel(Context context,CharSequence label,int imgInt,MODE mode){
			return TextPicSymobel(context,label,Color.BLACK,15,imgInt,mode);
		}
	
	public static Bitmap createBitmapfromSymbol(Symbol symbol,ArcGISFeatureLayer layer){
		Bitmap bitmap = null;
//		if (layer.getGeometryType().equals(Geometry.Type.POINT)) {
//			Point pt = new Point(20,20);
//			bitmap = SymbolHelper.getLegendImage(symbol,pt, 50, 50,Color.WHITE);
//		}
//		else if (layer.getGeometryType().equals(Geometry.Type.POLYLINE)) {
//			Polyline polyline = new Polyline();
//			polyline.startPath(0,0);
//			polyline.lineTo(40,40);			
//			bitmap =  SymbolHelper.getLegendImage(symbol,polyline, 50, 50, Color.WHITE);
//		}
//		else if (layer.getGeometryType().equals(Geometry.Type.POLYGON)){
//			Polygon polygon = new Polygon();
//			polygon.startPath(0, 0);
//			polygon.lineTo(40, 0);
//			polygon.lineTo(40, 40);
//			polygon.lineTo(0, 40);
//			polygon.lineTo(0, 0);
//			bitmap =  SymbolHelper.getLegendImage(symbol,polygon, 50, 50, Color.WHITE);
//		}
		return bitmap;
	}

}
