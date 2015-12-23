package com.lianghao.myweibo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.lianghao.myweibo.*;
import com.lianghao.myweibo.Datas.Constant;

/**
 * Created by Administrator on 2015/12/17.
 */
public class StartActivity extends Activity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    //startActivity(new Intent(StartActivity.this, com.lianghao.myweibo.Activity.MainActivity.class));
                    finish();
                    break;
                case 2:
                    startActivity(new Intent(StartActivity.this, AuthorizeActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                if (checkIsValiable())
                    handler.sendEmptyMessage(1);
                else
                    handler.sendEmptyMessage(2);
            }
        }).start();
    }

    private boolean checkIsValiable(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.SP_USERTOKEN,MODE_PRIVATE);
        if (sharedPreferences.contains(Constant.ACCESS_TOKEN))
            return true;
        return false;
    }
}
