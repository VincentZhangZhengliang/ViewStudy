package com.topzrt.viewstudy;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private Context mContext;
    private FrameLayout mRealTabContent;
    private FragmentTabHost mTabHost;
    private FrameLayout mTabContent;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, InvestFragment.class, EventFragment.class, MineFragment.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home, R.drawable.tab_invest, R.drawable.tab_event, R.drawable.tab_mine};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "投资", "活动", "我的"};
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initListener();
    }

    private void initListener() {
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int currentTab = mTabHost.getCurrentTab();
                for (int i = 0; i < mCount; i++) {
                    if (i == currentTab) {
                        View view = mTabHost.getTabWidget().getChildAt(currentTab);
                        TextView textview = view.findViewById(R.id.textview);
                        textview.setTextColor(getResources().getColor(R.color.app_main_color));
                    } else {
                        View view = mTabHost.getTabWidget().getChildAt(i);
                        TextView textview = view.findViewById(R.id.textview);
                        textview.setTextColor(Color.BLACK);
                    }
                }
            }
        });
    }

    private void initView() {
        //布局填充器
        layoutInflater = LayoutInflater.from(this);
        //显示fragment
        mRealTabContent = (FrameLayout) findViewById(R.id.realtabcontent);
        //设置Tabhost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //去除TabHost的分割线
        mTabHost.getTabWidget().setDividerDrawable(null);
        //包裹Tab的容器
        mTabContent = (FrameLayout) findViewById(android.R.id.tabcontent);
        //获取fragment的数量
        mCount = fragmentArray.length;
        //将tab和fragment对应添加
        for (int i = 0; i < mCount; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }

    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageview = view.findViewById(R.id.imageview);
        TextView textview = view.findViewById(R.id.textview);
        imageview.setImageResource(mImageViewArray[i]);
        textview.setText(mTextviewArray[i]);
        if (i == 0) {
            textview.setTextColor(getResources().getColor(R.color.app_main_color));
        }
        return view;
    }
}

