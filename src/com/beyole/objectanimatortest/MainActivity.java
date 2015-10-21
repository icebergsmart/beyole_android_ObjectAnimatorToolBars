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
import android.widget.Toast;

public class MainActivity extends Activity {

	//图片资源
	private int[] res = { R.id.id_a, R.id.id_b, R.id.id_c, R.id.id_d, R.id.id_e, R.id.id_f, R.id.id_g, R.id.id_h };
	//存放ImageView
	private List<ImageView> imageViewList = new ArrayList<ImageView>();
	//菜单是不是展开
	private boolean isNotExpand = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//利用循环，初始化ImageView组件
		for (int i = 0; i < res.length; i++) {
			ImageView imageView = (ImageView) findViewById(res[i]);
			//存放在list中
			imageViewList.add(imageView);
		}
	}

	//点击事件
	public void clickview(View view) {
		switch (view.getId()) {
		//主菜单被点击
		case R.id.id_a:
			//主菜单没有展开时被点击
			if (isNotExpand == true) {
				//启动动画
				startAnim();
			} else {
				//关闭动画
				closeAnim();
			}
			break;
			//定义其他组件被点击时触发的事件
		default:
			Toast.makeText(MainActivity.this, "您点击了:" + view.getId(), Toast.LENGTH_LONG).show();
			break;
		}
	}

	//关闭动画
	private void closeAnim() {
		for (int i = 1; i < res.length; i++) {
			float angle = (90 * 1.0f / (res.length - 2)) * (i - 1);
			PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", (float) (Math.sin((angle * 1.57 / 90)) * 200), 0);
			PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", (float) (Math.cos((angle * 1.57 / 90)) * 200), 0);
			ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageViewList.get(i), holder1, holder2);
			// ObjectAnimator animator =
			// ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", i * 60,
			// 0);
			animator.setDuration(300);
			animator.start();
			isNotExpand = true;

		}
	}

	//开始动画
	private void startAnim() {
		//遍历第一个不是主菜单的ImageView列表
		for (int i = 1; i < res.length; i++) {
			//获取展开角度
			float angle = (90 * 1.0f / (res.length - 2)) * (i - 1);
			//获取X位移
			PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 0, (float) (Math.sin((angle * 1.57 / 90)) * 200));
			//获取Y位移
			PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 0, (float) (Math.cos((angle * 1.57 / 90)) * 200));
			//设置ImageView的属性动画
			ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageViewList.get(i), holder1, holder2);
			// ObjectAnimator animator =
			// ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 0, i *
			// 60);
			//动画时间
			animator.setDuration(1000);
			//动画延迟时间
			animator.setFrameDelay(500 * i);
			//设置加速器
			animator.setInterpolator(new BounceInterpolator());
			//启动动画
			animator.start();
			isNotExpand = false;
		}
	}
}
