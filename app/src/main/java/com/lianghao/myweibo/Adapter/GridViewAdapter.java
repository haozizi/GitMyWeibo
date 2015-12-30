package com.lianghao.myweibo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lianghao.myweibo.Net.ImageNet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;

    private List<String> urls = new ArrayList<>();

    public GridViewAdapter(List<String> imageUrls,Context context){
        this.urls = imageUrls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = new ImageView(context);
        if (getCount()==1){
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(300,300);
            iv.setLayoutParams(params);
        }
        Log.i("bb", "setting image url" + position + "is:" + urls.get(position));
        //ImageNet.setImage(urls.get(position), iv);
        //ImageNet.loadImage(urls.get(position), iv);
        ImageNet.displayImage(urls.get(position),iv);
        return iv;
    }

}
