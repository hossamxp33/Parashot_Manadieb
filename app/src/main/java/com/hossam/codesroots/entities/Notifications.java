package com.hossam.codesroots.entities;

import java.util.List;

public class Notifications {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * text : Notification
         * user_id : 0
         * user_group_id : 1
         * created : 2018-12-01T13:46:35+0000
         * modified : null
         */

        private int id;
        private String text;
        private int user_id;
        private int user_group_id;
        private String created;
        private Object modified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getUser_group_id() {
            return user_group_id;
        }

        public void setUser_group_id(int user_group_id) {
            this.user_group_id = user_group_id;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Object getModified() {
            return modified;
        }

        public void setModified(Object modified) {
            this.modified = modified;
        }
    }
}
