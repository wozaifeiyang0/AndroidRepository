package com.esri.china.tanghy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * 网络相关的工具类
 * @author Administrator
 *
 */
public class NetworkUtils {
	
	
	/**
	 * 判断当前设备连接是否为WIFI连接
	 * @param context 上下文
	 * @return 
	 */
	public static boolean isWIFI(Context context){
		
		ConnectivityManager connectM = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectM.getActiveNetworkInfo();
		
		//判断网络连接是否为WIFI连接
		if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断当前设备连接是否为3G连接
	 * @param context
	 * @return
	 */
	public static boolean is3GRD(Context context){
		
		ConnectivityManager connectM = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectM.getActiveNetworkInfo();
		
		//判断网络连接是否为3G连接
		if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 判断网络连接状态
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnabled(Context context) { 
		ConnectivityManager mgrConn = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE); 
		TelephonyManager mgrTel = (TelephonyManager) context .getSystemService(Context.TELEPHONY_SERVICE); 
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS); 
	}
	
	/**
	 * 打开GPS设置界面
	 * @param context
	 */
	public static void openGPSSettings(Context context){
		LocationManager alm =(LocationManager)context.getSystemService( Context.LOCATION_SERVICE );
		if( alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER ) ){
			Toast.makeText( context, "GPS 已经准备好！", Toast.LENGTH_SHORT ).show();
		}else{
				Toast.makeText( context, "跳转到GPS设置界面！", Toast.LENGTH_SHORT ).show();
				Intent myIntent = new Intent( Settings.ACTION_SECURITY_SETTINGS );
				context.startActivity(myIntent);
			}
	}
	
	/**
	 * 判断GPS是否可用
	 * @param context
	 * @return
	 */
	public static boolean isGPSEnabled(Activity activity){
		LocationManager locationManager=(LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		//判断GPS模块是否开启，如果没有则开启
		if(!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
			Toast.makeText(activity, "GPS 没有打开，请打开GPS设置!", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(Settings.ACTION_SECURITY_SETTINGS);
			activity.startActivityForResult(intent,0);
			return false;
		}else{
			Toast.makeText(activity, "GPS 已经开启！", Toast.LENGTH_SHORT).show();
			return true;
		}
	}
	

	/**
	 * 判断网络是否连接
	 * @param context
	 * @return
	 */
	public boolean isNetworkConnected(Context context) { 
		if (context != null) { 
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
				.getSystemService(Context.CONNECTIVITY_SERVICE); 
				NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
			if (mNetworkInfo != null) { 
					return mNetworkInfo.isAvailable(); 
				} 
		} 
		return false; 
	}
	
	

	/**
	 * 判断当前网络类型，是3G网络，还是WIFI网络
	 * @param context
	 * @return
	 */
	public static int getConnectedType(Context context) { 
		if (context != null) { 
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
			.getSystemService(Context.CONNECTIVITY_SERVICE); 
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) { 
				return mNetworkInfo.getType(); 
			} 
		} 
		return -1; 
	}
	

	/**
	 * 判断当前是否可用
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) { 
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		if (connectivity == null) { 
			return false; 
		} else { 
			NetworkInfo[] info = connectivity.getAllNetworkInfo(); 
			if (info != null) { 
				for (int i = 0; i < info.length; i++) { 
					if (info[i].getState() == NetworkInfo.State.CONNECTED) { 
						return true; 
					} 
				} 
			} 
		} 
		return false; 
	}
	
	
	
	

}
