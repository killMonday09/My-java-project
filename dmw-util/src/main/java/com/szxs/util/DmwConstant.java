package com.szxs.util;

public class DmwConstant {

    /**
     * 失败状态码
     */
    public static final String EXCEPTION_CODE = "10001";

    /**
     * 查询成功状态码
     */
    public static final String SUCCESS_CODE = "0000";


    public static final String IMG_PRIXEF = "img:";

    public static final String IMG_DEFAULT_URL = "http://localhost/imgdefault_normal.jpg";

    public static final String SEARCH_READTIME = "es-readtime";

    public static class User{

        public static final String IMG_URL = "http://static/img/cardimg8.jpg";

        public static final int TOKEN_EXPTIME_TIME_SECOND = 7200;

        public static final Integer LEVEL1 = 1;
        public static final Integer LEVEL2 = 2;
        public static final Integer LEVEL3 = 3;

        //已激活
        public static final Integer ACTIVATED = 1;
        //未激活
        public static final Integer UN_ACTIVATED = 0;

        //令牌过期时间
        public static final Integer TOKEN_EXPIRE_TIME = 7200;

        //令牌存储前缀
        public static final String TOKEN_PRIFIX = "token:";

        //短信验证码前缀
        public static final String PHONE_CODE_PRIFIX = "phone-code:";

        //置换时间
        public static final Long TOKEN_RESET_TIME = 1800L;

        //验证码过期时间
        public static final Integer REGISTER_CODE_TIME = 60;

        //短信验证码过期时间
        public static final Integer REGISTER_CODE_SMS_TIME = 60;
    }

    public static class City{
        //热门城市列表前缀
        public static final String CITY_HOT_LIST = "city:hot:";
        //热门城市列表前缀 过期时间7天
        public static final int CITY_HOT_LIST_EXPIRE = 7*24*60*60;
        //国内外
        public static final int IS_CHINA = 1;
        public static final int IS_NOT_CHINA = 2;

        //热门
        public static final int IS_HOT = 1;
        public static final int IS_NOT_HOT = 0;
    }

    public static class Lable{
        //热门城市列表前缀
        public static final String LABLE_LIST = "LABLE:";

    }


}
