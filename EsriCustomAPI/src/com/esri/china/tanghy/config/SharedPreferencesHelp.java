package com.esri.china.tanghy.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * SharedPreferences配置文件
 * @author tanghy
 *
 */
public class SharedPreferencesHelp {
	
	SharedPreferences sp;
	SharedPreferences.Editor spe;
	Context context;
	
	public SharedPreferencesHelp(Context context ,String name){
		this.context = context;
		sp =  context.getSharedPreferences(name,0);
		spe = sp.edit();
	}
	
	
	/**
	 * 为配置文件添加键/值
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(String key,String value){
		spe.putString(key, value);
		spe.commit();
	}
	
	
	/**
	 * 获取配置文件的键值
	 * @param key 键
	 * @return 返回相对应的值
	 */
	public String getValue(String key){
		return sp.getString(key, null);
	}
	
	
	
	
	

}
