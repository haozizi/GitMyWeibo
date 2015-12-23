package com.lianghao.myweibo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lianghao.myweibo.R;
import com.lianghao.myweibo.Utils.LogUtil;

/**
 * Created by Administrator on 2015/12/17.
 */
public class DiscoverFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("DiscoverFragment onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("DiscoverFragment onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("DiscoverFragment onDestroy");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover,null);
    }
}
