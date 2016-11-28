package com.sankuai.dsx.sxmeilishuo.bean;

/**
 * Created by dsx on 2016/11/28.
 */

public class ShoppingInfoItem {

    private int total;
    private int marketId;
    private int sort;
    private long stime;
    private String catalog;
    private ShoppingImageInfo image;
    private String type;
    private String url;
    private String pcUrl;
    private int banner_status;
    private String title;
    private String time;
    private int orderNo;
    private ShoppingUserInfo uinfo;
    private int topic_type;
    private String description;
    private String share_pic;
    private String subtitle;
    private int topic_id;

    // 还有个数组


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public long getStime() {
        return stime;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public ShoppingImageInfo getImage() {
        return image;
    }

    public void setImage(ShoppingImageInfo image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPcUrl() {
        return pcUrl;
    }

    public void setPcUrl(String pcUrl) {
        this.pcUrl = pcUrl;
    }

    public int getBanner_status() {
        return banner_status;
    }

    public void setBanner_status(int banner_status) {
        this.banner_status = banner_status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public ShoppingUserInfo getUinfo() {
        return uinfo;
    }

    public void setUinfo(ShoppingUserInfo uinfo) {
        this.uinfo = uinfo;
    }

    public int getTopic_type() {
        return topic_type;
    }

    public void setTopic_type(int topic_type) {
        this.topic_type = topic_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare_pic() {
        return share_pic;
    }

    public void setShare_pic(String share_pic) {
        this.share_pic = share_pic;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public static class ShoppingUserInfo{
        private int is_vip;
        private String nickname;
        private long user_id;
        private String avatar;

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class ShoppingImageInfo{
        private int height;
        private int width;
        private String pic_url;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }

}
