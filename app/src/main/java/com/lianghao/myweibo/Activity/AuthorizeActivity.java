package com.lianghao.myweibo.Activity;

import android.support.v4.app.Fragment;

import com.lianghao.myweibo.Fragment.AuthorizeFragment;

/**
 * Created by Administrator on 2015/12/16.
 */
public class AuthorizeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return AuthorizeFragment.newInstance();
    }
}
