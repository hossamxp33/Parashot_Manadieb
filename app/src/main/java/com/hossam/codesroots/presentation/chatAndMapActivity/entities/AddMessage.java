package com.hossam.codesroots.presentation.chatAndMapActivity.entities;

public class AddMessage {


    /**
     * success : true
     * chat : {"too":1,"fromm":2,"post":"hello ahmed","photo":"http://manadeeb.codesroots.com/library/chat/1545471495533423308.png","message_id":1,"name":"osama","created":"2018-12-22T12:38:15+0300","modified":"2018-12-22T12:38:15+0300","id":489}
     */

    private String success;
    private ChatBean chat;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
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
         * too : 1
         * fromm : 2
         * post : hello ahmed
         * photo : http://manadeeb.codesroots.com/library/chat/1545471495533423308.png
         * message_id : 1
         * name : osama
         * created : 2018-12-22T12:38:15+0300
         * modified : 2018-12-22T12:38:15+0300
         * id : 489
         */

        private int too;
        private int fromm;
        private String post;
        private String photo;
        private int message_id;
        private String name;
        private String created;
        private String modified;
        private int id;

        public int getToo() {
            return too;
        }

        public void setToo(int too) {
            this.too = too;
        }

        public int getFromm() {
            return fromm;
        }

        public void setFromm(int fromm) {
            this.fromm = fromm;
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

        public int getMessage_id() {
            return message_id;
        }

        public void setMessage_id(int message_id) {
            this.message_id = message_id;
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
