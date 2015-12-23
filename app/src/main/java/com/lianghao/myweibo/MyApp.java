package com.lianghao.myweibo;

import android.app.Application;
import android.content.SharedPreferences;

import com.lianghao.myweibo.Datas.Constant;
import com.lianghao.myweibo.Bean.AccessToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2015/12/17.
 */
public class MyApp extends Application {

    private SharedPreferences sharedPreferences;

    private static AccessToken accessToken = new AccessToken();

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(Constant.SP_USERTOKEN,MODE_PRIVATE);
        String ak = sharedPreferences.getString(Constant.ACCESS_TOKEN,"null");
        accessToken.setAccess_token(ak);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
    }

    public static AccessToken getAccessToken() {
        return accessToken;
    }
}
