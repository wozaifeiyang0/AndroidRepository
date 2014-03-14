package com.tanghy.pro.activity;

import java.io.File;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.esri.china.tanghy.utils.FileUtils;

public class MainActivity extends Activity {
	
	private final static String XML_NODE_MAP = "map"; 
	private final static String XML_NODE_MAP_ATTRIBUTE_EXTENT = "initialextent"; 
	
	private final static String XML_NODE_MAP_BASEMAP = "basemaps";
	private final static String XML_NODE_MAP_OPERATIONAL_LAYER = "operationallayers";
	
	private final static String XML_NODE_MAP_LAYER = "layer"; 
	private final static String XML_NODE_MAP_LAYER_ATTRIBUTE_LABEL = "label";
	private final static String XML_NODE_MAP_LAYER_ATTRIBUTE_TYPE = "type";
	private final static String XML_NODE_MAP_LAYER_ATTRIBUTE_URL = "url";
	private final static String XML_NODE_MAP_LAYER_ATTRIBUTE_ICON = "icon";
	private final static String XML_NODE_MAP_LAYER_ATTRIBUTE_VISIBLE = "visible";
	private final static String XML_NODE_MAP_LAYER_ATTRIBUTE_ALPHA = "alpha";
	
	private final static String XML_NODE_WIDGETCONTAINER = "widgetcontainer"; 
	private final static String XML_NODE_MENUS = "menus";
	
	private final static String XML_NODE_WIDGET = "widget";  
	private final static String XML_NODE_WIDGET_ATTRIBUTE_LABEL = "label"; 
	private final static String XML_NODE_WIDGET_ATTRIBUTE_ICON = "icon";
	private final static String XML_NODE_WIDGET_ATTRIBUTE_CONFIG = "config";
	private final static String XML_NODE_WIDGET_ATTRIBUTE_CLASSNAME = "classname"; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InputStream input = null;

		try
		{
			input = getResources().getAssets().open(Constant.CONFIG_FILE);
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
		
		try {
			deleteFile("config.xml");
			File file = getFileStreamPath("config.xml");
			if(file.exists()){
				Log.i("aaaa", "asdfasdf");
			}
			FileUtils.writeDataFile(MainActivity.this, "config.xml", input);
			InputStream in = openFileInput("config.xml");
			input = getResources().getAssets().open(Constant.CONFIG_FILE);
			boolean aaa = FileUtils.isFilesToSame(input, in);
			
			 int length = in.available();
			 byte [] buffer = new byte[length];        
			 in.read(buffer);            
			 String res = EncodingUtils.getString(buffer, "UTF-8"); 
			 System.out.println(res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
