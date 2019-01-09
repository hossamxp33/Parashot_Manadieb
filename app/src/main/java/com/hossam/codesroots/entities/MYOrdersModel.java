package com.hossam.codesroots.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class MYOrdersModel {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 36
         * user_id : 1
         * store_id : 50
         * order_status : 2
         * delivry_id : 1
         * created : 2018-10-27T00:00:00+0000
         * modified : 2018-10-29T14:37:16+0000
         * orderdetails : [{"id":59,"product_id":1,"price":"5,8499.00ريال","address":"maddi","type":"ss","user_lat":"76","user_long":"60","notice":"no","created":"2018-10-28T14:39:09+0000","modified":"2018-10-28T14:39:09+0000","payment_id":1,"order_id":36,"product":{"id":1,"name":"iphone","subcat_id":"","category_id":"","price":"10","last_price":"15","description":"iphone is a line of smartphones designed and marketed by Apple Inc","store_id":"50","brand":"apple","product_info":"<html>\r\n<head>\r\n<\/head>\r\n<body>\r\n<ul>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<\/ul>\r\n<\/body>\r\n<\/html>","amount":2,"guarantee":1,"created":"2018-09-17T16:37:29+0000","modified":"2018-09-17T16:38:03+0000","visible":"false","total_rating":[{"product_id":1,"stars":6,"count":3}],"productphotos":[{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}],"productrates":[{"id":1,"rate":3,"product_id":1,"user_id":1,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":2,"rate":2,"product_id":1,"user_id":1,"created":"2018-10-03T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":6,"rate":1,"product_id":1,"user_id":1,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"}]}}]
         * store : {"id":50,"name":"ahmed12345"}
         * delivry : {"id":1,"name":"حازم"}
         */

        private int id;
        private int user_id;
        private int store_id;
        private String order_status;
        private int delivry_id;
        private String created;
        private String modified;
        private String photo;
        private String notes;
        private String store_icon;
        private StoreBean store;
        private String storename;
        private float rate;
        private DelivryBean delivry;
        private User user;
        private SmallStore smallStore;

        public SmallStore getSmallStore() {
            return smallStore;
        }

        public void setSmallStore(SmallStore smallStore) {
            this.smallStore = smallStore;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        private List<OrderdetailsBean> orderdetails;

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getStore_icon() {
            return store_icon;
        }

        public void setStore_icon(String store_icon) {
            this.store_icon = store_icon;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

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

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public DelivryBean getDelivry() {
            return delivry;
        }

        public void setDelivry(DelivryBean delivry) {
            this.delivry = delivry;
        }

        public List<OrderdetailsBean> getOrderdetails() {
            return orderdetails;
        }

        public void setOrderdetails(List<OrderdetailsBean> orderdetails) {
            this.orderdetails = orderdetails;
        }

        public static class StoreBean {
            /**
             * id : 50
             * name : ahmed12345
             */

            private int id;
            private String name;

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
        }

        public static class DelivryBean {
            /**
             * id : 1
             * name : حازم
             */

            private int id;
            private String name;
            private String photo;

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

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
        }

        public static class User {
            /**
             * id : 1
             * name : حازم
             */

            private int id;
            private String username;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return username;
            }

            public void setName(String name) {
                this.username = name;
            }
        }

        public static class SmallStore {
            /**
             * id : 1
             * name : حازم
             */

            private int id;
            private String username;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return username;
            }

            public void setName(String name) {
                this.username = name;
            }
        }
        public static class OrderdetailsBean implements Parcelable {
            /**
             * id : 59
             * product_id : 1
             * price : 5,8499.00ريال
             * address : maddi
             * type : ss
             * user_lat : 76
             * user_long : 60
             * notice : no
             * created : 2018-10-28T14:39:09+0000
             * modified : 2018-10-28T14:39:09+0000
             * payment_id : 1
             * order_id : 36
             * product : {"id":1,"name":"iphone","subcat_id":"","category_id":"","price":"10","last_price":"15","description":"iphone is a line of smartphones designed and marketed by Apple Inc","store_id":"50","brand":"apple","product_info":"<html>\r\n<head>\r\n<\/head>\r\n<body>\r\n<ul>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<\/ul>\r\n<\/body>\r\n<\/html>","amount":2,"guarantee":1,"created":"2018-09-17T16:37:29+0000","modified":"2018-09-17T16:38:03+0000","visible":"false","total_rating":[{"product_id":1,"stars":6,"count":3}],"productphotos":[{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}],"productrates":[{"id":1,"rate":3,"product_id":1,"user_id":1,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":2,"rate":2,"product_id":1,"user_id":1,"created":"2018-10-03T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":6,"rate":1,"product_id":1,"user_id":1,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"}]}
             */

            private int id;
            private int product_id;
            private String price;
            private String address;
            private String type;
            private String user_lat;
            private String user_long;
            private String notice;
            private String created;
            private String modified;
            private int payment_id;
            private int order_id;
            private ProductBean product;

            protected OrderdetailsBean(Parcel in) {
                id = in.readInt();
                product_id = in.readInt();
                price = in.readString();
                address = in.readString();
                type = in.readString();
                user_lat = in.readString();
                user_long = in.readString();
                notice = in.readString();
                created = in.readString();
                modified = in.readString();
                payment_id = in.readInt();
                order_id = in.readInt();
            }

            public static final Creator<OrderdetailsBean> CREATOR = new Creator<OrderdetailsBean>() {
                @Override
                public OrderdetailsBean createFromParcel(Parcel in) {
                    return new OrderdetailsBean(in);
                }

                @Override
                public OrderdetailsBean[] newArray(int size) {
                    return new OrderdetailsBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
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

            public int getPayment_id() {
                return payment_id;
            }

            public void setPayment_id(int payment_id) {
                this.payment_id = payment_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeInt(product_id);
                dest.writeString(price);
                dest.writeString(address);
                dest.writeString(type);
                dest.writeString(user_lat);
                dest.writeString(user_long);
                dest.writeString(notice);
                dest.writeString(created);
                dest.writeString(modified);
                dest.writeInt(payment_id);
                dest.writeInt(order_id);
            }

            public static class ProductBean implements Serializable{
                /**
                 * id : 1
                 * name : iphone
                 * subcat_id :
                 * category_id :
                 * price : 10
                 * last_price : 15
                 * description : iphone is a line of smartphones designed and marketed by Apple Inc
                 * store_id : 50
                 * brand : apple
                 * product_info : <html>
                 <head>
                 </head>
                 <body>
                 <ul>
                 <li>دقه الكاميرا:5 ميجابكسل</li>
                 <li>رام: 1 جيجابايت</li>
                 <li>كاميرا ثانيه:5 ميجابكسل</li>
                 <li>بنيه الشبكات:4جي ال تي اي</li>
                 <li>دقه الكاميرا:5 ميجابكسل</li>
                 <li>رام: 1 جيجابايت</li>
                 <li>كاميرا ثانيه:5 ميجابكسل</li>
                 <li>بنيه الشبكات:4جي ال تي اي</li>
                 <li>دقه الكاميرا:5 ميجابكسل</li>
                 <li>رام: 1 جيجابايت</li>
                 <li>كاميرا ثانيه:5 ميجابكسل</li>
                 <li>بنيه الشبكات:4جي ال تي اي</li>
                 </ul>
                 </body>
                 </html>
                 * amount : 2
                 * guarantee : 1
                 * created : 2018-09-17T16:37:29+0000
                 * modified : 2018-09-17T16:38:03+0000
                 * visible : false
                 * total_rating : [{"product_id":1,"stars":6,"count":3}]
                 * productphotos : [{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}]
                 * productrates : [{"id":1,"rate":3,"product_id":1,"user_id":1,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":2,"rate":2,"product_id":1,"user_id":1,"created":"2018-10-03T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":6,"rate":1,"product_id":1,"user_id":1,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"}]
                 */

                private int id;
                private String name;
                private String subcat_id;
                private String category_id;
                private String price;
                private String last_price;
                private String description;
                private String store_id;
                private String brand;
                private String product_info;
                private int amount;
                private int guarantee;
                private String created;
                private String modified;
                private String visible;
                private List<TotalRatingBean> total_rating;
                private List<ProductphotosBean> productphotos;
                private List<ProductratesBean> productrates;

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

                public String getSubcat_id() {
                    return subcat_id;
                }

                public void setSubcat_id(String subcat_id) {
                    this.subcat_id = subcat_id;
                }

                public String getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getLast_price() {
                    return last_price;
                }

                public void setLast_price(String last_price) {
                    this.last_price = last_price;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
                }

                public String getBrand() {
                    return brand;
                }

                public void setBrand(String brand) {
                    this.brand = brand;
                }

                public String getProduct_info() {
                    return product_info;
                }

                public void setProduct_info(String product_info) {
                    this.product_info = product_info;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public int getGuarantee() {
                    return guarantee;
                }

                public void setGuarantee(int guarantee) {
                    this.guarantee = guarantee;
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

                public String getVisible() {
                    return visible;
                }

                public void setVisible(String visible) {
                    this.visible = visible;
                }

                public List<TotalRatingBean> getTotal_rating() {
                    return total_rating;
                }

                public void setTotal_rating(List<TotalRatingBean> total_rating) {
                    this.total_rating = total_rating;
                }

                public List<ProductphotosBean> getProductphotos() {
                    return productphotos;
                }

                public void setProductphotos(List<ProductphotosBean> productphotos) {
                    this.productphotos = productphotos;
                }

                public List<ProductratesBean> getProductrates() {
                    return productrates;
                }

                public void setProductrates(List<ProductratesBean> productrates) {
                    this.productrates = productrates;
                }

                public static class TotalRatingBean {
                    /**
                     * product_id : 1
                     * stars : 6
                     * count : 3
                     */

                    private int product_id;
                    private int stars;
                    private int count;

                    public int getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(int product_id) {
                        this.product_id = product_id;
                    }

                    public int getStars() {
                        return stars;
                    }

                    public void setStars(int stars) {
                        this.stars = stars;
                    }

                    public int getCount() {
                        return count;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }
                }

                public static class ProductphotosBean {
                    /**
                     * id : 1
                     * photo : http://parashot.codesroots.com/library/default/35768252.jpg
                     * main : ahmed2
                     * product_id : 1
                     * created : 2018-09-18T14:47:31+0000
                     * modified : 2018-10-15T13:59:48+0000
                     */

                    private int id;
                    private String photo;
                    private String main;
                    private int product_id;
                    private String created;
                    private String modified;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getPhoto() {
                        return photo;
                    }

                    public void setPhoto(String photo) {
                        this.photo = photo;
                    }

                    public String getMain() {
                        return main;
                    }

                    public void setMain(String main) {
                        this.main = main;
                    }

                    public int getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(int product_id) {
                        this.product_id = product_id;
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
                }

                public static class ProductratesBean {
                    /**
                     * id : 1
                     * rate : 3
                     * product_id : 1
                     * user_id : 1
                     * created : 2018-09-19T09:29:00+0000
                     * modified : 2018-09-18T09:32:08+0000
                     */

                    private int id;
                    private int rate;
                    private int product_id;
                    private int user_id;
                    private String created;
                    private String modified;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getRate() {
                        return rate;
                    }

                    public void setRate(int rate) {
                        this.rate = rate;
                    }

                    public int getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(int product_id) {
                        this.product_id = product_id;
                    }

                    public int getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(int user_id) {
                        this.user_id = user_id;
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
                }
            }
        }
    }
}
