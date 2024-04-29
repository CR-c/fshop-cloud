package com.fshop.common.utils;

public class UserContext {

    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    public static void setUser(String user) {
        tl.set(user);
    }

    //获取用户
    public static String getUser() {

        return tl.get();
    }
    //删除用户
    public static void removeUser() {
        tl.remove();
    }
}
