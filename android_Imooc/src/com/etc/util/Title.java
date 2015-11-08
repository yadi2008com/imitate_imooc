package com.etc.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.etc.activity.SearchActivity;
import com.example.b.R;

public class Title {
	private Context context;
	private Intent intent;
	private ImageButton leftMenu = null, rightMenu = null, search = null;
	private TextView titleName = null;
	private DrawerLayout mDrawerLayout = null;// 左侧抽式菜单

	public Title(Context context) {
		this.context = context;
	}

	public void init(View titleView, View leftMenuView) {

		leftMenu = (ImageButton) titleView.findViewById(R.id.leftMenu);
		rightMenu = (ImageButton) titleView.findViewById(R.id.rightMenu);
		search = (ImageButton) titleView.findViewById(R.id.search);
		titleName = (TextView) titleView.findViewById(R.id.titleName);
		mDrawerLayout = (DrawerLayout) leftMenuView
				.findViewById(R.id.menu_layout);
	}

	public void initListener() {
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(context, SearchActivity.class);
				context.startActivity(intent);
			}
		});
		// 左侧菜单的点击事件
		leftMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});
		// 右侧菜单的点击事件
		rightMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDrawerLayout.openDrawer(Gravity.RIGHT);
			}
		});
	}
}
