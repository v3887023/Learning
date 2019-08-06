package com.example.learning;

import android.util.Log;

import com.example.learning.java.Utils;

import java.security.MessageDigest;

/**
 * MD5 加密的工具类
 *
 * @author 小雷
 * @date 2018/7/14
 */
public class Md5Tool {
    /**
     * 将 password 与默认的当前的时间戳及字符串 "topsky" 进行拼接，进行 MD5 加密
     *
     * @param password 密码
     * @return 密文
     */
    public static String encryptWithTimeStamp(String password) {
        //复合字符串，密码+时间戳（精确到十位）+混淆字符串
        String recombination = password + '_' + (System.currentTimeMillis() / 1000L / 100L + 1) + "_topsky";
        Log.d("Command", "password: " + recombination);
        return encrypt(recombination).toUpperCase();
    }

    /**
     * 执行加密操作，输出密文
     *
     * @param content 要加密的内容
     * @return 密文
     */
    public static String encrypt(String content) {
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(content.getBytes());
            //加密
            byte[] m = md5.digest();

            for (byte b : m) {
                if ((b & 0xff) < 0x10) {
                    //一个byte为16字节，用两个char表示
                    //0x5 & 0xff = 5,0x05 & 0xff = 05 （用字符串表示便会有区别）
                    builder.append('0');
                }
                builder.append(Integer.toHexString(b & 0xff));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    /**
     * 将 password 与指定的时间戳及字符串 "topsky" 进行拼接，进行 MD5 加密
     *
     * @param password  密码
     * @param timestamp 指定的时间戳
     * @return 密文
     */
    public static String encryptWithTimeStamp(String password, String timestamp) {
        //复合字符串，密码+时间戳（精确到十位）+混淆字符串
        String recombination = password + '_' + timestamp + "_topsky";
        return encrypt(recombination);
    }

    public static void main(String[] args) {
        Utils.println("ABC: " + encrypt("ABC"));
        Utils.println("abc: " + encrypt("abc"));
    }
}
