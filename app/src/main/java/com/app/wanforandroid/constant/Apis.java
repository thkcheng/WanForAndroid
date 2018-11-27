package com.app.wanforandroid.constant;

/**
 * Created by thkcheng on 2018/11/23.
 */
public class Apis {

    public final static String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 首页文章列表
     * GET
     */
    public static String WAN_HOME_BANNER = BASE_URL + "banner/json";

    /**
     * 首页文章列表(页码从0开始)
     * GET
     */
    public static String WAN_HOME_LIST = BASE_URL + "article/list/%d/json";

    /**
     * 项目分类
     * GET
     */
    public static String WAN_PROJECT_TITLE_LIST = BASE_URL + "project/tree/json";

    /**
     * 项项目列表数据
     * GET
     */
    public static String WAN_PROJECT_DATA_LIST = BASE_URL + "project/list/%d/json";

    /**
     * 公众号分类
     * GET
     */
    public static String WAN_WECHAT_TITLE_LIST = BASE_URL + "wxarticle/chapters/json";

    /**
     * 公众号列表数据
     * GET
     */
    public static String WAN_WECHAT_DATA_LIST = BASE_URL + "wxarticle/list/%d/%d/json";

    /**
     * 收藏列表
     * GET
     */
    public static String WAN_COLLECT_LIST = BASE_URL + "lg/collect/list/%d/json";

    /**
     * 收藏列表
     * POST
     */
    public static String WAN_USER_LOGIN = BASE_URL + "user/login";
}
