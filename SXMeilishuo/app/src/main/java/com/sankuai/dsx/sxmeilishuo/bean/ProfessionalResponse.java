package com.sankuai.dsx.sxmeilishuo.bean;

import java.util.List;

/**
 * Created by dsx on 2016/11/8.
 */

public class ProfessionalResponse {

    private ProfessionalData data;

    public ProfessionalData getData() {
        return data;
    }

    public void setData(ProfessionalData data) {
        this.data = data;
    }

    public static class ProfessionalData{

        private List<ProfessionalItem> list;

        public List<ProfessionalItem> getList() {
            return list;
        }

        public void setList(List<ProfessionalItem> list) {
            this.list = list;
        }

        public static class ProfessionalItem{

            private String avatar_e;
            private String avatar_d;
            private String birthday;
            private String avatar_c;
            private String avatar_b;
            private String is_daren;
            private String avatar_a;
            private String nickname;
            private String avatar_o;
            private String data_desc;
            private int followed;
            private int post_num;
            private String identity_desc;
            private String about_me;
            private int is_me;
            private int following_num;
            private String identity_img;
            private int follow_status;
            private int get_praise_num;
            private long user_id;

            public String getAvatar_e() {
                return avatar_e;
            }

            public void setAvatar_e(String avatar_e) {
                this.avatar_e = avatar_e;
            }

            public String getAvatar_d() {
                return avatar_d;
            }

            public void setAvatar_d(String avatar_d) {
                this.avatar_d = avatar_d;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getAvatar_c() {
                return avatar_c;
            }

            public void setAvatar_c(String avatar_c) {
                this.avatar_c = avatar_c;
            }

            public String getAvatar_b() {
                return avatar_b;
            }

            public void setAvatar_b(String avatar_b) {
                this.avatar_b = avatar_b;
            }

            public String getIs_daren() {
                return is_daren;
            }

            public void setIs_daren(String is_daren) {
                this.is_daren = is_daren;
            }

            public String getAvatar_a() {
                return avatar_a;
            }

            public void setAvatar_a(String avatar_a) {
                this.avatar_a = avatar_a;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar_o() {
                return avatar_o;
            }

            public void setAvatar_o(String avatar_o) {
                this.avatar_o = avatar_o;
            }

            public String getData_desc() {
                return data_desc;
            }

            public void setData_desc(String data_desc) {
                this.data_desc = data_desc;
            }

            public int getFollowed() {
                return followed;
            }

            public void setFollowed(int followed) {
                this.followed = followed;
            }

            public int getPost_num() {
                return post_num;
            }

            public void setPost_num(int post_num) {
                this.post_num = post_num;
            }

            public String getIdentity_desc() {
                return identity_desc;
            }

            public void setIdentity_desc(String identity_desc) {
                this.identity_desc = identity_desc;
            }

            public String getAbout_me() {
                return about_me;
            }

            public void setAbout_me(String about_me) {
                this.about_me = about_me;
            }

            public int getIs_me() {
                return is_me;
            }

            public void setIs_me(int is_me) {
                this.is_me = is_me;
            }

            public int getFollowing_num() {
                return following_num;
            }

            public void setFollowing_num(int following_num) {
                this.following_num = following_num;
            }

            public String getIdentity_img() {
                return identity_img;
            }

            public void setIdentity_img(String identity_img) {
                this.identity_img = identity_img;
            }

            public int getFollow_status() {
                return follow_status;
            }

            public void setFollow_status(int follow_status) {
                this.follow_status = follow_status;
            }

            public int getGet_praise_num() {
                return get_praise_num;
            }

            public void setGet_praise_num(int get_praise_num) {
                this.get_praise_num = get_praise_num;
            }

            public long getUser_id() {
                return user_id;
            }

            public void setUser_id(long user_id) {
                this.user_id = user_id;
            }
        }
    }
}
