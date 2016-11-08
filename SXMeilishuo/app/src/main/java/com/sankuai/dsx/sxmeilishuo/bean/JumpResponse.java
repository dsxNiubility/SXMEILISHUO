package com.sankuai.dsx.sxmeilishuo.bean;

import java.util.List;

/**
 * Created by dsx on 2016/11/8.
 */

public class JumpResponse {

    private List<JumpItem> data;

    public List<JumpItem> getData() {
        return data;
    }

    public void setData(List<JumpItem> data) {
        this.data = data;
    }

    public static class JumpItem {
        private String pic_height;
        private String pic_width;
        private String jump_url;
        private String mogu_jump_url;
        private String tag_name;
        private String pic_url;
        private int tag_id;

        public String getPic_height() {
            return pic_height;
        }

        public void setPic_height(String pic_height) {
            this.pic_height = pic_height;
        }

        public String getPic_width() {
            return pic_width;
        }

        public void setPic_width(String pic_width) {
            this.pic_width = pic_width;
        }

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

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }
    }
}
