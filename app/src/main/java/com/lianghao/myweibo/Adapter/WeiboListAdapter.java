package com.lianghao.myweibo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lianghao.myweibo.Bean.Weibo;
import com.lianghao.myweibo.R;
import com.marshalchen.common.commonUtils.urlUtils.UniversalImageLoader;
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

    private Context context;

    public WeiboListAdapter(List<Weibo> datas, Context context) {
        this.weibos = datas;
        this.context = context;
    }


    public void insert(Weibo weibo, int position) {
        insert(weibos, weibo, position);
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
        if (position >= weibos.size()) return;
        Weibo weibo = weibos.get(position);
        ((ViewHolder) holder).name.setText(weibo.getUser().getScreen_name());
        String createTime = weibo.getCreated_at();
        ((ViewHolder) holder).create_time.setText(createTime.substring(createTime.indexOf(":") - 2, createTime.indexOf("+")));
        String source = weibo.getSource();
        if (source.length() > 0) {
            ((ViewHolder) holder).source.setText("来自  " + source.substring(source.indexOf(">") + 1, source.length() - 4));
        }
        //设置微博内容
        ((ViewHolder) holder).content.setText(weibo.getText() + "");
        ((ViewHolder) holder).repost.setText("转发 " + weibo.getReposts_count() + "");
        ((ViewHolder) holder).comment.setText("评论 " + weibo.getComments_count() + "");
        ((ViewHolder) holder).attitude.setText("点赞 " + weibo.getAttitudes_count() + "");
        //异步网络请求设置头像
        setImage(weibo.getUser().getProfile_image_url(), ((ViewHolder) holder).headImage);
        //如果微博是转发的
        if (weibo.getRetweeted_status() != null) {
            String name = weibo.getRetweeted_status().getUser().getName();
            String content = weibo.getRetweeted_status().getText();
            ((ViewHolder) holder).inner_name.setText("@" + name);
            ((ViewHolder) holder).inner_content.setText(content);
        } else ((ViewHolder) holder).parent.removeView(((ViewHolder) holder).inner);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int pWidth = wm.getDefaultDisplay().getWidth();
        int pHeight = wm.getDefaultDisplay().getHeight();
        //如果有图片
        if (weibo.getPic_urls().size() > 0) {
            List<String> ivUrls = new ArrayList<>();
            //如果只有一张图片
            if (weibo.getPic_urls().size() == 1) {
                String url = new String(subOnePicUrl(weibo.getPic_urls().toString()));
                Log.i("bb", url);
                ivUrls.add(url);
            } else {
                for (int i = 0; i < weibo.getPic_urls().size(); i++) {
                    String url = new String(subPicUrl(weibo.getPic_urls().get(i).toString()));
                    ivUrls.add(url);
                }
                if (ivUrls.size()>6)
                    ((ViewHolder) holder).images.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, pHeight/3-50));
                else if (ivUrls.size()>3&&ivUrls.size()<=6)
                    ((ViewHolder) holder).images.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, pHeight/4));
                else  ((ViewHolder) holder).images.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ((ViewHolder) holder).images.setVerticalSpacing(20);
                ((ViewHolder) holder).images.setHorizontalSpacing(20);
                Log.i("bb", "ivUrls:" + ivUrls + ",add urls length is " + ivUrls.size());
                GridViewAdapter adapter = new GridViewAdapter(ivUrls,context);
                ((ViewHolder) holder).images.setAdapter(adapter);
            }
        }else {

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void setLoadMoreView(View view) {
        customLoadMoreView = view;
    }

    class ViewHolder extends UltimateRecyclerviewViewHolder {

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
        LinearLayout imageLayout;

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

    private void setImage(String url, ImageView iv) {
        ImageLoader.getInstance().displayImage(url, iv,
                UniversalImageLoader.getDefaultImageOptions());
    }

    private String subOnePicUrl(String ss) {
        String url = ss.substring(ss.indexOf("=") + 1, ss.length() - 2);
        return url;
    }

    private String subPicUrl(String ss) {
        String url = ss.substring(ss.indexOf("=") + 1, ss.length() - 1);
        return url;
    }
}
