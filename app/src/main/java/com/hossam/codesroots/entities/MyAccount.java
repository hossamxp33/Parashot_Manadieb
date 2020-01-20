package com.hossam.codesroots.entities;


public class MyAccount {


    /**
     * sort : {"acceptcount":4,"total":4000,"total_month":4216,"total_week":4216,"monthcount":16,"weekcount":16,"cancelcount":0,"cancelsum":null,"acceptsum":1054}
     */

    private SortBean sort;

    public SortBean getSort() {
        return sort;
    }

    public void setSort(SortBean sort) {
        this.sort = sort;
    }

    public static class SortBean {
        /**
         * acceptcount : 4
         * total : 4000
         * total_month : 4216
         * total_week : 4216
         * monthcount : 16
         * weekcount : 16
         * cancelcount : 0
         * cancelsum : null
         * acceptsum : 1054
         */

        private int acceptcount;
        private int total;
        private int total_month;
        private int total_week;
        private int monthcount;
        private int weekcount;
        private int cancelcount;
        private String cancelsum;
        private int acceptsum;

        public int getAcceptcount() {
            return acceptcount;
        }

        public void setAcceptcount(int acceptcount) {
            this.acceptcount = acceptcount;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal_month() {
            return total_month;
        }

        public void setTotal_month(int total_month) {
            this.total_month = total_month;
        }

        public int getTotal_week() {
            return total_week;
        }

        public void setTotal_week(int total_week) {
            this.total_week = total_week;
        }

        public int getMonthcount() {
            return monthcount;
        }

        public void setMonthcount(int monthcount) {
            this.monthcount = monthcount;
        }

        public int getWeekcount() {
            return weekcount;
        }

        public void setWeekcount(int weekcount) {
            this.weekcount = weekcount;
        }

        public int getCancelcount() {
            return cancelcount;
        }

        public void setCancelcount(int cancelcount) {
            this.cancelcount = cancelcount;
        }

        public String getCancelsum() {
            return cancelsum;
        }

        public void setCancelsum(String cancelsum) {
            this.cancelsum = cancelsum;
        }

        public int getAcceptsum() {
            return acceptsum;
        }

        public void setAcceptsum(int acceptsum) {
            this.acceptsum = acceptsum;
        }
    }
}
