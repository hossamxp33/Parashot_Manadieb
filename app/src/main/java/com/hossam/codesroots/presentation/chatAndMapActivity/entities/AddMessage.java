package com.hossam.codesroots.presentation.chatAndMapActivity.entities;

public class AddMessage {


    /**
     * success : true
     * chat : {"user_id":129,"room_id":"11","post":"hello ahmed","photo":"","name":"err4443eedd","created":"2019-01-14T19:52:18+0300","modified":"2019-01-14T19:52:18+0300","id":1839}
     */

    private Boolean success;
    private ChatBean chat;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ChatBean getChat() {
        return chat;
    }

    public void setChat(ChatBean chat) {
        this.chat = chat;
    }

    public static class ChatBean {
        /**
         * user_id : 129
         * room_id : 11
         * post : hello ahmed
         * photo :
         * name : err4443eedd
         * created : 2019-01-14T19:52:18+0300
         * modified : 2019-01-14T19:52:18+0300
         * id : 1839
         */

        private int user_id;
        private String room_id;
        private String post;
        private String photo;
        private String name;
        private String created;
        private String modified;
        private int id;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
