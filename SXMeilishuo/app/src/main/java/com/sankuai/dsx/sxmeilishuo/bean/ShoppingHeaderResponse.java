package com.sankuai.dsx.sxmeilishuo.bean;

import java.util.List;

/**
 * Created by dsx on 2016/11/28.
 */

public class ShoppingHeaderResponse {
    private String api;
    private String v;
    private String ret;
    private ShoppingHeaderData data;

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

    public ShoppingHeaderData getData() {
        return data;
    }

    public void setData(ShoppingHeaderData data) {
        this.data = data;
    }

    public static class ShoppingHeaderData{

        private ShoppingDataSub category;
        private ShoppingDataSub activity;
        private ShoppingDataSub marketing;
        private ShoppingDataSub banner;

        public ShoppingDataSub getCategory() {
            return category;
        }

        public void setCategory(ShoppingDataSub category) {
            this.category = category;
        }

        public ShoppingDataSub getActivity() {
            return activity;
        }

        public void setActivity(ShoppingDataSub activity) {
            this.activity = activity;
        }

        public ShoppingDataSub getMarketing() {
            return marketing;
        }

        public void setMarketing(ShoppingDataSub marketing) {
            this.marketing = marketing;
        }

        public ShoppingDataSub getBanner() {
            return banner;
        }

        public void setBanner(ShoppingDataSub banner) {
            this.banner = banner;
        }

        public static class ShoppingDataSub{
            private List<BannerItem> list;
            private boolean isEnd;
            private int nextPage;

            public List<BannerItem> getList() {
                return list;
            }

            public void setList(List<BannerItem> list) {
                this.list = list;
            }

            public boolean isEnd() {
                return isEnd;
            }

            public void setEnd(boolean end) {
                isEnd = end;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }
        }
    }
}
