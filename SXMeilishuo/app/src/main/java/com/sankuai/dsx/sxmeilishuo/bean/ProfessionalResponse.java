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

        private List<UserInfoItem> list;

        public List<UserInfoItem> getList() {
            return list;
        }

        public void setList(List<UserInfoItem> list) {
            this.list = list;
        }
    }
}
