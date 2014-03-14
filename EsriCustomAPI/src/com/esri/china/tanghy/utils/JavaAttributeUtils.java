package com.esri.china.tanghy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��ȡjava��������
 * @author Administrator
 *
 */
public class JavaAttributeUtils {
	
	 
	   /**
	    * 
	    * ��ȡ����ֵ
	 * @param fieldName
	 * @param o
	 * @return
	 */
	public static  Object getFieldValueByName(String fieldName, Object o) {  
	       try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	           return null;    
	       }    
	   }   
	     
	     
	   /**
	    * 
	    * ��ȡ���������������������
	 * @param o
	 * @return
	 */
	public static String[] getFiledName(Object o){  
	    Field[] fields=o.getClass().getDeclaredFields();  
	        String[] fieldNames=new String[fields.length];  
	    for(int i=0;i<fields.length;i++){
	        System.out.println(fields[i].getType());  
	        fieldNames[i]=fields[i].getName();  
	    }  
	    return fieldNames;  
	   }  
	     
	     
	   /**
	    * ��ȡ�����б����
	 * @param o
	 * @return
	 */
	public static List getFiledsInfo(Object o){  
	    Field[] fields=o.getClass().getDeclaredFields();  
	        String[] fieldNames=new String[fields.length];  
	        List list = new ArrayList();  
	        Map infoMap=null;  
	    for(int i=0;i<fields.length;i++){
	        infoMap = new HashMap();  
	        infoMap.put("type", fields[i].getType().toString());  
	        infoMap.put("name", fields[i].getName());  
	        infoMap.put("value", getFieldValueByName(fields[i].getName(), o));  
	        list.add(infoMap);  
	    }  
	    return list;  
	   }  
	     
	     
	   /**
	    * ��ȡ����ֵ����
	 * @param o
	 * @return
	 */
	public static Object[] getFiledValues(Object o){  
	    String[] fieldNames=getFiledName(o);  
	    Object[] value=new Object[fieldNames.length];  
	    for(int i=0;i<fieldNames.length;i++){
	        value[i]=getFieldValueByName(fieldNames[i], o);  
	    }  
	    return value;  
	   }     
	   
	   /**
	    * ��ȡmap����
	 * @param o
	 * @return
	 */
	public static Map<String,Object> getMap(Object o){
		   
		   Map<String,Object> map = new HashMap<String, Object>();
		   Field[] fields=o.getClass().getDeclaredFields();  
	       String[] fieldNames=new String[fields.length];  
	       
		    for(int i=0;i<fields.length;i++){
		        fieldNames[i]=fields[i].getName(); 
		        Object value = getFieldValueByName(fields[i].getName(),o);
		        map.put(fields[i].getName(), value);
		    }  
		    
		   return map;
		   
	   }

}
