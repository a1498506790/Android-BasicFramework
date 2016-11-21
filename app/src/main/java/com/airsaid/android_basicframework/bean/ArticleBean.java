package com.airsaid.android_basicframework.bean;

/**
 * Created by zhouyou on 2016/11/21.
 * Class desc:
 * 测试：文章Bean
 */
public class ArticleBean {

    /**
     * aid : 文章ID
     * title : 文章标题
     * poster : 封面
     * ctime : 发布时间
     */

    private String aid;
    private String title;
    private String poster;
    private String ctime;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "aid='" + aid + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", ctime='" + ctime + '\'' +
                '}';
    }
}
