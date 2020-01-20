package com.hossam.codesroots.entities;

public class LoginResponseModel {


    /**
     * success : true
     * data : {"id":113,"group":"1","username":"admin","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjExMywiZXhwIjoxNTQ5MTE4MTQ0fQ.hHF8E6zUbx-5fj8iXDnhwbZSnE3kLRXN_0SavoLo26w"}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 113
         * group : 1
         * username : admin
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjExMywiZXhwIjoxNTQ5MTE4MTQ0fQ.hHF8E6zUbx-5fj8iXDnhwbZSnE3kLRXN_0SavoLo26w
         */

        private int id;
        private String group;
        private String username;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
