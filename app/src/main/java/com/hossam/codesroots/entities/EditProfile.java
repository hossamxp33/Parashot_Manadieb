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
        private int gender = 0;
        private String email;
        private String phone;
        private String username;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
