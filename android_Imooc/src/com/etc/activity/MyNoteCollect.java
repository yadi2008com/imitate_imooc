package com.etc.activity;

import com.example.b.R;
import com.example.b.R.layout;
import com.example.b.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MyNoteCollect extends Activity {
	private LinearLayout linout_note = null;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_note_collect);
		linout_note = (LinearLayout) findViewById(R.id.linout_note);
		linout_note.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				linout_note.setBackgroundColor(getResources().getColor(R.color.background));
				intent = new Intent(MyNoteCollect.this, MyNoteContent.class);
				startActivity(intent);
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_note_collect, menu);
		return true;
	}

}
