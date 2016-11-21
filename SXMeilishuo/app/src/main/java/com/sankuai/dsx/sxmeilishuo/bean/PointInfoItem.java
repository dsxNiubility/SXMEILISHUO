package com.sankuai.dsx.sxmeilishuo.bean;

import java.util.List;

/**
 * Created by dsx on 2016/11/21.
 */

public class PointInfoItem {
    private List<PointTagsInfoItem> tags;
    private int pic_height;
    private int pic_width;
    private String pic_url_o;
    private String pic_url;

    public List<PointTagsInfoItem> getTags() {
        return tags;
    }

    public void setTags(List<PointTagsInfoItem> tags) {
        this.tags = tags;
    }

    public int getPic_height() {
        return pic_height;
    }

    public void setPic_height(int pic_height) {
        this.pic_height = pic_height;
    }

    public int getPic_width() {
        return pic_width;
    }

    public void setPic_width(int pic_width) {
        this.pic_width = pic_width;
    }

    public String getPic_url_o() {
        return pic_url_o;
    }

    public void setPic_url_o(String pic_url_o) {
        this.pic_url_o = pic_url_o;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public static class PointTagsInfoItem{

        private String direction;
        private List<PointTagsLabelItem> label;
        private GoodsInfoItem goods_info;
        private GoodsLessInfoItem ginfo;
        private double y;
        private double x;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public List<PointTagsLabelItem> getLabel() {
            return label;
        }

        public void setLabel(List<PointTagsLabelItem> label) {
            this.label = label;
        }

        public GoodsInfoItem getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoItem goods_info) {
            this.goods_info = goods_info;
        }

        public GoodsLessInfoItem getGinfo() {
            return ginfo;
        }

        public void setGinfo(GoodsLessInfoItem ginfo) {
            this.ginfo = ginfo;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public static class PointTagsLabelItem{
            private String jump_url;
            private String mogu_jump_url;
            private String type;
            private String tag_name;
            private String tag_id;

            public String getJump_url() {
                return jump_url;
            }

            public void setJump_url(String jump_url) {
                this.jump_url = jump_url;
            }

            public String getMogu_jump_url() {
                return mogu_jump_url;
            }

            public void setMogu_jump_url(String mogu_jump_url) {
                this.mogu_jump_url = mogu_jump_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getTag_id() {
                return tag_id;
            }

            public void setTag_id(String tag_id) {
                this.tag_id = tag_id;
            }
        }

    }
}
