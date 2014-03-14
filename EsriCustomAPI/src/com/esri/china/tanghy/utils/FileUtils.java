package com.esri.china.tanghy.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import android.content.Context;
import android.util.Log;

/**
 * 
 * Android�ļ�������
 * @author tanghy
 *
 */
public class FileUtils {
	
	
	/**
	 * ��ȡ�����ļ�������Properties����
	 * @param context ������
	 * @param path �����ļ�·��
	 * @return �������Զ���
	 */
	public static Properties loadConfig(Context context,String path){
		Properties properties = new Properties();
		try {
			FileInputStream input = new FileInputStream(path);
			properties.load(input);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null;
		}
		
		return properties;
		
	}
	
	/**
	 * ���������ļ��ķ���
	 * @param context
	 * @param file
	 * @param properties
	 * @return
	 */
	public static boolean saveConfig(Context context ,String file,Properties properties){
		File fil = new File(file);
		if(!fil.exists()){
			try {
				fil.createNewFile();
				FileOutputStream s = new FileOutputStream(fil);
				properties.store(s, "");
				
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * д��/data/data/<package name>/files��
	 * @param context ������
	 * @param fileName ������ļ���
	 * @param fileContent ���������
	 */
	public static void writeDataFile(Context context,String fileName,String fileContent){
		
		FileOutputStream output = null;
		try {
			if(fileName != null && fileContent != null){
				output = context.openFileOutput(fileName, Context.MODE_PRIVATE);
				output.write(fileContent.getBytes());
				output.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * д��/data/data/<package name>/files��
	 * @param context ������
	 * @param fileName ������ļ���
	 * @param fileContent ���������
	 * @throws IOException 
	 */
	public static void writeDataFile(Context context,String fileName, InputStream fileContent){
		try {
			writeDataFile(context, fileName,fileContent,Context.MODE_PRIVATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("writeDataFile", "write Data wrong!");
		}
	}
	/**
	 * д��/data/data/<package name>/files��
	 * @param context ������
	 * @param fileName ������ļ���
	 * @param fileContent ���������
	 * @param mode ģʽ�磺Context.MODE_PRIVATE
	 * @throws IOException 
	 */
	public static void writeDataFile(Context context,String fileName, InputStream fileContent,int mode) throws IOException{
		int length = fileContent.available();         
		byte [] buffer = new byte[length];        
		fileContent.read(buffer);     
		FileOutputStream output = context.openFileOutput(fileName, mode);
		output.write(buffer);
		output.flush();
		output.close();
	}
	
	
	/**
	 * 
	 * �ж������ļ��Ƿ���ͬ
	 * @param input1 �ļ���һ
	 * @param input2 �ļ�����
	 * @return �����ļ��Ƿ���ͬ
	 * @throws IOException
	 */
	public static boolean isFilesToSame(InputStream input1,InputStream input2) throws IOException {
		
		boolean issame = true;
		
		//��ȡ������������С
		int len1 = input1.available();
		int len2 = input2.available();
		
		//�ж������ļ��Ƿ���ͬ
		if(len1 == len2){
			
			byte[] data1 = new byte[len1];
			byte[] data2 = new byte[len2];
			
			//�ֱ������ļ������ݶ��뻺����
			input1.read(data1);   
			input2.read(data2);
			//�ر���
			input1.close();
			input2.close();
			 //���αȽ��ļ��е�ÿһ���ֽ�    
			 for (int i=0; i<len1; i++) {     
				 //ֻҪ��һ���ֽڲ�ͬ�������ļ��Ͳ�һ��    
				 if(data1[i] != data2[i]){
					 issame = false;
					return issame; 
				 }
			 }
		}else{
			issame = false;
		}
		
		return issame;
	}
	
	
	public static String filePath(Context context, String fileName) throws IOException{
		URL url = context.getClass().getResource("/assets/"+fileName);
		String path = url.getPath();
		return path;
	}


    public static byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte imgdata[] = bytestream.toByteArray();
        bytestream.close();
        return imgdata;
    }

}
