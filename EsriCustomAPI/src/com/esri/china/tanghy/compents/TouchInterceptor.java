/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.esri.china.tanghy.compents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class TouchInterceptor extends ListView {
    
	private ImageView dragImageView;//����ק�����ʵ����һ��ImageView  
    private int dragSrcPosition;//��ָ�϶���ԭʼ���б��е�λ��  
    private int dragPosition;//��ָ�϶���ʱ�򣬵�ǰ�϶������б��е�λ��  
      
    private int dragPoint;//�ڵ�ǰ�������е�λ��  
    private int dragOffset;//��ǰ��ͼ����Ļ�ľ���(����ֻʹ����y������)  
      
    private WindowManager windowManager;//windows���ڿ�����  
    private WindowManager.LayoutParams windowParams;//���ڿ�����ק�����ʾ�Ĳ���  
      
    private int scaledTouchSlop;//�жϻ�����һ������  
    private int upScrollBounce;//�϶���ʱ�򣬿�ʼ���Ϲ����ı߽�  
    private int downScrollBounce;//�϶���ʱ�򣬿�ʼ���¹����ı߽�  
    
    private int resouceId = -1;

	public TouchInterceptor(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        
        resouceId = attrs.getAttributeResourceValue(null, "dragger", 0);
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();  
    }  
      
    //����touch�¼�����ʵ���Ǽ�һ�����  
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        if(ev.getAction()==MotionEvent.ACTION_DOWN){  
            int x = (int)ev.getX();  
            int y = (int)ev.getY();  
              
            dragSrcPosition = dragPosition = pointToPosition(x, y);  
            if(dragPosition==AdapterView.INVALID_POSITION){  
                return super.onInterceptTouchEvent(ev);  
            }  
  
            ViewGroup itemView = (ViewGroup) getChildAt(dragPosition-getFirstVisiblePosition());  
            dragPoint = y - itemView.getTop();  
            dragOffset = (int) (ev.getRawY() - y);  
              
            View dragger = resouceId == 0?itemView.getRootView():itemView.findViewById(resouceId);  
            if(dragger!=null&&x>dragger.getLeft()-20){  
                //  
                upScrollBounce = Math.min(y-scaledTouchSlop, getHeight()/3);  
                downScrollBounce = Math.max(y+scaledTouchSlop, getHeight()*2/3);  
                  
                itemView.setDrawingCacheEnabled(true);  
                Bitmap bm = Bitmap.createBitmap(itemView.getDrawingCache());  
                startDrag(bm, y);  
            }  
            return false;  
         }  
         return super.onInterceptTouchEvent(ev);  
    }  
  
    /** 
     * �����¼� 
     */  
    @Override  
    public boolean onTouchEvent(MotionEvent ev) {  
        if(dragImageView!=null&&dragPosition!=INVALID_POSITION){  
            int action = ev.getAction();  
            switch(action){  
                case MotionEvent.ACTION_UP:  
                    int upY = (int)ev.getY();  
                    stopDrag();  
                    onDrop(upY);  
                    break;  
                case MotionEvent.ACTION_MOVE:  
                    int moveY = (int)ev.getY();  
                    onDrag(moveY);  
                    break;  
                default:break;  
            }  
            return true;  
        }  
        //Ҳ������ѡ�е�Ч��  
        return super.onTouchEvent(ev);  
    }  
      
    /** 
     * ׼���϶�����ʼ���϶����ͼ�� 
     * @param bm 
     * @param y 
     */  
    public void startDrag(Bitmap bm ,int y){  
        stopDrag();  
          
        windowParams = new WindowManager.LayoutParams();  
        windowParams.gravity = Gravity.TOP;  
        windowParams.x = 0;  
        windowParams.y = y - dragPoint + dragOffset;  
        windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;  
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;  
        windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE  
                            | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON  
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;  
        windowParams.format = PixelFormat.TRANSLUCENT;  
        windowParams.windowAnimations = 0;  
  
        ImageView imageView = new ImageView(getContext());  
        imageView.setImageBitmap(bm);  
        windowManager = (WindowManager)getContext().getSystemService("window");  
        windowManager.addView(imageView, windowParams);  
        dragImageView = imageView;  
    }  
      
    /** 
     * ֹͣ�϶���ȥ���϶����ͷ�� 
     */  
    public void stopDrag(){  
        if(dragImageView!=null){  
            windowManager.removeView(dragImageView);  
            dragImageView = null;  
        }  
    }  
      
    /** 
     * �϶�ִ�У���Move������ִ�� 
     * @param y 
     */  
    public void onDrag(int y){  
        if(dragImageView!=null){  
            windowParams.alpha = 0.8f;  
            windowParams.y = y - dragPoint + dragOffset;  
            windowManager.updateViewLayout(dragImageView, windowParams);  
        }  
        //Ϊ�˱��⻬�����ָ��ߵ�ʱ�򣬷���-1������  
        int tempPosition = pointToPosition(0, y);  
        if(tempPosition!=INVALID_POSITION){  
            dragPosition = tempPosition;  
        }  
          
        //����  
        int scrollHeight = 0;  
        if(y<upScrollBounce){  
            scrollHeight = 8;//�������Ϲ���8�����أ�����������Ϲ����Ļ�  
        }else if(y>downScrollBounce){  
            scrollHeight = -8;//�������¹���8�����أ�������������Ϲ����Ļ�  
        }  
          
        if(scrollHeight!=0){  
            //���������ķ���setSelectionFromTop()  
            setSelectionFromTop(dragPosition, getChildAt(dragPosition-getFirstVisiblePosition()).getTop()+scrollHeight);  
        }  
    }  
      
    /** 
     * �϶����µ�ʱ�� 
     * @param y 
     */  
    public void onDrop(int y){  
          
        //Ϊ�˱��⻬�����ָ��ߵ�ʱ�򣬷���-1������  
        int tempPosition = pointToPosition(0, y);  
        if(tempPosition!=INVALID_POSITION){  
            dragPosition = tempPosition;  
        }  
          
        //�����߽紦��  
        if(y<getChildAt(1).getTop()){  
            //�����ϱ߽�  
            dragPosition = 1;  
        }else if(y>getChildAt(getChildCount()-1).getBottom()){  
            //�����±߽�  
            dragPosition = getAdapter().getCount()-1;  
        }  
          
        //���ݽ���  
        if(dragPosition>0 && dragPosition<getAdapter().getCount()-2 && dragSrcPosition!=getAdapter().getCount()-1){  
            @SuppressWarnings("unchecked")  
            ArrayAdapter<Object> adapter = (ArrayAdapter<Object>)getAdapter();  
            Object dragItem = adapter.getItem(dragSrcPosition);  
            adapter.remove(dragItem);  
            adapter.insert(dragItem, dragPosition);  
        } 
    }  
}