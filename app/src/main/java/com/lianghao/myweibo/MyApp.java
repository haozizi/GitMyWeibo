package com.lianghao.myweibo;

import android.app.Application;
import android.content.SharedPreferences;

import com.lianghao.myweibo.Datas.Constant;

/**
 * Created by Administrator on 2015/12/17.
 */
public class MyApp extends Application {

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(Constant.SP_USERTOKEN,MODE_PRIVATE);
        sharedPreferences.contains(Constant.ACCESS_TOKEN);
    }


}
