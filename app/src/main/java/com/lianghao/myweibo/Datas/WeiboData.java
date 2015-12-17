package com.lianghao.myweibo.Datas;

/**
 * Created by Administrator on 2015/12/16.
 */
public class WeiboData {

    private static final String APP_KEY = "2672051258";
    public static final String APP_SECRET = "bc6c91be7de708c8a8425b3b3202f4a4";

//    public static final String APP_KEY = "3195288873";
//    public static final String APP_SECRET = "f0bbaad564233b470d8f2850c08069cf";

    /**
     * 授权回调页
     */
    public static final String AUTH_REDIRECT_URI = "https://api.weibo.com/oauth2/default.html";

    /**
     * 取消授权回调页
     */
    public static final String UNAUTH_REDIRECT_URI = "https://api.weibo.com/oauth2/default.html";


    /**
     * 获取授权页面uri
     */
    public static String getOAuthUri() {
        return
                new StringBuilder("https://api.weibo.com/oauth2/authorize")
                        .append("?client_id=").append(APP_KEY)
                        .append("&response_type=code")
                        .append("&redirect_uri=").append(AUTH_REDIRECT_URI)
                        //.append("&forcelogin=").append("true")
                        .toString();
    }

    /**
     * 获取access token
     * @param code 用户认证成功后新浪返回的校检码
     */
    public static String getAccessTokenUri(String code) {
        return
                new StringBuilder("https://api.weibo.com/oauth2/access_token")
                        .append("?client_id=").append(APP_KEY)
                        .append("&client_secret=").append(APP_SECRET)
                        .append("&grant_type=authorization_code")
                        .append("&redirect_uri=").append(AUTH_REDIRECT_URI)
                        .append("&code=").append(code)
                        .toString();
    }
}
