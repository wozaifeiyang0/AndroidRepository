package com.tanghy.pro.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListTestActivity extends Activity  {
	
	private static List<String> list = null;  
    private DragListAdapter adapter = null;  
      
    public static List<String> groupKey= new ArrayList<String>();  
    private List<String> navList = new ArrayList<String>();  
    private List<String> moreList = new ArrayList<String>();  
      
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
          
        initData();  
          
        com.esri.china.tanghy.compents.TouchInterceptor dragListView = (com.esri.china.tanghy.compents.TouchInterceptor)findViewById(R.id.drag_list);  
        adapter = new DragListAdapter(this, list);  
        dragListView.setAdapter(adapter);  
    }  
      
    public void initData(){  
        //���ݽ��  
        list = new ArrayList<String>();  
          
        //groupKey��ŵ��Ƿ����ǩ  
        groupKey.add("A��");  
        groupKey.add("B��");  
          
        for(int i=0; i<8; i++){  
            navList.add("Aѡ��"+i);  
        }  
        list.add("A��");  
        list.addAll(navList);  
          
        for(int i=0; i<1; i++){  
            moreList.add("Bѡ��"+i);  
        }  
        list.add("B��");  
        list.addAll(moreList);  
    }  
      
    public static class DragListAdapter extends ArrayAdapter<String>{  
          
        private Context mContext;  
        public DragListAdapter(Context context, List<String> objects) {  
            super(context, 0, objects);  
            this.mContext=context;  
        }  
          
        public List<String> getList(){  
            return list;  
        }  
        
        
          
        @Override  
        public boolean isEnabled(int position) {  
            if(groupKey.contains(getItem(position))){  
                //����Ƿ����ǩ������false������ѡ�У����ܵ��  
                return false;  
            }  
            return super.isEnabled(position);  
        }  
  
        @Override  
        public View getView(int position, View convertView, ViewGroup parent) {  
              
            View view = convertView;  
            if(groupKey.contains(getItem(position))){  
                //����Ƿ����ǩ���ͼ��ط����ǩ�Ĳ����ļ������������ļ���ʾЧ����ͬ  
                view = LayoutInflater.from(getContext()).inflate(R.layout.drag_list_item_tag, null);  
            }else{  
                //����������������ǩ���ͼ�������������Ĳ����ļ�  
                view = LayoutInflater.from(getContext()).inflate(R.layout.drag_list_item, null);  
            }  
              
            TextView textView = (TextView)view.findViewById(R.id.drag_list_item_text);  
            textView.setText(getItem(position));  
              
            return view;  
        }  
    }  

}
