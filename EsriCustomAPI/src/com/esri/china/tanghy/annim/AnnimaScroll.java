package com.esri.china.tanghy.annim;

import android.content.Context;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;


public class AnnimaScroll {

	private Context context;
	
	public AnnimaScroll(Context context ,View content, View menu) {
		super();
		this.content = content;
		this.menu = menu;
		this.context = context;
		initValues();
	}
	
	/**
	 * ��ʼ��һЩ�ؼ������ݡ�������ȡ��Ļ�Ŀ�ȣ���content�����������ÿ�ȣ���menu�����������ÿ�Ⱥ�ƫ�ƾ���ȡ�
	 */
	private void initValues() {
		WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = window.getDefaultDisplay().getWidth();
		menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();
		// ��menu�Ŀ������Ϊ��Ļ��ȼ�ȥmenuPadding
		menuParams.width = screenWidth - menuPadding;
		// ���Ե��ֵ��ֵΪmenu��ȵĸ���
		leftEdge = -menuParams.width;
		// menu��leftMargin����Ϊ���Ե��ֵ��������ʼ��ʱmenu�ͱ�Ϊ���ɼ�
		menuParams.leftMargin = leftEdge;
		// ��content�Ŀ������Ϊ��Ļ���
		content.getLayoutParams().width = screenWidth;
	}

	/**
	 * ������ʾ������menuʱ����ָ������Ҫ�ﵽ���ٶȡ�
	 */
	public static final int SNAP_VELOCITY = 200;

	/**
	 * ��Ļ���ֵ��
	 */
	private int screenWidth;

	/**
	 * menu�����Ի����������Ե��ֵ��menu���ֵĿ��������marginLeft�����ֵ֮�󣬲����ټ��١�
	 */
	private int leftEdge;

	/**
	 * menu�����Ի��������ұ�Ե��ֵ��Ϊ0����marginLeft����0֮�󣬲������ӡ�
	 */
	private int rightEdge = 0;

	/**
	 * menu��ȫ��ʾʱ������content�Ŀ��ֵ��
	 */
	private int menuPadding = 80;

	/**
	 * �����ݵĲ��֡�
	 */
	private View content;

	/**
	 * menu�Ĳ��֡�
	 */
	private View menu;

	/**
	 * menu���ֵĲ�����ͨ���˲���������leftMargin��ֵ��
	 */
	private LinearLayout.LayoutParams menuParams;

	/**
	 * ��¼��ָ����ʱ�ĺ����ꡣ
	 */
	private float xDown;

	/**
	 * ��¼��ָ�ƶ�ʱ�ĺ����ꡣ
	 */
	private float xMove;

	/**
	 * ��¼�ֻ�̧��ʱ�ĺ����ꡣ
	 */
	private float xUp;

	/**
	 * menu��ǰ����ʾ�������ء�ֻ����ȫ��ʾ������menuʱ�Ż���Ĵ�ֵ�����������д�ֵ��Ч��
	 */
	private boolean isMenuVisible;

	/**
	 * ���ڼ�����ָ�������ٶȡ�
	 */
	private VelocityTracker mVelocityTracker;

	
	/**
	 * �жϵ�ǰ���Ƶ���ͼ�ǲ�������ʾcontent�������ָ�ƶ��ľ����Ǹ������ҵ�ǰmenu�ǿɼ��ģ�����Ϊ��ǰ��������Ҫ��ʾcontent��
	 * 
	 * @return ��ǰ��������ʾcontent����true�����򷵻�false��
	 */
	private boolean wantToShowContent() {
		return xUp - xDown < 0 && isMenuVisible;
	}

	/**
	 * �жϵ�ǰ���Ƶ���ͼ�ǲ�������ʾmenu�������ָ�ƶ��ľ������������ҵ�ǰmenu�ǲ��ɼ��ģ�����Ϊ��ǰ��������Ҫ��ʾmenu��
	 * 
	 * @return ��ǰ��������ʾmenu����true�����򷵻�false��
	 */
	private boolean wantToShowMenu() {
		return xUp - xDown > 0 && !isMenuVisible;
	}

	/**
	 * �ж��Ƿ�Ӧ�ù�����menuչʾ�����������ָ�ƶ����������Ļ��1/2��������ָ�ƶ��ٶȴ���SNAP_VELOCITY��
	 * ����ΪӦ�ù�����menuչʾ������
	 * 
	 * @return ���Ӧ�ù�����menuչʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToMenu() {
		return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * �ж��Ƿ�Ӧ�ù�����contentչʾ�����������ָ�ƶ��������menuPadding������Ļ��1/2��
	 * ������ָ�ƶ��ٶȴ���SNAP_VELOCITY�� ����ΪӦ�ù�����contentչʾ������
	 * 
	 * @return ���Ӧ�ù�����contentչʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToContent() {
		return xDown - xUp + menuPadding > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * ����Ļ������menu���棬�����ٶ��趨Ϊ30.
	 */
	private void scrollToMenu() {
		new ScrollTask().execute(30);
	}

	/**
	 * ����Ļ������content���棬�����ٶ��趨Ϊ-30.
	 */
	private void scrollToContent() {
		new ScrollTask().execute(-30);
	}
	
	public void scrollView(){
		
		if(menuParams.leftMargin == 0){
			scrollToContent();
		}else{
			scrollToMenu();
		}
		
	}

	/**
	 * ����VelocityTracker���󣬲�������content����Ļ����¼����뵽VelocityTracker���С�
	 * 
	 * @param event
	 *            content����Ļ����¼�
	 */
	private void createVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	/**
	 * ��ȡ��ָ��content���滬�����ٶȡ�
	 * 
	 * @return �����ٶȣ���ÿ�����ƶ��˶�������ֵΪ��λ��
	 */
	private int getScrollVelocity() {
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	/**
	 * ����VelocityTracker����
	 */
	private void recycleVelocityTracker() {
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... speed) {
			int leftMargin = menuParams.leftMargin;
			// ���ݴ�����ٶ����������棬������������߽���ұ߽�ʱ������ѭ����
			while (true) {
				leftMargin = leftMargin + speed[0];
				if (leftMargin > rightEdge) {
					leftMargin = rightEdge;
					break;
				}
				if (leftMargin < leftEdge) {
					leftMargin = leftEdge;
					break;
				}
				publishProgress(leftMargin);
				// Ϊ��Ҫ�й���Ч��������ÿ��ѭ��ʹ�߳�˯��20���룬�������۲��ܹ���������������
				sleep(20);
			}
			if (speed[0] > 0) {
				isMenuVisible = true;
			} else {
				isMenuVisible = false;
			}
			return leftMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... leftMargin) {
			menuParams.leftMargin = leftMargin[0];
			menu.setLayoutParams(menuParams);
		}

		@Override
		protected void onPostExecute(Integer leftMargin) {
			menuParams.leftMargin = leftMargin;
			menu.setLayoutParams(menuParams);
		}
	}

	/**
	 * ʹ��ǰ�߳�˯��ָ���ĺ�������
	 * 
	 * @param millis
	 *            ָ����ǰ�߳�˯�߶�ã��Ժ���Ϊ��λ
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
