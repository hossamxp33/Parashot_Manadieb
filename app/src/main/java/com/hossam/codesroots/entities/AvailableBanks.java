package com.hossam.codesroots.entities;

import java.util.List;

public class AvailableBanks {


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
         * iban : SA06-2000-0001-0503-0004-9940
         * bankname_en : RiyadBank
         * bankname_ar : بنك الرياض
         * bankimage : http://parashot.codesroots.com/img/1542394947480931340.jpg
         * bankaccount_number : 1050300049940
         * created : 2019-02-24T14:25:14+0000
         * modified : 2019-02-24T14:25:14+0000
         */

        private int id;
        private String iban;
        private String bankname_en;
        private String bankname_ar;
        private String bankimage;
        private long bankaccount_number;
        private String created;
        private String modified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public String getBankname_en() {
            return bankname_en;
        }

        public void setBankname_en(String bankname_en) {
            this.bankname_en = bankname_en;
        }

        public String getBankname_ar() {
            return bankname_ar;
        }

        public void setBankname_ar(String bankname_ar) {
            this.bankname_ar = bankname_ar;
        }

        public String getBankimage() {
            return bankimage;
        }

        public void setBankimage(String bankimage) {
            this.bankimage = bankimage;
        }

        public long getBankaccount_number() {
            return bankaccount_number;
        }

        public void setBankaccount_number(long bankaccount_number) {
            this.bankaccount_number = bankaccount_number;
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

