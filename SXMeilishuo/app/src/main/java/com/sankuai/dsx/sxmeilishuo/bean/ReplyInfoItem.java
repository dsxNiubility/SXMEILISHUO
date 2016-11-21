package com.sankuai.dsx.sxmeilishuo.bean;

/**
 * Created by dsx on 2016/11/21.
 * 回复模型
 */

public class ReplyInfoItem {
    private UserInfoItem uInfo;
    private int is_me;
    private String service_type;
    private String reply_id;
    private String reply_content;
    private String create_time;
    private String post_id;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public UserInfoItem getuInfo() {
        return uInfo;
    }

    public void setuInfo(UserInfoItem uInfo) {
        this.uInfo = uInfo;
    }

    public int getIs_me() {
        return is_me;
    }

    public void setIs_me(int is_me) {
        this.is_me = is_me;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
