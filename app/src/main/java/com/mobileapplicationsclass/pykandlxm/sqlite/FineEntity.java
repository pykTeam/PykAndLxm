package com.mobileapplicationsclass.pykandlxm.sqlite;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class FineEntity {
    private Long id;
    private String wechat_id;
    private String source;
    private String title;
    private String icon;
    private String url;

    public FineEntity() {
    }

    public FineEntity(Long id, String wechat_id, String source, String title, String icon, String url) {
        this.id = id;
        this.wechat_id = wechat_id;
        this.source = source;
        this.title = title;
        this.icon = icon;
        this.url = url;
    }

    public String getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(String wechat_id) {
        this.wechat_id = wechat_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
