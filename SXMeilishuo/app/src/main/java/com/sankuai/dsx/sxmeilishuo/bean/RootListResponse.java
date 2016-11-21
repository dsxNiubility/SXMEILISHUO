package com.sankuai.dsx.sxmeilishuo.bean;

import java.util.List;

/**
 * Created by dsx on 2016/11/21.
 */

public class RootListResponse {
    private String message;
    private RootListData data;
    private int error_code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RootListData getData() {
        return data;
    }

    public void setData(RootListData data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class RootListData{
        List<RootListItem> list;

        public List<RootListItem> getList() {
            return list;
        }

        public void setList(List<RootListItem> list) {
            this.list = list;
        }

        public static class RootListItem{
            private String time_text;
            private UserInfoItem uinfo;
            private String acm;
            private String post_reply_more;
            private int is_recommend;
            private List<ReplyInfoItem> post_replies;
            private String type;
            private BigPointInfoItem pinfo;
            private String collect_time;

            public String getTime_text() {
                return time_text;
            }

            public void setTime_text(String time_text) {
                this.time_text = time_text;
            }

            public UserInfoItem getUinfo() {
                return uinfo;
            }

            public void setUinfo(UserInfoItem uinfo) {
                this.uinfo = uinfo;
            }

            public String getAcm() {
                return acm;
            }

            public void setAcm(String acm) {
                this.acm = acm;
            }

            public String getPost_reply_more() {
                return post_reply_more;
            }

            public void setPost_reply_more(String post_reply_more) {
                this.post_reply_more = post_reply_more;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public List<ReplyInfoItem> getPost_replies() {
                return post_replies;
            }

            public void setPost_replies(List<ReplyInfoItem> post_replies) {
                this.post_replies = post_replies;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public BigPointInfoItem getPinfo() {
                return pinfo;
            }

            public void setPinfo(BigPointInfoItem pinfo) {
                this.pinfo = pinfo;
            }

            public String getCollect_time() {
                return collect_time;
            }

            public void setCollect_time(String collect_time) {
                this.collect_time = collect_time;
            }
        }
    }
}
