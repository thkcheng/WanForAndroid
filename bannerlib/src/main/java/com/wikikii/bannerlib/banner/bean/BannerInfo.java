package com.wikikii.bannerlib.banner.bean;

/**
 * BannerInfo
 *
 * @author Edwin.Wu
 * @version 2016/12/6 17:32
 * @since JDK1.8
 */
public class BannerInfo<T> {
    public T data;
    public String title;
    public String link;

    public BannerInfo(T data, String title, String link) {
        this.data = data;
        this.title = title;
        this.link = link;
    }
}
