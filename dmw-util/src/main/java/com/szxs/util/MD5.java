package com.szxs.util;

import com.szxs.entity.DmUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5 {
    /**
     * 使用md5加密
     * @param plainText  加密内容
     * @param length  加密长度
     * @return
     */
    public static String getMd5(String plainText,int length) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位
            // return buf.toString();
            // 16位
            // return buf.toString().substring(0, 16);

            return buf.toString().substring(0, length);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 生成令牌
     * @param user
     * @return
     */
    public static String generateToken(DmUser user) {
        //  token:PC-ed6e201becad0e79ae04178e519fd13b-29-20191011085935-0aeb0a
        StringBuilder sb = new StringBuilder();
        sb.append("token:");//统一前缀
        // 设备，暂时固定为PC
        sb.append("PC-");
        // usercode
        sb.append( MD5.getMd5(user.getPassword(),32) + "-");
        // userid
        sb.append(user.getId() + "-");
        // creation date
        sb.append(DateUtil.getCurrentDate("yyyyMMddHHmmss") + "-");
        // 6位random，暂无特定含义或用途
        sb.append(UUID.randomUUID().toString().substring(0, 6));
        return sb.toString();
    }

    /**
     * 生成验证码
     * @return
     */
    public static String generateCode(){
        String date = DateUtil.getCurrentDate("yyyyMMddHHmmss");
        return getMd5(date,4);
    }
}
