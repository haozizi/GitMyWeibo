package com.lianghao.myweibo.Datas;

/**
 * Created by Administrator on 2015/12/18.
 */
public class WeiboUrl {

    /**
     * 获取最新公共微博的URL
     * @return
     */
    public static String getPublicWeiboUrl(int page){
        return new StringBuffer("https://api.weibo.com/2/statuses/friends_timeline.json")
                .append("?access_token=").append(Constant.AT).append("&")
                .append("count=").append(5).append("&")//单页返回的记录条数，最大不超过100，默认为20。
                .append("page=").append(page)   //返回结果的页码，默认为1。
                .toString();
    }
}
