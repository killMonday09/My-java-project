package com.szxs.util;

import java.util.Collection;
import java.util.Map;

/**
 * 判断是否是空的
 */
public class EmptyUtil {
    /***
     * 判断对象是否为空
     * @param obj 任意对象
     * @return true是空 false非空
     */
    public static boolean isEmpty(Object obj){
        if (obj == null)
            return true;
        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;
        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();
        if (obj instanceof Map)
            return ((Map) obj).isEmpty();
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /***
     * 判断对象是否为空
     * @param obj 任意对象
     * @return true非空 false是空
     */
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }

    /***
     * 判断对象是否为空
     * @param args 数组对象
     * @return 数组中有一个是空的就返回true  否则false
     */
    private boolean validPropertyEmpty(Object ...args) {
        for (int i = 0; i < args.length; i++) {
            if(EmptyUtil.isEmpty(args[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * 检测是否是数字
     * @param num
     * @return
     */
    public static boolean isNumber(String num){
        try {
            Integer.parseInt(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
