package com.esri.china.tanghy.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * SharedPreferences�����ļ�
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
	 * Ϊ�����ļ���Ӽ�/ֵ
	 * @param key ��
	 * @param value ֵ
	 */
	public void putValue(String key,String value){
		spe.putString(key, value);
		spe.commit();
	}
	
	
	/**
	 * ��ȡ�����ļ��ļ�ֵ
	 * @param key ��
	 * @return �������Ӧ��ֵ
	 */
	public String getValue(String key){
		return sp.getString(key, null);
	}
	
	
	
	
	

}
