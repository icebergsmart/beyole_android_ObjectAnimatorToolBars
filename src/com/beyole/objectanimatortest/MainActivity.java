package com.beyole.objectanimatortest;

import java.util.ArrayList;
import java.util.List;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private int[] res = { R.id.id_a, R.id.id_b, R.id.id_c, R.id.id_d, R.id.id_e, R.id.id_f, R.id.id_g, R.id.id_h };
	private List<ImageView> imageViewList = new ArrayList<ImageView>();
	private boolean isNotExpand = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i = 0; i < res.length; i++) {
			ImageView imageView = (ImageView) findViewById(res[i]);
			imageViewList.add(imageView);
		}
	}

	public void clickview(View view) {
		switch (view.getId()) {
		case R.id.id_a:
			if (isNotExpand == true) {
				startAnim();
			} else {
				closeAnim();
			}
			break;

		default:
			break;
		}
	}

	private void closeAnim() {
		for (int i = 1; i < res.length; i++) {
			ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", i * 60, 0);
			animator.setDuration(300);
			animator.start();
			isNotExpand = true;

		}
	}

	private void startAnim() {
		for (int i = 1; i < res.length; i++) {
			ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 0, i * 60);
			animator.setDuration(500);
			animator.setFrameDelay(500);
			animator.setInterpolator(new BounceInterpolator());
			animator.start();
			isNotExpand = false;
		}
	}
}
