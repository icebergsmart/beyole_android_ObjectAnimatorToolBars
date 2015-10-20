package com.beyole.objectanimatortest;

import java.util.ArrayList;
import java.util.List;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
			float angle = (90 * 1.0f / (res.length - 1)) * (i-1);
			PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX",  (float) (Math.sin((angle / 90) * 1.57) * 200),0);
			PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", (float) (Math.cos((angle / 90) * 1.57) * 200),0);
			ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageViewList.get(i), holder1, holder2);
			//ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", i * 60, 0);
			animator.setDuration(300);
			animator.start();
			isNotExpand = true;

		}
	}

	private void startAnim() {
		for (int i = 1; i < res.length; i++) {
			float angle = (90 * 1.0f / (res.length - 1)) * (i-1);
			PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 0, (float) (Math.sin((angle / 90) * 1.57) * 200));
			PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 0, (float) (Math.cos((angle / 90) * 1.57) * 200));
			ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageViewList.get(i), holder1, holder2);
			// ObjectAnimator animator =
			// ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 0, i *
			// 60);
			animator.setDuration(1000);
			animator.setFrameDelay(500 * i);
			animator.setInterpolator(new BounceInterpolator());
			animator.start();
			isNotExpand = false;
		}
	}
}
