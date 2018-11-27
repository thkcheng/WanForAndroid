package com.app.wanforandroid.model;

import java.util.List;

/**
 * Created by thkcheng on 2018/11/27.
 */

public class CollectBean extends BaseModel{

    /**
     * data : {"curPage":1,"datas":[{"author":"鸿洋","chapterId":408,"chapterName":"鸿洋","courseId":13,"desc":"","envelopePic":"","id":32758,"link":"https://mp.weixin.qq.com/s/1ua0geFnrbQbyHi8KG2VJQ","niceDate":"2018-11-22","origin":"","originId":7545,"publishTime":1542857622000,"title":"换肤、全局字体替换、无需编写shape、selector 的原理Factory小结","userId":7386,"visible":0,"zan":0},{"author":"鸿洋","chapterId":408,"chapterName":"鸿洋","courseId":13,"desc":"","envelopePic":"","id":30789,"link":"https://mp.weixin.qq.com/s/vVJ3IDuGumo9h4XCNgrbAA","niceDate":"2018-11-05","origin":"","originId":7457,"publishTime":1541419859000,"title":"对 Android 未来的发展十分重要的技术 | App Bundles","userId":7386,"visible":0,"zan":0},{"author":"郭霖","chapterId":409,"chapterName":"郭霖","courseId":13,"desc":"","envelopePic":"","id":30788,"link":"https://mp.weixin.qq.com/s/cvcu6kbXzR5x5tpLYYEMKw","niceDate":"2018-11-05","origin":"","originId":7455,"publishTime":1541419849000,"title":"总结几种判断RecyclerView到达底部的方法","userId":7386,"visible":0,"zan":0},{"author":"小编","chapterId":360,"chapterName":"小编发布","courseId":13,"desc":"","envelopePic":"","id":30787,"link":"http://www.wanandroid.com/blog/show/2042","niceDate":"2018-11-05","origin":"","originId":7459,"publishTime":1541419840000,"title":"玩 Android 迭代管理: 移除了注册、登录 json 接口返回的密码信息","userId":7386,"visible":0,"zan":0},{"author":" JerryloveEmily","chapterId":260,"chapterName":"RxJava & Retrofit & MVP","courseId":13,"desc":"","envelopePic":"","id":19733,"link":"https://juejin.im/post/5b5927eae51d4518e311a93f","niceDate":"2018-08-02","origin":"","originId":3186,"publishTime":1533189652000,"title":"RxJava 2.x入门新姿势一","userId":7386,"visible":0,"zan":0},{"author":"蒋志碧","chapterId":395,"chapterName":"事件总线","courseId":13,"desc":"","envelopePic":"","id":19732,"link":"https://www.jianshu.com/p/a3cdf5add8bd","niceDate":"2018-08-02","origin":"","originId":3160,"publishTime":1533189433000,"title":"EventBus 3.0+ 源码详解（史上最详细图文讲解）","userId":7386,"visible":0,"zan":0},{"author":"像一只狗","chapterId":321,"chapterName":"算法","courseId":13,"desc":"","envelopePic":"","id":19731,"link":"https://juejin.im/post/5b5536825188251af6622815","niceDate":"2018-08-02","origin":"","originId":3176,"publishTime":1533189354000,"title":"了解面试算法之 - 栈&amp;队列&amp;位运算","userId":7386,"visible":0,"zan":0},{"author":"鸿洋公众号","chapterId":73,"chapterName":"面试相关","courseId":13,"desc":"","envelopePic":"","id":19730,"link":"https://mp.weixin.qq.com/s/haZRurfMHQzzr-ffxAh20w","niceDate":"2018-08-02","origin":"","originId":3177,"publishTime":1533188929000,"title":"我的杭州面试之旅","userId":7386,"visible":0,"zan":0},{"author":"Mobcase","chapterId":358,"chapterName":"项目基础功能","courseId":13,"desc":" 轻量级的Android图片压缩插件，采用Hook Gradle Task的方式，压缩module，jar，aar中所有的png图片并有策略的转换成webp（已考虑各种情况），全自动压缩无需认为干涉，简单集成，所有开关可配置。已应用在大型商业App中","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","id":19729,"link":"http://www.wanandroid.com/blog/show/2247","niceDate":"2018-08-02","origin":"","originId":3179,"publishTime":1533188869000,"title":"McImage Android端自动化图片压缩插件","userId":7386,"visible":0,"zan":0},{"author":"Jdqm","chapterId":61,"chapterName":"Android测试相关","courseId":13,"desc":"","envelopePic":"","id":19728,"link":"https://www.jianshu.com/p/aa51a3e007e2","niceDate":"2018-08-02","origin":"","originId":3180,"publishTime":1533188840000,"title":"Android单元测试只看这一篇就够了","userId":7386,"visible":0,"zan":0},{"author":"guanpj","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"API 来自官网 https://readhub.me，采用 MVP + Dagger 架构进行开发，网络请求使用 Retrofit + RxJava 2 框架，数据库存储使用 Room + RxJava 2 框架。项目模块不多，但是麻雀虽小五脏俱全。适合 Dagger 、RxJava、Room 等框架的入门学习，没什么意外的话会一直维护。","envelopePic":"http://www.wanandroid.com/blogimgs/251b4ff8-5f8c-4435-aa7f-baf404ffa273.png","id":19726,"link":"http://www.wanandroid.com/blog/show/2249","niceDate":"2018-08-02","origin":"","originId":3188,"publishTime":1533188716000,"title":"目前功能最完整的 Readhub 客户端","userId":7386,"visible":0,"zan":0},{"author":"CodingEnding","chapterId":387,"chapterName":"对话框","courseId":13,"desc":"PopupLayout是通用弹出布局辅助库，允许开发者从顶部、底部、左侧、右侧和中心这五个位置弹出自己指定的View，此外还提供圆角和动画特性。","envelopePic":"http://www.wanandroid.com/blogimgs/4838a40c-f56c-4a0e-bcb9-3c1aecb45c21.png","id":19725,"link":"http://www.wanandroid.com/blog/show/2252","niceDate":"2018-08-02","origin":"","originId":3195,"publishTime":1533188711000,"title":"PopupLayout 通用弹出布局辅助库","userId":7386,"visible":0,"zan":0},{"author":"lizixian18","chapterId":385,"chapterName":"架构","courseId":13,"desc":"一个简单强大且灵活的MVP框架","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","id":19724,"link":"http://www.wanandroid.com/blog/show/2253","niceDate":"2018-08-02","origin":"","originId":3196,"publishTime":1533188692000,"title":"EasyMvp 一个简单强大且灵活的MVP框架","userId":7386,"visible":0,"zan":0},{"author":"mouxuefei","chapterId":338,"chapterName":"日历&amp;时钟","courseId":13,"desc":"今天玩小米mix2的时候看到了小米的时间控件效果真的很棒。有各种动画效果，3d触摸效果，然后就想着自己能不能也实现一个这样的时间控件，那就开始行动绘制一个简易版本的小米时间控件吧o((≧▽≦o)","envelopePic":"http://www.wanandroid.com/blogimgs/d61e55e4-cd39-4940-a1ad-befc4aeb9f78.png","id":19723,"link":"http://www.wanandroid.com/blog/show/2256","niceDate":"2018-08-02","origin":"","originId":3202,"publishTime":1533188671000,"title":"自定义view之kotlin绘制精简小米时间控件 MIClockView","userId":7386,"visible":0,"zan":0},{"author":"光源_Android","chapterId":295,"chapterName":"混淆","courseId":13,"desc":"","envelopePic":"","id":19722,"link":"https://www.jianshu.com/p/158aa484da13","niceDate":"2018-08-02","origin":"","originId":3199,"publishTime":1533188585000,"title":"写给Android开发者的混淆使用手册","userId":7386,"visible":0,"zan":0},{"author":"腾讯Bugly","chapterId":295,"chapterName":"混淆","courseId":13,"desc":"","envelopePic":"","id":19721,"link":"https://mp.weixin.qq.com/s/WmJyiA3fDNriw5qXuoA9MA","niceDate":"2018-08-02","origin":"","originId":3198,"publishTime":1533188582000,"title":"Android 混淆那些事儿","userId":7386,"visible":0,"zan":0},{"author":"openXu","chapterId":339,"chapterName":"K线图","courseId":13,"desc":"自定义图标库，使用方便，扩展性强，后续会添加更多开发中使用到的图表\r\n\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/2870d58d-c9a4-47a5-8123-ef869b6bd6d7.png","id":19720,"link":"http://www.wanandroid.com/blog/show/2259","niceDate":"2018-08-02","origin":"","originId":3207,"publishTime":1533188566000,"title":"各种自定义图表库，使用简单，支持扩展 OXChart","userId":7386,"visible":0,"zan":0},{"author":"LillteZheng","chapterId":400,"chapterName":"ViewPager","courseId":13,"desc":"这个一个 viewpager 工具类，能够帮你快速实现导航栏轮播图，app引导页，viewpager + fragment；内置多种tab指示器，让你告别 viewpager 的繁琐操作，专注逻辑功能","envelopePic":"http://www.wanandroid.com/blogimgs/ea5cac16-f9b7-4b8d-8812-d215a00f2853.png","id":19719,"link":"http://www.wanandroid.com/blog/show/2258","niceDate":"2018-08-02","origin":"","originId":3204,"publishTime":1533188563000,"title":"ViewPager 工具类，快速实现轮播图，app引导页，tab标签  ViewPagerHelper","userId":7386,"visible":0,"zan":0},{"author":"sheajin","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"WanAndroid 客户端 java版。项目基于Material Design + MVP + RxJava2 + Retrofit + Glide 框架。采用Material Design设置UI。","envelopePic":"http://www.wanandroid.com/blogimgs/fe08759d-2244-4254-9123-b8192e5c10f7.png","id":16482,"link":"http://www.wanandroid.com/blog/show/2217","niceDate":"2018-07-12","origin":"","originId":3133,"publishTime":1531388155000,"title":"WanAndroid Java客户端","userId":7386,"visible":0,"zan":0},{"author":"zfman","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"hpu小课:一款精致、简洁的课表软件.\r\n\r\n它是为河南理工大学学生设计的一个蹭课软件,但你可以通过本项目的学习进而完成自己的课程表软件，已上架酷安.","envelopePic":"http://www.wanandroid.com/blogimgs/acd85350-9b4f-426a-a14c-bbc0cb0967a0.png","id":16479,"link":"http://www.wanandroid.com/blog/show/2219","niceDate":"2018-07-12","origin":"","originId":3135,"publishTime":1531388064000,"title":" 一款精致、简洁的课表软件 hputimetable","userId":7386,"visible":0,"zan":0}],"offset":0,"over":false,"pageCount":2,"size":20,"total":33}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"author":"鸿洋","chapterId":408,"chapterName":"鸿洋","courseId":13,"desc":"","envelopePic":"","id":32758,"link":"https://mp.weixin.qq.com/s/1ua0geFnrbQbyHi8KG2VJQ","niceDate":"2018-11-22","origin":"","originId":7545,"publishTime":1542857622000,"title":"换肤、全局字体替换、无需编写shape、selector 的原理Factory小结","userId":7386,"visible":0,"zan":0},{"author":"鸿洋","chapterId":408,"chapterName":"鸿洋","courseId":13,"desc":"","envelopePic":"","id":30789,"link":"https://mp.weixin.qq.com/s/vVJ3IDuGumo9h4XCNgrbAA","niceDate":"2018-11-05","origin":"","originId":7457,"publishTime":1541419859000,"title":"对 Android 未来的发展十分重要的技术 | App Bundles","userId":7386,"visible":0,"zan":0},{"author":"郭霖","chapterId":409,"chapterName":"郭霖","courseId":13,"desc":"","envelopePic":"","id":30788,"link":"https://mp.weixin.qq.com/s/cvcu6kbXzR5x5tpLYYEMKw","niceDate":"2018-11-05","origin":"","originId":7455,"publishTime":1541419849000,"title":"总结几种判断RecyclerView到达底部的方法","userId":7386,"visible":0,"zan":0},{"author":"小编","chapterId":360,"chapterName":"小编发布","courseId":13,"desc":"","envelopePic":"","id":30787,"link":"http://www.wanandroid.com/blog/show/2042","niceDate":"2018-11-05","origin":"","originId":7459,"publishTime":1541419840000,"title":"玩 Android 迭代管理: 移除了注册、登录 json 接口返回的密码信息","userId":7386,"visible":0,"zan":0},{"author":" JerryloveEmily","chapterId":260,"chapterName":"RxJava & Retrofit & MVP","courseId":13,"desc":"","envelopePic":"","id":19733,"link":"https://juejin.im/post/5b5927eae51d4518e311a93f","niceDate":"2018-08-02","origin":"","originId":3186,"publishTime":1533189652000,"title":"RxJava 2.x入门新姿势一","userId":7386,"visible":0,"zan":0},{"author":"蒋志碧","chapterId":395,"chapterName":"事件总线","courseId":13,"desc":"","envelopePic":"","id":19732,"link":"https://www.jianshu.com/p/a3cdf5add8bd","niceDate":"2018-08-02","origin":"","originId":3160,"publishTime":1533189433000,"title":"EventBus 3.0+ 源码详解（史上最详细图文讲解）","userId":7386,"visible":0,"zan":0},{"author":"像一只狗","chapterId":321,"chapterName":"算法","courseId":13,"desc":"","envelopePic":"","id":19731,"link":"https://juejin.im/post/5b5536825188251af6622815","niceDate":"2018-08-02","origin":"","originId":3176,"publishTime":1533189354000,"title":"了解面试算法之 - 栈&amp;队列&amp;位运算","userId":7386,"visible":0,"zan":0},{"author":"鸿洋公众号","chapterId":73,"chapterName":"面试相关","courseId":13,"desc":"","envelopePic":"","id":19730,"link":"https://mp.weixin.qq.com/s/haZRurfMHQzzr-ffxAh20w","niceDate":"2018-08-02","origin":"","originId":3177,"publishTime":1533188929000,"title":"我的杭州面试之旅","userId":7386,"visible":0,"zan":0},{"author":"Mobcase","chapterId":358,"chapterName":"项目基础功能","courseId":13,"desc":" 轻量级的Android图片压缩插件，采用Hook Gradle Task的方式，压缩module，jar，aar中所有的png图片并有策略的转换成webp（已考虑各种情况），全自动压缩无需认为干涉，简单集成，所有开关可配置。已应用在大型商业App中","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","id":19729,"link":"http://www.wanandroid.com/blog/show/2247","niceDate":"2018-08-02","origin":"","originId":3179,"publishTime":1533188869000,"title":"McImage Android端自动化图片压缩插件","userId":7386,"visible":0,"zan":0},{"author":"Jdqm","chapterId":61,"chapterName":"Android测试相关","courseId":13,"desc":"","envelopePic":"","id":19728,"link":"https://www.jianshu.com/p/aa51a3e007e2","niceDate":"2018-08-02","origin":"","originId":3180,"publishTime":1533188840000,"title":"Android单元测试只看这一篇就够了","userId":7386,"visible":0,"zan":0},{"author":"guanpj","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"API 来自官网 https://readhub.me，采用 MVP + Dagger 架构进行开发，网络请求使用 Retrofit + RxJava 2 框架，数据库存储使用 Room + RxJava 2 框架。项目模块不多，但是麻雀虽小五脏俱全。适合 Dagger 、RxJava、Room 等框架的入门学习，没什么意外的话会一直维护。","envelopePic":"http://www.wanandroid.com/blogimgs/251b4ff8-5f8c-4435-aa7f-baf404ffa273.png","id":19726,"link":"http://www.wanandroid.com/blog/show/2249","niceDate":"2018-08-02","origin":"","originId":3188,"publishTime":1533188716000,"title":"目前功能最完整的 Readhub 客户端","userId":7386,"visible":0,"zan":0},{"author":"CodingEnding","chapterId":387,"chapterName":"对话框","courseId":13,"desc":"PopupLayout是通用弹出布局辅助库，允许开发者从顶部、底部、左侧、右侧和中心这五个位置弹出自己指定的View，此外还提供圆角和动画特性。","envelopePic":"http://www.wanandroid.com/blogimgs/4838a40c-f56c-4a0e-bcb9-3c1aecb45c21.png","id":19725,"link":"http://www.wanandroid.com/blog/show/2252","niceDate":"2018-08-02","origin":"","originId":3195,"publishTime":1533188711000,"title":"PopupLayout 通用弹出布局辅助库","userId":7386,"visible":0,"zan":0},{"author":"lizixian18","chapterId":385,"chapterName":"架构","courseId":13,"desc":"一个简单强大且灵活的MVP框架","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","id":19724,"link":"http://www.wanandroid.com/blog/show/2253","niceDate":"2018-08-02","origin":"","originId":3196,"publishTime":1533188692000,"title":"EasyMvp 一个简单强大且灵活的MVP框架","userId":7386,"visible":0,"zan":0},{"author":"mouxuefei","chapterId":338,"chapterName":"日历&amp;时钟","courseId":13,"desc":"今天玩小米mix2的时候看到了小米的时间控件效果真的很棒。有各种动画效果，3d触摸效果，然后就想着自己能不能也实现一个这样的时间控件，那就开始行动绘制一个简易版本的小米时间控件吧o((≧▽≦o)","envelopePic":"http://www.wanandroid.com/blogimgs/d61e55e4-cd39-4940-a1ad-befc4aeb9f78.png","id":19723,"link":"http://www.wanandroid.com/blog/show/2256","niceDate":"2018-08-02","origin":"","originId":3202,"publishTime":1533188671000,"title":"自定义view之kotlin绘制精简小米时间控件 MIClockView","userId":7386,"visible":0,"zan":0},{"author":"光源_Android","chapterId":295,"chapterName":"混淆","courseId":13,"desc":"","envelopePic":"","id":19722,"link":"https://www.jianshu.com/p/158aa484da13","niceDate":"2018-08-02","origin":"","originId":3199,"publishTime":1533188585000,"title":"写给Android开发者的混淆使用手册","userId":7386,"visible":0,"zan":0},{"author":"腾讯Bugly","chapterId":295,"chapterName":"混淆","courseId":13,"desc":"","envelopePic":"","id":19721,"link":"https://mp.weixin.qq.com/s/WmJyiA3fDNriw5qXuoA9MA","niceDate":"2018-08-02","origin":"","originId":3198,"publishTime":1533188582000,"title":"Android 混淆那些事儿","userId":7386,"visible":0,"zan":0},{"author":"openXu","chapterId":339,"chapterName":"K线图","courseId":13,"desc":"自定义图标库，使用方便，扩展性强，后续会添加更多开发中使用到的图表\r\n\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/2870d58d-c9a4-47a5-8123-ef869b6bd6d7.png","id":19720,"link":"http://www.wanandroid.com/blog/show/2259","niceDate":"2018-08-02","origin":"","originId":3207,"publishTime":1533188566000,"title":"各种自定义图表库，使用简单，支持扩展 OXChart","userId":7386,"visible":0,"zan":0},{"author":"LillteZheng","chapterId":400,"chapterName":"ViewPager","courseId":13,"desc":"这个一个 viewpager 工具类，能够帮你快速实现导航栏轮播图，app引导页，viewpager + fragment；内置多种tab指示器，让你告别 viewpager 的繁琐操作，专注逻辑功能","envelopePic":"http://www.wanandroid.com/blogimgs/ea5cac16-f9b7-4b8d-8812-d215a00f2853.png","id":19719,"link":"http://www.wanandroid.com/blog/show/2258","niceDate":"2018-08-02","origin":"","originId":3204,"publishTime":1533188563000,"title":"ViewPager 工具类，快速实现轮播图，app引导页，tab标签  ViewPagerHelper","userId":7386,"visible":0,"zan":0},{"author":"sheajin","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"WanAndroid 客户端 java版。项目基于Material Design + MVP + RxJava2 + Retrofit + Glide 框架。采用Material Design设置UI。","envelopePic":"http://www.wanandroid.com/blogimgs/fe08759d-2244-4254-9123-b8192e5c10f7.png","id":16482,"link":"http://www.wanandroid.com/blog/show/2217","niceDate":"2018-07-12","origin":"","originId":3133,"publishTime":1531388155000,"title":"WanAndroid Java客户端","userId":7386,"visible":0,"zan":0},{"author":"zfman","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"hpu小课:一款精致、简洁的课表软件.\r\n\r\n它是为河南理工大学学生设计的一个蹭课软件,但你可以通过本项目的学习进而完成自己的课程表软件，已上架酷安.","envelopePic":"http://www.wanandroid.com/blogimgs/acd85350-9b4f-426a-a14c-bbc0cb0967a0.png","id":16479,"link":"http://www.wanandroid.com/blog/show/2219","niceDate":"2018-07-12","origin":"","originId":3135,"publishTime":1531388064000,"title":" 一款精致、简洁的课表软件 hputimetable","userId":7386,"visible":0,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 2
         * size : 20
         * total : 33
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * author : 鸿洋
             * chapterId : 408
             * chapterName : 鸿洋
             * courseId : 13
             * desc :
             * envelopePic :
             * id : 32758
             * link : https://mp.weixin.qq.com/s/1ua0geFnrbQbyHi8KG2VJQ
             * niceDate : 2018-11-22
             * origin :
             * originId : 7545
             * publishTime : 1542857622000
             * title : 换肤、全局字体替换、无需编写shape、selector 的原理Factory小结
             * userId : 7386
             * visible : 0
             * zan : 0
             */

            private String author;
            private int chapterId;
            private String chapterName;
            private int courseId;
            private String desc;
            private String envelopePic;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private int originId;
            private long publishTime;
            private String title;
            private int userId;
            private int visible;
            private int zan;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }
        }
    }
}
