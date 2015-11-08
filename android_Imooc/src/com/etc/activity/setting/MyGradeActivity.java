package com.etc.activity.setting;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.example.b.R;

public class MyGradeActivity extends Activity {
	private RatingBar rb_grade;
	private TextView tv_gradeshow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_grade);
		rb_grade = (RatingBar) findViewById(R.id.rb_grade);
		tv_gradeshow = (TextView) findViewById(R.id.tv_gradeshow);
		// 实例化ratingBar评分条
		initRatingBar(MyGradeActivity.this);
		//为评分条添加监听
		rb_grade.setOnRatingBarChangeListener(new ratingBarChangeListenerImpl());
	}
	class ratingBarChangeListenerImpl implements OnRatingBarChangeListener{
		public void onRatingChanged(RatingBar ratingBar, float grade, boolean fromUser) {
			SharedPreferences sp=MyGradeActivity.this.getSharedPreferences("setting", Context.MODE_PRIVATE);
			Editor editor=sp.edit();
			int gradeInt=(int)grade;
			switch (gradeInt) {
			case 0:
				tv_gradeshow.setText("超级不满意");
				break;
			case 1:
				tv_gradeshow.setText("很不满意");
				break;
			case 2:
				tv_gradeshow.setText("不满意");
				break;
			case 3:
				tv_gradeshow.setText("还好");
				break;
			case 4:
				tv_gradeshow.setText("满意");
				break;
			case 5:
				tv_gradeshow.setText("非常满意");
				break;

			default:
				break;
			}
			editor.putFloat("grade", grade);
			editor.commit();
		}
		
	}
	private void initRatingBar(Context context) {
		SharedPreferences sp = context.getSharedPreferences("setting",
				Context.MODE_PRIVATE);
		//从sharedPreference中获的用户的评分记录
		float grade = sp.getFloat("grade", 0);
		rb_grade.setRating(grade);
		int gradeInt = (int) grade;
		switch (gradeInt) {
		case 0:
			tv_gradeshow.setText("超级不满意");
			break;
		case 1:
			tv_gradeshow.setText("很不满意");
			break;
		case 2:
			tv_gradeshow.setText("不满意");
			break;
		case 3:
			tv_gradeshow.setText("还好");
			break;
		case 4:
			tv_gradeshow.setText("满意");
			break;
		case 5:
			tv_gradeshow.setText("非常满意");
			break;

		default:
			break;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
