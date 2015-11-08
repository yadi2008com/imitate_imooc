package com.etc.util;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.etc.activity.CourseLeiActivity;
import com.example.b.R;

public class RightMenu {
	private Context context;
	private ListView mylistview;
	private ArrayList<String> list = new ArrayList<String>();

	public RightMenu(Context context) {
		this.context = context;
	}

	/**
	 * 实例化右侧菜单的子项
	 * @author 陈雅迪
	 */
	public void initRightMenuItem(View right_drawer) {

		mylistview = (ListView) right_drawer.findViewById(R.id.rightmenulist);
		list.add("全部课程");
		list.add("SQLServer");
		list.add("C");
		list.add("大数据");
		list.add("Java");
		ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(context,
				R.layout.search_list_item, list);

		mylistview.setAdapter(myArrayAdapter);

	}

	/**
	 * 左侧菜单的单击事件
	 */
	public void rightMenuItemSetOnclickListener() {
		mylistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(context, CourseLeiActivity.class);
				context.startActivity(intent);
				
			}
		});
	}

	/**
	 * 
	 * @author 陈雅迪
	 * @功能 右侧菜单的单击事件的具体实现
	 */
	public class leftMenuOnclickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {

			switch (view.getId()) {
			case R.id.all_course:

				break;
			case R.id.my_course:

				break;

			default:
				break;
			}
		}
	}
}