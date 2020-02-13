package com.hossam.codesroots.entities;

/**
 * Created by Hossam on 1/7/2020.
 */
public class SocialMediaModel {
    /**
     * success : true
     * data : {"id":274,"email":"asada@adfasdf.sdf","gender":"male","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI3NCwiZXhwIjoxNTc5MDI1MjEyfQ.tY3Sjbkbs_0cShrLFXy_rEB5KGKzC0fUINBwN4KF3LA"}
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
         * id : 274
         * email : asada@adfasdf.sdf
         * gender : male
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI3NCwiZXhwIjoxNTc5MDI1MjEyfQ.tY3Sjbkbs_0cShrLFXy_rEB5KGKzC0fUINBwN4KF3LA
         */

        private int id;
        private String email;
        private String last;

        private String gender;
        private String token;
        private String delivery;

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
        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }
        public String getDelivery() {
            return delivery;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
        public void setLast(String last) {
            this.last = last;
        }
        public String getLast() {
            return last;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
