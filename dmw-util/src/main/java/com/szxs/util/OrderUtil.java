package com.szxs.util;

import java.util.Random;

public class OrderUtil {

    public static String Order(){
        //  orderNo:"D1000001202209301539184bc20e"
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
//        sb.append(System.currentTimeMillis());
//        sb.append(Math.random()*1000000);
       // sb.append("D1000001");
     //   sb.append(DateUtil.getCurrentDate("yyyyMMdd"));
//        sb.append("D1000001");
        sb.append(DateUtil.getCurrentDate("yyyyMMddHHmmss"));

        return sb.toString();
    }

    public static String MathOrder(int length){
        StringBuffer sbf = new StringBuffer();
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String flag = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(flag) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sbf.append((char)(random.nextInt(26) + temp));
            } else if( "num".equalsIgnoreCase(flag) ) {
                sbf.append(String.valueOf(random.nextInt(10)));
            }
        }
        return sbf.toString();
    }
}
