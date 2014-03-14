////////////////////////////////////////////////////////////////////////////////
//
//Copyright (c) 2011-2012 Esri
//
//All rights reserved under the copyright laws of the United States.
//You may freely redistribute and use this software, with or
//without modification, provided you include the original copyright
//and use restrictions.  See use restrictions in the file:
//<install location>/License.txt
//
////////////////////////////////////////////////////////////////////////////////

package com.tanghy.pro.activity;


/*
 * All the file paths in folder "assets" here
 */

public class Constant 
{
	public static final int SUCCESS = 1;
	public static final int FAILURE = -1;
	
	public static final int WIDGET_ICON_WIDTH = 60;
	public static final int WIDGET_ICON_HEIGHT = 60;
	
	public static final String CONFIG_FILE = "config.xml";
	public static final String CONFIG_ASSETS_ICON_FOLDER = "icon/";
	public static final String CONFIG_ASSETS_LAYER_ICON_FOLDER = "layericon/";
	public static final String DEFAULT_WIDGET_ICON = "images/default_widget.png";
	
	public static final String LAYER_TILED = "tiled";
	public static final String LAYER_TILED_NAME = "Tiled Map Service Layer";
	public static final String LAYER_TILED_CLASSNAME = "com.esri.android.map.ags.ArcGISTiledMapServiceLayer";
	
	public static final String LAYER_FEATURE = "feature";
	public static final String LAYER_FEATURE_NAME = "Feature Layer";
	public static final String LAYER_FEATURE_CLASSNAME = "com.esri.android.map.ags.ArcGISFeatureLayer";
	
	public static final String LAYER_DYNAMIC = "dynamic";
	public static final String LAYER_DYNAMIC_NAME = "Dynamic Map Service Layer";
	public static final String LAYER_DYNAMIC_CLASSNAME = "com.esri.android.map.ags.ArcGISDynamicMapServiceLayer";
	
	public static final String LAYER_IMAGE = "image";
	public static final String LAYER_IMAGE_NAME = "Image Service Layer";
	public static final String LAYER_IMAGE_CLASSNAME = "com.esri.android.map.ags.ArcGISImageServiceLayer";
	
	public static final String LAYER_LOCAL = "local";
	public static final String LAYER_LOCAL_NAME = "Local Tiled Layer";
	public static final String LAYER_LOCAL_CLASSNAME = "com.esri.android.map.ags.ArcGISLocalTiledLayer";
	
	public static final String LAYER_OFFLINE = "offline";
	public static final String LAYER_OFFLINE_NAME = "offline Layer";
	public static final String LAYER_OFFLINE_CLASSNAME = "com.esri.android.map.FeatureLayer";
	
	public static final String LAYER_GRAPHIC = "graphic";
	public static final String LAYER_GRAPHIC_NAME = "graphic Layer";
	public static final String LAYER_GRAPHIC_CLASSNAME = "com.esri.android.map.GraphicsLayer";
	
	public static final String LAYER_TIANDITU = "tianditu";
	public static final String LAYER_TIANDITU_NAME = "TianDiTu Tiled Layer";
	public static final String LAYER_TIANDITU_CLASSNAME = "cn.com.esrichina.tianditu.TianDiTuLayer";

}
