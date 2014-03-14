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
 * Android文件操作类
 * @author tanghy
 *
 */
public class FileUtils {
	
	
	/**
	 * 读取配置文件，返回Properties对象
	 * @param context 上下文
	 * @param path 配置文件路径
	 * @return 返回属性对象
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
	 * 保存配置文件的方法
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
	 * 写入/data/data/<package name>/files里
	 * @param context 上下文
	 * @param fileName 保存的文件名
	 * @param fileContent 保存的内容
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
	 * 写入/data/data/<package name>/files里
	 * @param context 上下文
	 * @param fileName 保存的文件名
	 * @param fileContent 保存的内容
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
	 * 写入/data/data/<package name>/files里
	 * @param context 上下文
	 * @param fileName 保存的文件名
	 * @param fileContent 保存的内容
	 * @param mode 模式如：Context.MODE_PRIVATE
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
	 * 判断两个文件是否相同
	 * @param input1 文件流一
	 * @param input2 文件流二
	 * @return 返回文件是否相同
	 * @throws IOException
	 */
	public static boolean isFilesToSame(InputStream input1,InputStream input2) throws IOException {
		
		boolean issame = true;
		
		//获取两个输入流大小
		int len1 = input1.available();
		int len2 = input2.available();
		
		//判断两个文件是否相同
		if(len1 == len2){
			
			byte[] data1 = new byte[len1];
			byte[] data2 = new byte[len2];
			
			//分别将两个文件的内容读入缓冲区
			input1.read(data1);   
			input2.read(data2);
			//关闭流
			input1.close();
			input2.close();
			 //依次比较文件中的每一个字节    
			 for (int i=0; i<len1; i++) {     
				 //只要有一个字节不同，两个文件就不一样    
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
