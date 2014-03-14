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
 * ������صĹ�����
 * @author Administrator
 *
 */
public class NetworkUtils {
	
	
	/**
	 * �жϵ�ǰ�豸�����Ƿ�ΪWIFI����
	 * @param context ������
	 * @return 
	 */
	public static boolean isWIFI(Context context){
		
		ConnectivityManager connectM = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectM.getActiveNetworkInfo();
		
		//�ж����������Ƿ�ΪWIFI����
		if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
			return true;
		}
		return false;
	}
	
	/**
	 * �жϵ�ǰ�豸�����Ƿ�Ϊ3G����
	 * @param context
	 * @return
	 */
	public static boolean is3GRD(Context context){
		
		ConnectivityManager connectM = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectM.getActiveNetworkInfo();
		
		//�ж����������Ƿ�Ϊ3G����
		if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * �ж���������״̬
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnabled(Context context) { 
		ConnectivityManager mgrConn = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE); 
		TelephonyManager mgrTel = (TelephonyManager) context .getSystemService(Context.TELEPHONY_SERVICE); 
		return ((mgrConn.getActiveNetworkInfo() != null && mgrConn .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS); 
	}
	
	/**
	 * ��GPS���ý���
	 * @param context
	 */
	public static void openGPSSettings(Context context){
		LocationManager alm =(LocationManager)context.getSystemService( Context.LOCATION_SERVICE );
		if( alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER ) ){
			Toast.makeText( context, "GPS �Ѿ�׼���ã�", Toast.LENGTH_SHORT ).show();
		}else{
				Toast.makeText( context, "��ת��GPS���ý��棡", Toast.LENGTH_SHORT ).show();
				Intent myIntent = new Intent( Settings.ACTION_SECURITY_SETTINGS );
				context.startActivity(myIntent);
			}
	}
	
	/**
	 * �ж�GPS�Ƿ����
	 * @param context
	 * @return
	 */
	public static boolean isGPSEnabled(Activity activity){
		LocationManager locationManager=(LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		//�ж�GPSģ���Ƿ��������û������
		if(!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
			Toast.makeText(activity, "GPS û�д򿪣����GPS����!", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(Settings.ACTION_SECURITY_SETTINGS);
			activity.startActivityForResult(intent,0);
			return false;
		}else{
			Toast.makeText(activity, "GPS �Ѿ�������", Toast.LENGTH_SHORT).show();
			return true;
		}
	}
	

	/**
	 * �ж������Ƿ�����
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
	 * �жϵ�ǰ�������ͣ���3G���磬����WIFI����
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
	 * �жϵ�ǰ�Ƿ����
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
