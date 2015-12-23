package com.lianghao.myweibo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lianghao.myweibo.Datas.Constant;
import com.lianghao.myweibo.Datas.WeiboData;
import com.lianghao.myweibo.Bean.AccessToken;
import com.lianghao.myweibo.MainActivity;
import com.lianghao.myweibo.Utils.LogUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.marshalchen.common.commonUtils.urlUtils.HttpUtilsAsync;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2015/12/16.
 */
public class AuthorizeFragment extends Fragment{

    //private AuthorizeFragment fragment;

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new WebView(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        mWebView.setWebViewClient(new WebViewClient() {
            //在点击请求的是链接时才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，
            //不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，
            //于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String code = Uri.parse(url).getQueryParameter("code");
                //获取accessToken
                HttpUtilsAsync.post(WeiboData.getAccessTokenUri(code), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        LogUtil.i("result:" + new String(responseBody));
                        saveAccessToken(responseBody);
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                return true;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mWebView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("tt",WeiboData.getOAuthUri());
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);     //支持JS，默认JS是禁用的
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);   //不使用缓存，从网络上加载
        settings.setSaveFormData(false);    //设置webView是否保存格式数据，默认为true
        mWebView.loadUrl(WeiboData.getOAuthUri());
    }

    public static AuthorizeFragment newInstance(){
        AuthorizeFragment fragment = new AuthorizeFragment();
        return fragment;
    }

    /**
     * 保存uid,expires_in,access_token到sp中
     * @param result
     */
    private void saveAccessToken(byte[] result){
        Gson gson = new Gson();
        AccessToken accessToken = gson.fromJson(new String(result), AccessToken.class);
        SharedPreferences sharedPreferences = getActivity().
                getSharedPreferences("userToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.UID,accessToken.getUid());
        editor.putString(Constant.EXPIRES_IN,accessToken.getExpires_in());
        editor.putString(Constant.ACCESS_TOKEN,accessToken.getAccess_token());
        editor.commit();
    }
}
