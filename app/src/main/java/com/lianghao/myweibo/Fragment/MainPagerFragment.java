package com.lianghao.myweibo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lianghao.myweibo.Adapter.WeiboListAdapter;
import com.lianghao.myweibo.Bean.Weibo;
import com.lianghao.myweibo.Datas.WeiboUrl;
import com.lianghao.myweibo.MainActivity;
import com.lianghao.myweibo.R;
import com.lianghao.myweibo.Utils.LogUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.marshalchen.common.commonUtils.urlUtils.HttpUtilsAsync;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2015/12/17.
 */
public class MainPagerFragment extends Fragment {

    private LinearLayout waitting;
    private UltimateRecyclerView weiboList;
    private List<Weibo> mWeibos = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private WeiboListAdapter adapter;

    private Weibo latestWeibo;

    private static int PAGE_COUNT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("MainPagerFragment onCreate");
        LogUtil.i(WeiboUrl.getUserConcerenedWeiboUrl(PAGE_COUNT));
    }

    private void initView(View view) {
        weiboList = (UltimateRecyclerView) view.findViewById(R.id.weibo_list);
        waitting = (LinearLayout) view.findViewById(R.id.mainPager_waitting);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("MainPagerFragment onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("MainPagerFragment onDestroy");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWeiboData(1);
        PAGE_COUNT = 1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_pager, null);
        initView(view);
        return view;
    }

    private void initWeiboData(int page) {
        HttpUtilsAsync.get(WeiboUrl.getUserConcerenedWeiboUrl(page), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtil.i(new String(responseBody));
                Gson gson = new Gson();
                String statuses = null;
                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONArray st = jsonObject.getJSONArray("statuses");
                    statuses = st.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mWeibos = gson.fromJson(statuses, new TypeToken<List<Weibo>>() {
                }.getType());
                latestWeibo = mWeibos.get(0);
                initWeiboList();
                waitting.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), "获取微博失败", Toast.LENGTH_SHORT).show();
                if (responseBody != null)
                    LogUtil.i(new String(responseBody));
            }
        });
    }

    private void initWeiboList() {
        adapter = new WeiboListAdapter(mWeibos, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        weiboList.setLayoutManager(linearLayoutManager);
        weiboList.setAdapter(adapter);
        setSwipeReflash();
        setLoadMoreWeibo();

    }

    /**
     * 设置滑动更新
     */
    private void setSwipeReflash() {
        weiboList.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivity.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initWeiboData(1);
                        weiboList.setRefreshing(false);
                        linearLayoutManager.scrollToPosition(0);
                    }
                }, 1000);
            }
        });
    }

    /**
     * 加载更多微博
     */
    private void setLoadMoreWeibo() {
        weiboList.enableLoadmore();
        adapter.setLoadMoreView(LayoutInflater.from(getContext()).inflate(R.layout.load_more_view,null));
        weiboList.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                MainActivity.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtilsAsync.get(WeiboUrl.getUserConcerenedWeiboUrl(++PAGE_COUNT), new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                LogUtil.i(new String(responseBody));
                                Gson gson = new Gson();
                                String statuses = null;
                                try {
                                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                                    JSONArray st = jsonObject.getJSONArray("statuses");
                                    statuses = st.toString();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                List<Weibo> wbs = gson.fromJson(statuses, new TypeToken<List<Weibo>>() {
                                }.getType());
                                for (Weibo wb : wbs) {
                                    adapter.insert(wb, adapter.getAdapterItemCount());
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(getContext(), "获取微博失败", Toast.LENGTH_SHORT).show();
                                if (responseBody != null)
                                    LogUtil.i(new String(responseBody));
                            }
                        });

                    }
                },1000);

            }
        });
    }


}
