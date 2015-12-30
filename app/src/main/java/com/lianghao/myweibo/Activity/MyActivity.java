package com.lianghao.myweibo.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.lianghao.myweibo.Fragment.DiscoverFragment;
import com.lianghao.myweibo.Fragment.MainPagerFragment;
import com.lianghao.myweibo.Fragment.MeFragment;
import com.lianghao.myweibo.R;
import com.lianghao.myweibo.View.ChangeColorIconWithText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class MyActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private ChangeColorIconWithText mHome;
    private ChangeColorIconWithText mDiscover;
    private ChangeColorIconWithText mMe;
    private LinearLayout mChatLinearLayout;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        initData();
    }


    private void initView()
    {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mHome = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(mHome);
        mDiscover = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(mDiscover);
        mMe = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(mMe);

        mHome.setOnClickListener(this);
        mDiscover.setOnClickListener(this);
        mMe.setOnClickListener(this);

        mHome.setIconAlpha(1.0f);
    }

    private void initData() {
        mDatas = new ArrayList<>();

        MainPagerFragment tab01 = new MainPagerFragment();
        DiscoverFragment tab02 = new DiscoverFragment();
        MeFragment tab03 = new MeFragment();

        mDatas.add(tab01);
        mDatas.add(tab02);
        mDatas.add(tab03);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mDatas.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mDatas.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        resetOtherTabs();

        switch (v.getId())
        {
            case R.id.id_indicator_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
        }

    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs()
    {
        for (int i = 0; i < mTabIndicators.size(); i++)
        {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0)
        {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
