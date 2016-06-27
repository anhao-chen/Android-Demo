package com.chen.viewpagertubatu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.chen.viewpagertubatu.ui.ClipViewPager;
import com.chen.viewpagertubatu.ui.MySinkingView;

public class MainActivity2 extends Activity {

	private ClipViewPager mViewPager;

	private MySinkingView mSinkingView1;
	private MySinkingView mSinkingView2;
	private MySinkingView mSinkingView3;
	private List views;
	private MyPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mViewPager = (ClipViewPager) findViewById(R.id.viewpager);
		mViewPager.setPageTransformer(true, new ScalePageTransformer());

		findViewById(R.id.page_container).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						return mViewPager.dispatchTouchEvent(event);
					}
				});

		mAdapter = new MyPagerAdapter();

		initViews2();

	}

	private void initViews2() {
		views = new ArrayList<MySinkingView>();

		View item = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.item_viewpager, null);
		View item2 = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.item_viewpager, null);
		View item3 = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.item_viewpager, null);

		mSinkingView1 = (MySinkingView) item.findViewById(R.id.sinking);
		mSinkingView2 = (MySinkingView) item2.findViewById(R.id.sinking);
		mSinkingView3 = (MySinkingView) item3.findViewById(R.id.sinking);

		mSinkingView1.setPercent(0.2f);
		mSinkingView2.setPercent(0.5f);
		mSinkingView3.setPercent(0.7f);

		views.add(mSinkingView1);
		views.add(mSinkingView2);
		views.add(mSinkingView3);

		mViewPager.setOffscreenPageLimit(views.size());
		mViewPager.setAdapter(mAdapter);

	}

	class MyPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			// TODO 返回ViewPager显示的页数
			return views.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO 实例化指定位置的View
			// 1) 将当前显示的View增加到ViweGroup中
			container.addView((View) views.get(position));

			// 2) 将当前的页面的View作为数据返回
			return views.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO 销毁离当前页面超出一页的View
			container.removeView((View) views.get(position)); // 移除指定位置的页面
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			// TODO 判断当前页面的View与数据是否一致
			return view == obj;
		}
		
		
		

	}

}
