package com.szxs.util;

/**
 * @Author Rich
 * @create 2022/10/19 14:51
 */
public class AlxConstant {


    /**
     * sql常量值
     */
    public static class SQL_FINAL{
        //已激活
        public static final Integer IS = 1;
        //不激活
        public static final Integer IS_NOT = 0;
    }

    /**
     * 等级常量值
     */
    public static class Level{
        //分类级别1级
        public static final String Level1 = "1";

        //分类级别2级
        public static final String Level2 = "2";

        //父级类型0
        public static final Integer parent0 = 0;
    }

    /**
     * 用户常量值
     */
    public static class User{
        //图片存储前缀
        public static final String IMG_PREFIX = "img:";

        //图片存储后缀
        public static final String IMG_TYPE_POSTFIX = ":type:";

        //图片过期时间
        public static final Integer IMG_EXPIRE_TIME = 2;
    }

    /**
     * 公共常用类
     */
    public static class Common{
        public static final Integer ZERO = 0;
        public static final Integer ONE = 1;
        public static final Integer TWO = 2;
        public static final Integer Three = 3;
    }

    /**
     * 订单常用类
     */

    public static class Order{
        //订单状态
        public static final Integer NO_PAY = 0;
        public static final Integer TIMEOUT_PAY = 0;
        public static final Integer PAY = 0;

        //保险金额
        public static final double NEEDUNSYRANCE_MOENY = 20.0D;
    }
}
