package com.briup.cms_jpa.util;

import java.util.Date;
/*
    封装响应通知
 */
public class MessageUtil {
	
    /**
     * 成功并返回数据实体类
     * @param o
     * @param <E>
     * @return
     */
    public static <E>Message<E> success(E o,String msg){
        return new Message<>(CodeUtil.SUCCESS_CODE, msg, o, new Date().getTime());
    }

    /**
     * 成功，但无数据实体类返回
     * @return
     */
    public static <E>Message<E> success(String message){
        return new Message<>(CodeUtil.SUCCESS_CODE, message, null, new Date().getTime());
    }

    /**
     * 失败，有自定义异常返回
     * @param code
     * @param msg
     * @return
     */
    public static <E>Message<E> error(String msg){
        return new Message<>(CodeUtil.BAD_CODE, msg, null, new Date().getTime());
    }
//    public static <E>Message<E> unAuthorized(String msg){
//        return new Message<>(CodeUtil.UNAUTHOROZED_CODE, msg, null, new Date().getTime());
//    }
//    public static <E>Message<E> forbidden(String msg){
//        return new Message<>(CodeUtil.FORBIDDEN_CODE, msg, null, new Date().getTime());
//    }

}
