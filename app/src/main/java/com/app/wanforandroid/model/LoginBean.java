package com.app.wanforandroid.model;

import java.util.List;

public class LoginBean extends BaseModel {

    /**
     * data : {"chapterTops":[],"collectIds":[3115,3117,3118,3119,3116,3102,3095,3094,3092,3090,3099,3127,3134,3135,3133,3204,3207,3198,3199,3202,3196,3195,3188,3180,3179,3177,3176,3160,3186,7459,7455,7457,7545,1165],"email":"","icon":"","id":7386,"password":"","token":"","type":0,"username":"thkcheng"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * chapterTops : []
         * collectIds : [3115,3117,3118,3119,3116,3102,3095,3094,3092,3090,3099,3127,3134,3135,3133,3204,3207,3198,3199,3202,3196,3195,3188,3180,3179,3177,3176,3160,3186,7459,7455,7457,7545,1165]
         * email :
         * icon :
         * id : 7386
         * password :
         * token :
         * type : 0
         * username : thkcheng
         */

        private String email;
        private String icon;
        private int id;
        private String password;
        private String token;
        private int type;
        private String username;
        private List<?> chapterTops;
        private List<Integer> collectIds;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
