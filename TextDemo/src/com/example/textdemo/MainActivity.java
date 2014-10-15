package com.example.textdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView mTextView;
	RelativeLayout totalLayout;
	StrokeTextView cTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.mTextView);
		mTextView.setShadowLayer(20, 0, 0, getResources().getColor(R.color.textBadgeColor));
		
		cTextView = (StrokeTextView)findViewById(R.id.cTextView);
		cTextView.setText("700/¿Ô≥Ã");
	}
}
