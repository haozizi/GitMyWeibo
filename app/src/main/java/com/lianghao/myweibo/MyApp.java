package com.lianghao.myweibo;

import android.app.Application;
import android.content.SharedPreferences;

import com.lianghao.myweibo.Bean.AccessToken;
import com.lianghao.myweibo.Datas.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2015/12/17.
 */
public class MyApp extends Application {

    private static MyApp myApp;

    private SharedPreferences sharedPreferences;

    private static AccessToken accessToken = new AccessToken();

    @Override
    public void onCreate() {
        super.onCreate();
        setGlobalAccessToken();
        initImageLoader();
        myApp = new MyApp();
    }

    /**
     * 设置全局的AccessToken
     */
    private void setGlobalAccessToken() {
        sharedPreferences = getSharedPreferences(Constant.SP_USERTOKEN,MODE_PRIVATE);
        String ak = sharedPreferences.getString(Constant.ACCESS_TOKEN,"null");
        accessToken.setAccess_token(ak);
        String uid = sharedPreferences.getString(Constant.UID,"null");
        accessToken.setUid(uid);
    }

    public static AccessToken getAccessToken() {
        return accessToken;
    }

    private void initImageLoader(){
        //ImageLoader.getInstance().init(UniversalImageLoader.getDefaultImageLoaderConfiguration(getApplicationContext()));
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
    }

    public static MyApp getMyApp() {
        return myApp;
    }
}
