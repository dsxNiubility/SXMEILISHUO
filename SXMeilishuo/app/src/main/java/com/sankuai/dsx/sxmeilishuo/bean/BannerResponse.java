package com.sankuai.dsx.sxmeilishuo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dsx on 2016/11/8.
 */

public class BannerResponse {
    private BannerData data;
    private String returnCode;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BannerData getData() {
        return data;
    }

    public void setData(BannerData data) {
        this.data = data;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    // 第一层嵌套
    public static class BannerData{

        @SerializedName("5956")
        private BannerSubData subData;

        public BannerSubData getSubData() {
            return subData;
        }

        public void setSubData(BannerSubData subData) {
            this.subData = subData;
        }

        // 第二层嵌套
        public static class BannerSubData{

            private boolean isEnd;
            private List<BannerItem> list;

            public boolean isEnd() {
                return isEnd;
            }

            public void setEnd(boolean end) {
                isEnd = end;
            }

            public List<BannerItem> getList() {
                return list;
            }

            public void setList(List<BannerItem> list) {
                this.list = list;
            }

            // 第三层嵌套
            public static class BannerItem {

                private String title;
                private int sort;
                private String acm;
                private String link;
                private String image;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getAcm() {
                    return acm;
                }

                public void setAcm(String acm) {
                    this.acm = acm;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }
            }
        }
    }
}
