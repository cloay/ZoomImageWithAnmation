package com.example.zoomimagewithanimation;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imageV;
	private ImageView scaleImageV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scaleImageV = (ImageView) findViewById(R.id.imageview_scale);
		imageV = (ImageView) findViewById(R.id.imageView1);
		
		imageV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Util.browerImage(MainActivity.this, imageV, scaleImageV);
			}
		});
		scaleImageV.setImageBitmap(Util.getBitmap(this, R.drawable.hh));
		scaleImageV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Util.closeImage(MainActivity.this, imageV, scaleImageV);
			}
		});
	}
}
