package com.lianghao.myweibo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lianghao.myweibo.Fragment.DiscoverFragment;
import com.lianghao.myweibo.Fragment.MainPagerFragment;
import com.lianghao.myweibo.Fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    @InjectView(R.id.home_pager_image)
    ImageView homePagerImage;
    @InjectView(R.id.home_pager_text)
    TextView homePagerText;
    @InjectView(R.id.discover_image)
    ImageView discoverImage;
    @InjectView(R.id.discover_text)
    TextView discoverText;
    @InjectView(R.id.me_image)
    ImageView meImage;
    @InjectView(R.id.me_text)
    TextView meText;
    @InjectView(R.id.leftdrawer)
    ListView drawerList;
    @InjectView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @InjectView(R.id.click_menuButton)
    ImageView clickMenuButton;
    private ViewPager viewPager;

    private List<ImageView> imageViews = new ArrayList<>();

    private List<Fragment> fragmentList = new ArrayList<>();

    private MainPagerFragment mainPagerFragment = new MainPagerFragment();
    private DiscoverFragment discoverFragment = new DiscoverFragment();
    private MeFragment meFragment = new MeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
        initView();
    }

    private void initData() {
        fragmentList.add(mainPagerFragment);
        fragmentList.add(discoverFragment);
        fragmentList.add(meFragment);

        imageViews.add(homePagerImage);
        imageViews.add(discoverImage);
        imageViews.add(meImage);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int[] imageIds = initImageIds();
                switch (position) {
                    case 0:
                        imageIds[0] = R.drawable.home_selected;
                        setTextColor(homePagerText, discoverText, meText);
                        break;
                    case 1:
                        imageIds[1] = R.drawable.discover_selected;
                        setTextColor(discoverText, homePagerText, meText);
                        break;
                    case 2:
                        imageIds[2] = R.drawable.me_selected;
                        setTextColor(meText, discoverText, homePagerText);
                        break;
                }
                changImage(imageIds);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homePagerImage.setOnClickListener(this);
        discoverImage.setOnClickListener(this);
        meImage.setOnClickListener(this);
        clickMenuButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int index = 0;
        int[] imageIds = initImageIds();
        switch (v.getId()) {
            case R.id.home_pager_image:
                setTextColor(homePagerText, discoverText, meText);
                imageIds[0] = R.drawable.home_selected;
                index = 0;
                break;
            case R.id.discover_image:
                setTextColor(discoverText, homePagerText, meText);
                imageIds[1] = R.drawable.discover_selected;
                index = 1;
                break;
            case R.id.me_image:
                setTextColor(meText, discoverText, homePagerText);
                imageIds[2] = R.drawable.me_selected;
                index = 2;
                break;
            case R.id.click_menuButton:
                drawerlayout.openDrawer(Gravity.LEFT);
                break;
        }
        changImage(imageIds);
        viewPager.setCurrentItem(index);
    }

    private int[] initImageIds() {
        int[] imageIds = new int[]{R.drawable.home_normal,
                R.drawable.discover_normal,
                R.drawable.me_normal};
        return imageIds;
    }

    /**
     * 改变图片样式
     *
     * @param ids
     */
    private void changImage(int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            imageViews.get(i).setImageResource(ids[i]);
        }
    }

    /**
     * 改变导航栏字体颜色
     *
     * @param tv1
     * @param tv2
     * @param tv3
     */
    public void setTextColor(TextView tv1, TextView tv2, TextView tv3) {
        int restoreColorId = R.color.bottomTextColor;
        tv1.setTextColor(getResources().getColor(R.color.bottomTextColorSelected));
        tv2.setTextColor(getResources().getColor(restoreColorId));
        tv3.setTextColor(getResources().getColor(restoreColorId));
    }
}
