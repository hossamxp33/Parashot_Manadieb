package com.hossam.codesroots.entities;

/**
 * Created by Hossam on 1/7/2020.
 */
public class EditProfile {


    /**
     * success : true
     * data : {"id":283,"email":"hossam_xeon@yahoo.com","gender":"","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI4MywiZXhwIjoxNTc5OTcyNjE3fQ.tiQ9GNFREQSt0UFu-6HUS6Yx-_pz3rtRJHAnPPuJwfo"}
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
         * id : 283
         * email : hossam_xeon@yahoo.com
         * gender :
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI4MywiZXhwIjoxNTc5OTcyNjE3fQ.tiQ9GNFREQSt0UFu-6HUS6Yx-_pz3rtRJHAnPPuJwfo
         */

        private int id;
        private String email;
        private String gender;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
