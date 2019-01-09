package com.hossam.codesroots.presentation.chatAndMapActivity.entities;

import java.util.List;

public class ChatList {


    private List<MyChatBean> myChat;

    public List<MyChatBean> getMyChat() {
        return myChat;
    }

    public void setMyChat(List<MyChatBean> myChat) {
        this.myChat = myChat;
    }

    public static class MyChatBean {
        /**
         * id : 1
         * user_id : 1
         * too : 2
         * fromm : 1
         * created : 2018-01-28T15:16:12+0000
         * modified : 2018-12-22T15:32:42+0000
         * company_id : 0
         * users2 : null
         * users1 : {"id":2,"user_group_id":"2","username":"osama","password":"$2y$10$.dezXbhZdar0R0YWE45R3.NshUKQn6OXXqlWdcJe1tevJSWo469ei","email":"osama@gmail.com","first_name":null,"last_name":null,"gender":null,"photo":null,"bday":null,"active":0,"email_verified":1,"last_login":null,"ip_address":null,"created":"2018-06-26T14:19:34+0000","modified":"2018-06-26T14:19:34+0000","created_by":null,"modified_by":null,"mobile":"","isonline":0}
         * _matchingData : {"Chats":{"id":9,"post":"hey you!!","message_id":1,"created":"2018-01-28T15:26:41+0000","modified":"2018-01-28T15:26:41+0000","too":1,"fromm":2,"photo":"","room_id":"","company_id":0}}
         */

        private int id;
        private int user_id;
        private int too;
        private int fromm;
        private String created;
        private String modified;
        private int company_id;
        private Users1Bean users2;
        private Users1Bean users1;
        private MatchingDataBean _matchingData;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

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

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public Users1Bean getUsers2() {
            return users2;
        }

        public void setUsers2(Users1Bean users2) {
            this.users2 = users2;
        }

        public Users1Bean getUsers1() {
            return users1;
        }

        public void setUsers1(Users1Bean users1) {
            this.users1 = users1;
        }

        public MatchingDataBean get_matchingData() {
            return _matchingData;
        }

        public void set_matchingData(MatchingDataBean _matchingData) {
            this._matchingData = _matchingData;
        }

        public static class Users1Bean {
            /**
             * id : 2
             * user_group_id : 2
             * username : osama
             * password : $2y$10$.dezXbhZdar0R0YWE45R3.NshUKQn6OXXqlWdcJe1tevJSWo469ei
             * email : osama@gmail.com
             * first_name : null
             * last_name : null
             * gender : null
             * photo : null
             * bday : null
             * active : 0
             * email_verified : 1
             * last_login : null
             * ip_address : null
             * created : 2018-06-26T14:19:34+0000
             * modified : 2018-06-26T14:19:34+0000
             * created_by : null
             * modified_by : null
             * mobile :
             * isonline : 0
             */

            private int id;
            private String user_group_id;
            private String username;
            private String password;
            private String email;
            private Object first_name;
            private Object last_name;
            private Object gender;
            private Object photo;
            private Object bday;
            private int active;
            private int email_verified;
            private Object last_login;
            private Object ip_address;
            private String created;
            private String modified;
            private Object created_by;
            private Object modified_by;
            private String mobile;
            private int isonline;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUser_group_id() {
                return user_group_id;
            }

            public void setUser_group_id(String user_group_id) {
                this.user_group_id = user_group_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getFirst_name() {
                return first_name;
            }

            public void setFirst_name(Object first_name) {
                this.first_name = first_name;
            }

            public Object getLast_name() {
                return last_name;
            }

            public void setLast_name(Object last_name) {
                this.last_name = last_name;
            }

            public Object getGender() {
                return gender;
            }

            public void setGender(Object gender) {
                this.gender = gender;
            }

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
                this.photo = photo;
            }

            public Object getBday() {
                return bday;
            }

            public void setBday(Object bday) {
                this.bday = bday;
            }

            public int getActive() {
                return active;
            }

            public void setActive(int active) {
                this.active = active;
            }

            public int getEmail_verified() {
                return email_verified;
            }

            public void setEmail_verified(int email_verified) {
                this.email_verified = email_verified;
            }

            public Object getLast_login() {
                return last_login;
            }

            public void setLast_login(Object last_login) {
                this.last_login = last_login;
            }

            public Object getIp_address() {
                return ip_address;
            }

            public void setIp_address(Object ip_address) {
                this.ip_address = ip_address;
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

            public Object getCreated_by() {
                return created_by;
            }

            public void setCreated_by(Object created_by) {
                this.created_by = created_by;
            }

            public Object getModified_by() {
                return modified_by;
            }

            public void setModified_by(Object modified_by) {
                this.modified_by = modified_by;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getIsonline() {
                return isonline;
            }

            public void setIsonline(int isonline) {
                this.isonline = isonline;
            }
        }

        public static class MatchingDataBean {
            /**
             * Chats : {"id":9,"post":"hey you!!","message_id":1,"created":"2018-01-28T15:26:41+0000","modified":"2018-01-28T15:26:41+0000","too":1,"fromm":2,"photo":"","room_id":"","company_id":0}
             */

            private ChatsBean Chats;

            public ChatsBean getChats() {
                return Chats;
            }

            public void setChats(ChatsBean Chats) {
                this.Chats = Chats;
            }

            public static class ChatsBean {
                /**
                 * id : 9
                 * post : hey you!!
                 * message_id : 1
                 * created : 2018-01-28T15:26:41+0000
                 * modified : 2018-01-28T15:26:41+0000
                 * too : 1
                 * fromm : 2
                 * photo :
                 * room_id :
                 * company_id : 0
                 */

                private int id;
                private String post;
                private int message_id;
                private String created;
                private String modified;
                private int too;
                private int fromm;
                private String photo;
                private String room_id;
                private int company_id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getPost() {
                    return post;
                }

                public void setPost(String post) {
                    this.post = post;
                }

                public int getMessage_id() {
                    return message_id;
                }

                public void setMessage_id(int message_id) {
                    this.message_id = message_id;
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

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(String room_id) {
                    this.room_id = room_id;
                }

                public int getCompany_id() {
                    return company_id;
                }

                public void setCompany_id(int company_id) {
                    this.company_id = company_id;
                }
            }
        }
    }
}
