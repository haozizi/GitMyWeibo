package com.lianghao.myweibo.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lianghao.myweibo.Bean.User;
import com.lianghao.myweibo.Datas.WeiboUrl;
import com.lianghao.myweibo.Net.ImageNet;
import com.lianghao.myweibo.R;
import com.lianghao.myweibo.Utils.LogUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.marshalchen.common.commonUtils.urlUtils.HttpUtilsAsync;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2015/12/17.
 */
public class MeFragment extends Fragment {

    private User user;

    @InjectView(R.id.me_headImage)
    ImageView meHeadImage;
    @InjectView(R.id.me_userName)
    TextView meUserName;
    @InjectView(R.id.me_description)
    TextView meDescription;
    @InjectView(R.id.me_weiboNum)
    TextView meWeiboNum;
    @InjectView(R.id.me_concernedNum)
    TextView meConcernedNum;
    @InjectView(R.id.me_fansNum)
    TextView meFansNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("MeFragment onCreate");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!checkIsSaved())
            initDataFromServer();
        else initDataFromLocal();
    }

    private void initDataFromLocal() {
        user = new User();
        SharedPreferences sh = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user.setScreen_name(sh.getString("name"," "));
        user.setAvatar_large(sh.getString("headImageUrl", " "));
        user.setDescription(sh.getString("description", " "));
        user.setFollowers_count(Integer.parseInt(sh.getString("followers_count", " ")));
        user.setFriends_count(Integer.parseInt(sh.getString("friends_count", " ")));
        user.setFollowers_count(Integer.parseInt(sh.getString("statuses_count"," ")));
    }

    private void initDataFromServer() {
        LogUtil.i("url:" + WeiboUrl.getUserInfoUrl());
        HttpUtilsAsync.get(WeiboUrl.getUserInfoUrl(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtil.i("result:" + new String(responseBody));
                Gson gson = new Gson();
                user = gson.fromJson(new String(responseBody), User.class);
                SharedPreferences sh = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("name", user.getScreen_name());
                editor.putString("description", user.getDescription());
                editor.putString("headImageUrl", user.getAvatar_large());
                editor.putString("followers_count", user.getFollowers_count() + "");
                editor.putString("friends_count", user.getFriends_count() + "");
                editor.putString("statuses_count", user.getStatuses_count() + "");
                LogUtil.i("user:" + user.toString());
                initView();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("result:" + new String(responseBody));
                Toast.makeText(getContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        meUserName.setText(user.getScreen_name());
        meDescription.setText(user.getDescription());
        meFansNum.setText(user.getFollowers_count()+"");
        meWeiboNum.setText(user.getStatuses_count()+"");
        meConcernedNum.setText(user.getFriends_count()+"");
        ImageNet.displayImage(user.getAvatar_large(),meHeadImage);
    }

    private boolean checkIsSaved(){
        SharedPreferences sh = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sh.contains("name"))
            return true;
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("MeFragment onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("MeFragment onDestroy");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
