package com.lib.http.utils;

/**
 * Created by thkcheng on 2018/11/27.
 */
public class Exceptions {

    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }

}
