package com.lianghao.myweibo.Bean;

/**
 * Created by Administrator on 2015/12/18.
 */
public class Weibo {

    private String created_at;  //微博创建时间

    private long id; //微博ID

    private long mid;    //微博MID

    private String idstr;//字符串型的微博ID

    private String text;//微博信息内容

    private String source;//微博来源

    private boolean favorited;//是否已收藏,true：是，false：否

    private boolean truncated;//是否被截断，true：是，false：否

    private String thumbnail_pic;//缩略图片地址，没有时不返回此字段

    private String original_pic;//原始图片地址，没有时不返回此字段

    private int reposts_count;    //转发数

    private int comments_count;    //评论数

    private int attitudes_count;//表态数

    private User user;    //object	微博作者的用户信息字段

    private Weibo retweeted_status;//object	被转发的原微博信息字段，当该微博为转发微博时返回 详细

    public String getCreated_at() {
        return created_at;
    }

    public long getId() {
        return id;
    }

    public long getMid() {
        return mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public User getUser() {
        return user;
    }

    public Weibo getRetweeted_status() {
        return retweeted_status;
    }

    @Override
    public String toString() {
        return "创建时间：" + getCreated_at() +
                "微博ID：" + getId() +
                "转发数:" + getReposts_count() +
                "评论数:" + getComments_count() +
                "表态数:" + getAttitudes_count() +
                getText();
    }
}
