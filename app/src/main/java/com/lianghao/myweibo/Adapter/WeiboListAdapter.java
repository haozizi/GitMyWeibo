package com.lianghao.myweibo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lianghao.myweibo.Bean.Weibo;
import com.lianghao.myweibo.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/22.
 */
public class WeiboListAdapter extends UltimateViewAdapter {

    private static List<Weibo> weibos = new ArrayList<>();

    public WeiboListAdapter(List<Weibo> datas){
        this.weibos = datas;
    }


    @Override
    public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weibo_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public int getAdapterItemCount() {
        return weibos.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Weibo weibo = weibos.get(position);
        ((ViewHolder) holder).name.setText(weibo.getUser().getScreen_name());
        String createTime = weibo.getCreated_at();
        ((ViewHolder)holder).create_time.setText(createTime.substring(createTime.indexOf(":")-2,createTime.indexOf("+")));
        String source = weibo.getSource();
        if (source.length()>0){
            ((ViewHolder)holder).source.setText("来自  "+source.substring(source.indexOf(">")+1,source.length()-4));
        }
        ((ViewHolder)holder).content.setText(weibo.getText()+"");
        ((ViewHolder)holder).repost.setText(weibo.getReposts_count()+"");
        ((ViewHolder)holder).comment.setText(weibo.getComments_count() + "");
        ((ViewHolder)holder).attitude.setText(weibo.getAttitudes_count() + "");
        ImageLoader.getInstance().displayImage(weibo.getUser().getProfile_image_url(), ((ViewHolder) holder).headImage);
        if (weibo.getRetweeted_status()!=null){
            String name = weibo.getRetweeted_status().getUser().getName();
            String content = weibo.getRetweeted_status().getText();
            ((ViewHolder)holder).inner_name.setText("@"+name);
            ((ViewHolder)holder).inner_content.setText(content);
        }else ((ViewHolder)holder).parent.removeView(((ViewHolder)holder).inner);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends UltimateRecyclerviewViewHolder{

        LinearLayout parent;
        ImageView headImage;
        TextView name;
        TextView create_time;
        TextView source;
        TextView content;
        GridView images;
        TextView repost;
        TextView comment;
        TextView attitude;
        LinearLayout inner;
        TextView inner_name;
        TextView inner_content;

        public ViewHolder(View itemView) {
            super(itemView);
            parent = (LinearLayout) itemView.findViewById(R.id.weibo_item_layout);
            headImage = (ImageView) itemView.findViewById(R.id.weibo_user_headIamge);
            name = (TextView) itemView.findViewById(R.id.weibo_user_name);
            create_time = (TextView) itemView.findViewById(R.id.weibo_create_time);
            source = (TextView) itemView.findViewById(R.id.weibo_source);
            content = (TextView) itemView.findViewById(R.id.weibo_content);
            images = (GridView) itemView.findViewById(R.id.weibo_gridview);
            repost = (TextView) itemView.findViewById(R.id.weibo_repost_num);
            comment = (TextView) itemView.findViewById(R.id.weibo_comment_num);
            attitude = (TextView) itemView.findViewById(R.id.weibo_attitude_num);
            inner = (LinearLayout) itemView.findViewById(R.id.weibo_inner);
            inner_name = (TextView) itemView.findViewById(R.id.weibo_inner_name);
            inner_content = (TextView) itemView.findViewById(R.id.weibo_inner_content);
        }
    }
}
