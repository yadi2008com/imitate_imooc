package com.etc.activity.setting;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StatFs;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.etc.activity.MainActivity;
import com.etc.activity.PersonalSetActivity;
import com.etc.bean.UsersBean;
import com.etc.dao.MyExitDao;
import com.etc.dao.MyExitDaoImpl;
import com.etc.dao.SelectUserInfoDao;
import com.example.b.R;

public class MySettingActivity extends Activity {
	private TextView tv_cache_number, tv_username;
	private ImageView iv_wifi_setting, iv_auto_play_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_setting);
		// 设置清除缓存显示目前手机内存可用的空间
		String availSize = getCacheSize(MySettingActivity.this);
		tv_cache_number = (TextView) findViewById(R.id.tv_cache_number);
		iv_wifi_setting = (ImageView) findViewById(R.id.iv_wifi_setting);
		iv_auto_play_setting = (ImageView) findViewById(R.id.iv_auto_play_setting);
		tv_cache_number.setText("当前可用：" + availSize);
		tv_username = (TextView) findViewById(R.id.tv_username);
		// 实例化wifi播放设置 按钮
		this.initWifiBt(MySettingActivity.this);
		// 实例化自动播放按钮
		this.initAutoPlay(MySettingActivity.this);
		// 实例化当前的用户名
		initUserName();
	}

	// 实例化自动播放按钮，从sharedperference中获取自动播放按钮的信息
	private void initAutoPlay(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"setting", Context.MODE_PRIVATE);
		String autoplay = sharedPreferences.getString("autoplay", "true");
		if (autoplay.equals("true")) {
			iv_auto_play_setting.setImageResource(R.drawable.checked_bg);
		} else {
			iv_auto_play_setting.setImageResource(R.drawable.unchecked_bg);
		}

	}

	// 当点击意见反馈时触发的事件
	public void opinion(View v) {
		Intent intent = new Intent(MySettingActivity.this,
				MyOpinionActivity.class);
		startActivity(intent);
		// 设置切换动画，从右边进入，左边退出
		overridePendingTransition(R.anim.out_to_left, R.anim.in_from_right);
	}

	private void initWifiBt(Context context) {
		// 获取Sharedperference表中，关于非wifi条件下是否能播放的配置信息
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"setting", Context.MODE_PRIVATE);
		String nowifi = sharedPreferences.getString("nowifi", "false");
		if (nowifi.equals("false")) {
			iv_wifi_setting.setImageResource(R.drawable.unchecked_bg);
		} else {
			iv_wifi_setting.setImageResource(R.drawable.checked_bg);
		}
	}

	// 当点击给我评分时触发的事件
	public void grade(View v) {
		Intent intent = new Intent(MySettingActivity.this,
				MyGradeActivity.class);
		startActivity(intent);
		// 设置切换动画，从右边进入，左边退出
		overridePendingTransition(R.anim.out_to_left, R.anim.in_from_right);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 视频自动播放设置，当点击了视频自动播放按钮时
	public void autoPlay(View v) {
		SharedPreferences sharedPreferences = MySettingActivity.this
				.getSharedPreferences("setting", Context.MODE_PRIVATE);
		String autoPlay = sharedPreferences.getString("autoplay", "true");
		Editor editor = sharedPreferences.edit();
		if (autoPlay.equals("false")) {
			iv_auto_play_setting.setImageResource(R.drawable.checked_bg);
			editor.putString("autoplay", "true");
			editor.commit();
		} else {
			iv_auto_play_setting.setImageResource(R.drawable.unchecked_bg);
			editor.putString("autoplay", "false");
			editor.commit();
		}
	}

	// 允许非wifi的网络播放
	public void wifiSetting(View v) {
		SharedPreferences sharedPreferences = MySettingActivity.this
				.getSharedPreferences("setting", Context.MODE_PRIVATE);
		String nowifi = sharedPreferences.getString("nowifi", "false");
		if (nowifi.equals("true")) {

			iv_wifi_setting.setImageResource(R.drawable.unchecked_bg);
			Editor editor = sharedPreferences.edit();
			editor.putString("nowifi", "false");
			editor.commit();
		} else {
			iv_wifi_setting.setImageResource(R.drawable.checked_bg);
			Editor editor = sharedPreferences.edit();
			editor.putString("nowifi", "true");
			editor.commit();
		}
	}

	// 返回缓存的大小
	@SuppressLint("NewApi")
	public String getCacheSize(Context context) {

		String availStr;
		File path = context.getCacheDir();
		// 通过Statfs类得到文件的情况
		StatFs fs = new StatFs(path.getPath());
		// 得到内存中每一个块的大小。
		long blockSize = fs.getBlockSizeLong();
		// 得到可用的内存块的数量。
		long availBlocks = fs.getAvailableBlocksLong();
		long avaliSize = availBlocks * blockSize;
		availStr = android.text.format.Formatter.formatFileSize(
				MySettingActivity.this, avaliSize);
		return availStr;
	}

	// 当点击清除缓存时调用的方法，用来清除缓存
	public void clearCache(View v) {
		AlertDialog.Builder builder = new Builder(MySettingActivity.this);
		builder.setMessage("您确定要清除应用缓存吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				File file = getCacheDir();
				deleteFilesByDirectory(file);
				// 修改显示的剩余空间
				String availSize = getCacheSize(MySettingActivity.this);
				tv_cache_number.setText("当前可用：" + availSize);
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	/** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
	private static void deleteFilesByDirectory(File directory) {
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
	}

	// 当点击关于慕课网时调用的方法。跳到关于慕课网的activity
	public void aboutImooc(View v) {
		Intent intent = new Intent(MySettingActivity.this,
				MyAboutImoocActivity.class);
		startActivity(intent);
		// 设置切换动画，从右边进入，左边退出
		overridePendingTransition(R.anim.out_to_left, R.anim.in_from_right);
	}

	// 当点击退出登录时触发的事件：1.删除SQLite中存的当前用户的数据，
	// 2.跳转到mainAcitivity
	public void logOff(View v) {
		MyExitDao myExitDao = new MyExitDaoImpl(MySettingActivity.this);
		int result = myExitDao.deleteCurrentUserInfo();
		if (result > 0) {
			Intent intent = new Intent();
			intent.setClass(MySettingActivity.this, MainActivity.class);
			startActivity(intent);
		}
	}
	//当点击名字时触发的事件
	public void userinfo(View v){
		Intent intent = new Intent(MySettingActivity.this,
				PersonalSetActivity.class);
		startActivity(intent);
		// 设置切换动画，从右边进入，左边退出
		overridePendingTransition(R.anim.out_to_left, R.anim.in_from_right);
	}
	
	//实例化用户信息
	public void initUserName() {
		try {
			SelectUserInfoDao selectUserInfoDao = new SelectUserInfoDao(
					MySettingActivity.this);
			UsersBean usersBean = selectUserInfoDao.SelectLoginUser();
			String name = usersBean.getUsername();
			tv_username.setText(name);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
