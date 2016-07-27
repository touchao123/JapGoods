package com.touchenjoy.japgoods.model.entities;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/7/24.
 */
public class Trademark extends BmobObject {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String name;
    private String short_name;
    private String logo;
    private String url;
    private String category;

    public BmobFile getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(BmobFile logo_img) {
        this.logo_img = logo_img;
    }

    private BmobFile logo_img;

}
