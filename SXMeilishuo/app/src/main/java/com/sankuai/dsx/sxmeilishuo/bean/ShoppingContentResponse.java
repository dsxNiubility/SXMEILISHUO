package com.sankuai.dsx.sxmeilishuo.bean;

import java.util.List;

/**
 * Created by dsx on 2016/11/28.
 */

public class ShoppingContentResponse {
    private String api;
    private String v;
    private String ret;
    private ShoppingData data;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public ShoppingData getData() {
        return data;
    }

    public void setData(ShoppingData data) {
        this.data = data;
    }

    public static class ShoppingData{
        List<ShoppingInfoItem> list;

        public List<ShoppingInfoItem> getList() {
            return list;
        }

        public void setList(List<ShoppingInfoItem> list) {
            this.list = list;
        }
    }
}
