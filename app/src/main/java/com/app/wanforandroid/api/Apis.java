package com.app.wanforandroid.api;

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
}
