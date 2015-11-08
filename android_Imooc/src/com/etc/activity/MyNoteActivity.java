package com.etc.activity;

import java.util.ArrayList;
import java.util.List;

import com.etc.util.Title;
import com.example.b.R;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import android.view.Window;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.b.R;

public class MyNoteActivity extends Activity {
	private ViewPager mPager;// 页卡内容
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t0, t1;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	LocalActivityManager manager=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_note);
		
		manager=new LocalActivityManager(this,true);
		manager.dispatchCreate(savedInstanceState);
//		Title title = new Title(this);
//		View titleView = this.findViewById(R.id.title);

		t0 = (TextView) findViewById(R.id.text1);
		t1 = (TextView) findViewById(R.id.text2);
		/**
		 * 初始化ViewPager
		 */

		mPager = (ViewPager) findViewById(R.id.vPager);
		t0.setOnClickListener(new MyOnClickListener(0));
		t1.setOnClickListener(new MyOnClickListener(1));
		listViews = new ArrayList<View>();
		Intent intent = new Intent(MyNoteActivity.this, MyNoteOriginal.class);
		listViews.add(getView("MyNoteOriginal",intent));
		Intent intent2=new Intent(MyNoteActivity.this,MyNoteOriginal.class);        //!!!!!
		listViews.add(getView("MyNoteCollect", intent2));
		
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

		/**
		 * 初始化动画
		 */

		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.h)
				.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	private View getView(String id,Intent intent) {
		
		return manager.startActivity(id, intent).getDecorView();
	}

	/**
	 * 
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
			int two = one * 2;// 页卡1 -> 页卡3 偏移量
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
