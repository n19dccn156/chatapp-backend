package com.project.chatapp.util;

public class Const {

    public static final String HOST = "http://localhost:8111";
    public static final long ACCESS_TOKEN_EXPIRE = 60*5*1000;
    public static final long REFRESH_TOKEN_EXPIRE = 60*60*24*7;
    public static String getUriImage(Integer avatarId) {
        return "/api/images/" + avatarId;
    }

    public static final String FRIEND_STATE = "FRIEND";
    public static final String BLOCK_STATE = "BLOCKED";
    public static final String CONFIRM_STATE = "CONFIRM";
    public static final String UNFRIEND_STATE = "UNFRIEND";
}
