package com.lianghao.myweibo.Bean;

/**
 * Created by Administrator on 2015/12/18.
 */
public class User {

    private long id; //用户ID

    private String idstr;//字符串型的用户ID

    private String screen_name;//string	用户昵称

    private String name;	//友好显示名称

    private int province;	//用户所在省级ID

    private int city;	//用户所在城市ID

    private String location;//用户所在地

    private String description;//用户个人描述

    private String url;	//用户博客地址

    private String profile_image_url;//用户头像地址（中图），50×50像素

    private String profile_url;	//用户的微博统一URL地址

    private String domain;	//用户的个性化域名

    private String weihao;	//用户的微号

    private String gender;	//性别，m：男、f：女、n：未知

    private int followers_count;	//粉丝数

    private int friends_count;	//关注数

    private int statuses_count;	//微博数

    private int favourites_count;	//收藏数

    private String created_at;	//用户创建（注册）时间

    private String avatar_large;//用户头像地址（大图），180×180像素

    private String avatar_hd;//用户头像地址（高清），高清头像原图

    private String lang;//用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语

    public long getId() {
        return id;
    }

    public String getIdstr() {
        return idstr;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }

    public int getProvince() {
        return province;
    }

    public int getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public String getDomain() {
        return domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public String getGender() {
        return gender;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public String getLang() {
        return lang;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "用户ID:"+getId()+
                "用户昵称:"+getScreen_name()+
                "所在城市："+getCity();
    }
}
