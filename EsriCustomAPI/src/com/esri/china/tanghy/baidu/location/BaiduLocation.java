package com.esri.china.tanghy.baidu.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.esri.china.tanghy.interfaces.ILocation;

public class BaiduLocation {
	
	private final String DEFAULT_COORTYPE = "bd09ll";
	private final String DEFAULT_SERVICE = "com.baidu.location.service_v2.9";
	
	private LocationClient mLocationClient = null;
	private ILocation ilocation;
	public LocationClient getmLocationClient() {
		return mLocationClient;
	}


	private MyLocationListenner myListener = new MyLocationListenner();
	public BaiduLocation(Context context) {
		super();
		mLocationClient = new LocationClient( context );
		mLocationClient.registerLocationListener( myListener );
		setLocationOption(3000);
	}
	
	public void setLocationListenner(ILocation ilocation){
		this.ilocation = ilocation;
	}
	
	
	/**
	 * ��������������λ�õ�ʱ�򣬸�ʽ�����ַ������������Ļ��
	 */
	public class MyLocationListenner implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return ;
			BaiduLocation.this.ilocation.setLocation(location);
		}
		
		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null){
				return ; 
			}
		}
	}
	
	
	//������ز���
		public void setLocationOption(int timerInt){
			LocationClientOption option = new LocationClientOption();
			option.setOpenGps(true);				//��gps
			option.setCoorType(DEFAULT_COORTYPE);		//������������
			option.setServiceName(DEFAULT_SERVICE);
			option.setPoiExtraInfo(false);	
			option.setScanSpan(timerInt);
			option.setPriority(LocationClientOption.NetWorkFirst);      //������������
			option.setPoiNumber(10);
			option.disableCache(true);		
			mLocationClient.setLocOption(option);
		}
	
	
	
	

}
