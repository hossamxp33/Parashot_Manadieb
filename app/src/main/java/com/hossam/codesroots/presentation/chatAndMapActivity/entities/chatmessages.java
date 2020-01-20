package com.hossam.codesroots.presentation.chatAndMapActivity.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class chatmessages {


    private List<OrderBean> order;
    private List<MyChatBean> myChat;

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public List<MyChatBean> getMyChat() {
        return myChat;
    }

    public void setMyChat(List<MyChatBean> myChat) {
        this.myChat = myChat;
    }

    public static class OrderBean {
        /**
         * id : 1
         * user_id : 113
         * store_id : 1
         * order_status : 3
         * delivry_id : 1
         * created : 2018-11-18T12:38:19+0000
         * modified : 2019-01-09T14:22:38+0000
         * storename :
         * notes : osama is here
         * store_icon :
         * delivery_price : 300
         * user_lat :
         * user_long :
         * store_lat :
         * store_long :
         * photo :
         * user_address :
         * delivery_time :
         * rate : null
         * store_address : null
         * payment_id : 0
         * room_id : jz7gvaktge
         * smallstore : {"id":1,"name":"متجرك","bank_accounts":"4545","phone":"4545","alternative_phone":"787878","cover":"http://parashot.codesroots.com/library/default/c23dbaa0-f340-469e-a1c8-9851639715ae.jpeg","commercial_register_number":4545,"link":"dasds","description":"sss","branches":3,"birthdate":null,"city_id":0,"subcat_id":1,"logodesign_id":0,"visible":"true","user_id":1,"template_id":1,"category_id":1,"logo":"http://parashot.codesroots.com/library/default/15395177111708407597.png","created":null,"modified":null,"longitude":31.2089,"latitude":30.0131,"address":"حازم بيمسي","storebackground":"http://parashot.codesroots.com/library/default/c23dbaa0-f340-469e-a1c8-9851639715ae.jpeg","storetext":"osama is here","first_icon":"http://parashot.codesroots.com/library/default/15395177111708407597.png","second_icon":"http://parashot.codesroots.com/library/default/15395177111708407597.png","third_icon":"http://parashot.codesroots.com/library/default/15395177111708407597.png","forth_icon":"http://parashot.codesroots.com/library/default/15395177111708407597.png"}
         * user : {"id":113,"username":"admin","active":1,"email_verified":1,"user_group_id":"1","long":"","lat":"","gender":"Male","phone":"sssasss","purchase_way":0,"points":0,"type_phone":0,"created":"2018-10-09T13:01:38+0000","modified":"2018-12-11T10:12:44+0000","email":"sssasss","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png","birthdate":null,"facebook_id":1}
         * delivry : {"id":1,"name":"حازم","user_id":454545,"personal_id_image":"","personal_license_image":"","bank_accounts":"8888","phone":"010","alternative_phone":"010","gender":"male","code":0,"created":null,"modified":null,"delivery_long":"31.2099091","delivery_lat":"30.0384395","photo":"http://parashot.codesroots.com/library/osama.jpeg"}
         */

        private int id;
        private int user_id;
        private int store_id;
        private String order_status;
        private int delivry_id;
        private String created;
        private String modified;
        private String storename;
        private String notes;
        private String store_icon;
        private int delivery_price;
        private String user_lat;
        private String user_long;
        private String store_lat;
        private String store_long;
        private String photo;
        private String user_address;
        private String delivery_time;
        private Object rate;
        private Object store_address;
        private int payment_id;
        private String room_id;
        private SmallstoreBean smallstore;
        private UserBean user;
        private DelivryBean delivry;

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

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public int getDelivry_id() {
            return delivry_id;
        }

        public void setDelivry_id(int delivry_id) {
            this.delivry_id = delivry_id;
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

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getStore_icon() {
            return store_icon;
        }

        public void setStore_icon(String store_icon) {
            this.store_icon = store_icon;
        }

        public int getDelivery_price() {
            return delivery_price;
        }

        public void setDelivery_price(int delivery_price) {
            this.delivery_price = delivery_price;
        }

        public String getUser_lat() {
            return user_lat;
        }

        public void setUser_lat(String user_lat) {
            this.user_lat = user_lat;
        }

        public String getUser_long() {
            return user_long;
        }

        public void setUser_long(String user_long) {
            this.user_long = user_long;
        }

        public String getStore_lat() {
            return store_lat;
        }

        public void setStore_lat(String store_lat) {
            this.store_lat = store_lat;
        }

        public String getStore_long() {
            return store_long;
        }

        public void setStore_long(String store_long) {
            this.store_long = store_long;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public Object getRate() {
            return rate;
        }

        public void setRate(Object rate) {
            this.rate = rate;
        }

        public Object getStore_address() {
            return store_address;
        }

        public void setStore_address(Object store_address) {
            this.store_address = store_address;
        }

        public int getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(int payment_id) {
            this.payment_id = payment_id;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public SmallstoreBean getSmallstore() {
            return smallstore;
        }

        public void setSmallstore(SmallstoreBean smallstore) {
            this.smallstore = smallstore;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public DelivryBean getDelivry() {
            return delivry;
        }

        public void setDelivry(DelivryBean delivry) {
            this.delivry = delivry;
        }

        public static class SmallstoreBean {
            /**
             * id : 1
             * name : متجرك
             * bank_accounts : 4545
             * phone : 4545
             * alternative_phone : 787878
             * cover : http://parashot.codesroots.com/library/default/c23dbaa0-f340-469e-a1c8-9851639715ae.jpeg
             * commercial_register_number : 4545
             * link : dasds
             * description : sss
             * branches : 3
             * birthdate : null
             * city_id : 0
             * subcat_id : 1
             * logodesign_id : 0
             * visible : true
             * user_id : 1
             * template_id : 1
             * category_id : 1
             * logo : http://parashot.codesroots.com/library/default/15395177111708407597.png
             * created : null
             * modified : null
             * longitude : 31.2089
             * latitude : 30.0131
             * address : حازم بيمسي
             * storebackground : http://parashot.codesroots.com/library/default/c23dbaa0-f340-469e-a1c8-9851639715ae.jpeg
             * storetext : osama is here
             * first_icon : http://parashot.codesroots.com/library/default/15395177111708407597.png
             * second_icon : http://parashot.codesroots.com/library/default/15395177111708407597.png
             * third_icon : http://parashot.codesroots.com/library/default/15395177111708407597.png
             * forth_icon : http://parashot.codesroots.com/library/default/15395177111708407597.png
             */

            private int id;
            private String name;
            private String bank_accounts;
            private String phone;
            private String alternative_phone;
            private String cover;
            private int commercial_register_number;
            private String link;
            private String description;
            private int branches;
            private Object birthdate;
            private int city_id;
            private int subcat_id;
            private int logodesign_id;
            private String visible;
            private int user_id;
            private int template_id;
            private int category_id;
            private String logo;
            private Object created;
            private Object modified;
            private double longitude;
            private double latitude;
            private String address;
            private String storebackground;
            private String storetext;
            private String first_icon;
            private String second_icon;
            private String third_icon;
            private String forth_icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBank_accounts() {
                return bank_accounts;
            }

            public void setBank_accounts(String bank_accounts) {
                this.bank_accounts = bank_accounts;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAlternative_phone() {
                return alternative_phone;
            }

            public void setAlternative_phone(String alternative_phone) {
                this.alternative_phone = alternative_phone;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getCommercial_register_number() {
                return commercial_register_number;
            }

            public void setCommercial_register_number(int commercial_register_number) {
                this.commercial_register_number = commercial_register_number;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getBranches() {
                return branches;
            }

            public void setBranches(int branches) {
                this.branches = branches;
            }

            public Object getBirthdate() {
                return birthdate;
            }

            public void setBirthdate(Object birthdate) {
                this.birthdate = birthdate;
            }

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public int getSubcat_id() {
                return subcat_id;
            }

            public void setSubcat_id(int subcat_id) {
                this.subcat_id = subcat_id;
            }

            public int getLogodesign_id() {
                return logodesign_id;
            }

            public void setLogodesign_id(int logodesign_id) {
                this.logodesign_id = logodesign_id;
            }

            public String getVisible() {
                return visible;
            }

            public void setVisible(String visible) {
                this.visible = visible;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(int template_id) {
                this.template_id = template_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public Object getCreated() {
                return created;
            }

            public void setCreated(Object created) {
                this.created = created;
            }

            public Object getModified() {
                return modified;
            }

            public void setModified(Object modified) {
                this.modified = modified;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getStorebackground() {
                return storebackground;
            }

            public void setStorebackground(String storebackground) {
                this.storebackground = storebackground;
            }

            public String getStoretext() {
                return storetext;
            }

            public void setStoretext(String storetext) {
                this.storetext = storetext;
            }

            public String getFirst_icon() {
                return first_icon;
            }

            public void setFirst_icon(String first_icon) {
                this.first_icon = first_icon;
            }

            public String getSecond_icon() {
                return second_icon;
            }

            public void setSecond_icon(String second_icon) {
                this.second_icon = second_icon;
            }

            public String getThird_icon() {
                return third_icon;
            }

            public void setThird_icon(String third_icon) {
                this.third_icon = third_icon;
            }

            public String getForth_icon() {
                return forth_icon;
            }

            public void setForth_icon(String forth_icon) {
                this.forth_icon = forth_icon;
            }
        }

        public static class UserBean {
            /**
             * id : 113
             * username : admin
             * active : 1
             * email_verified : 1
             * user_group_id : 1
             * long :
             * lat :
             * gender : Male
             * phone : sssasss
             * purchase_way : 0
             * points : 0
             * type_phone : 0
             * created : 2018-10-09T13:01:38+0000
             * modified : 2018-12-11T10:12:44+0000
             * email : sssasss
             * photo : http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png
             * birthdate : null
             * facebook_id : 1
             */

            private int id;
            private String username;
            private int active;
            private int email_verified;
            private String user_group_id;
            @SerializedName("long")
            private String longX;
            private String lat;
            private String gender;
            private String phone;
            private int purchase_way;
            private int points;
            private int type_phone;
            private String created;
            private String modified;
            private String email;
            private String photo;
            private Object birthdate;
            private int facebook_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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

            public String getUser_group_id() {
                return user_group_id;
            }

            public void setUser_group_id(String user_group_id) {
                this.user_group_id = user_group_id;
            }

            public String getLongX() {
                return longX;
            }

            public void setLongX(String longX) {
                this.longX = longX;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getPurchase_way() {
                return purchase_way;
            }

            public void setPurchase_way(int purchase_way) {
                this.purchase_way = purchase_way;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public int getType_phone() {
                return type_phone;
            }

            public void setType_phone(int type_phone) {
                this.type_phone = type_phone;
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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public Object getBirthdate() {
                return birthdate;
            }

            public void setBirthdate(Object birthdate) {
                this.birthdate = birthdate;
            }

            public int getFacebook_id() {
                return facebook_id;
            }

            public void setFacebook_id(int facebook_id) {
                this.facebook_id = facebook_id;
            }
        }

        public static class DelivryBean {
            /**
             * id : 1
             * name : حازم
             * user_id : 454545
             * personal_id_image :
             * personal_license_image :
             * bank_accounts : 8888
             * phone : 010
             * alternative_phone : 010
             * gender : male
             * code : 0
             * created : null
             * modified : null
             * delivery_long : 31.2099091
             * delivery_lat : 30.0384395
             * photo : http://parashot.codesroots.com/library/osama.jpeg
             */

            private int id;
            private String name;
            private int user_id;
            private String personal_id_image;
            private String personal_license_image;
            private String bank_accounts;
            private String phone;
            private String alternative_phone;
            private String gender;
            private int code;
            private Object created;
            private Object modified;
            private String delivery_long;
            private String delivery_lat;
            private String photo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getPersonal_id_image() {
                return personal_id_image;
            }

            public void setPersonal_id_image(String personal_id_image) {
                this.personal_id_image = personal_id_image;
            }

            public String getPersonal_license_image() {
                return personal_license_image;
            }

            public void setPersonal_license_image(String personal_license_image) {
                this.personal_license_image = personal_license_image;
            }

            public String getBank_accounts() {
                return bank_accounts;
            }

            public void setBank_accounts(String bank_accounts) {
                this.bank_accounts = bank_accounts;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAlternative_phone() {
                return alternative_phone;
            }

            public void setAlternative_phone(String alternative_phone) {
                this.alternative_phone = alternative_phone;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public Object getCreated() {
                return created;
            }

            public void setCreated(Object created) {
                this.created = created;
            }

            public Object getModified() {
                return modified;
            }

            public void setModified(Object modified) {
                this.modified = modified;
            }

            public String getDelivery_long() {
                return delivery_long;
            }

            public void setDelivery_long(String delivery_long) {
                this.delivery_long = delivery_long;
            }

            public String getDelivery_lat() {
                return delivery_lat;
            }

            public void setDelivery_lat(String delivery_lat) {
                this.delivery_lat = delivery_lat;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }
    }

    public static class MyChatBean {
        /**
         * id : 1
         * post : HI
         * created : 2018-01-28T15:18:03+0000
         * modified : 2018-12-30T09:22:40+0000
         * user_id : 113
         * photo : 1
         * room_id :
         * seen : 1
         * order_id : 1
         * user : {"id":113,"user_group_id":"1","username":"admin","email":"sssasss","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}
         */

        private int id;
        private String post;
        private String created;
        private String modified;
        private int user_id;
        private String photo;
        private String room_id;
        private int seen;
        private int order_id;
        private UserBeanX user;


        public MyChatBean(String message, int fromm, String photo, String time,int group) {
            this.post = message;
            this.user_id=fromm;
            this.photo = photo;
            this.created = time;
            this.user = new UserBeanX(group);

            }

        public MyChatBean(String message, int fromm, String photo, String time) {
            this.post = message;
            this.user_id=fromm;
            this.photo = photo;
            this.created = time;
        }

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

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public int getSeen() {
            return seen;
        }

        public void setSeen(int seen) {
            this.seen = seen;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public static class UserBeanX {
            /**
             * id : 113
             * user_group_id : 1
             * username : admin
             * email : sssasss
             * photo : http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png
             */

            private int id;
            private int user_group_id;
            private String username;
            private String email;
            private String photo;

            public UserBeanX(int user_group_id) {
                this.user_group_id = user_group_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_group_id() {
                return user_group_id;
            }

            public void setUser_group_id(int user_group_id) {
                this.user_group_id = user_group_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }
    }
}
