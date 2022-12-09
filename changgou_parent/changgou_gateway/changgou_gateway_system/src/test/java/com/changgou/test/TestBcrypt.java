package com.changgou.test;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestBcrypt {

    public static void main(String[] args) {
        //获取盐 每次的盐都是不同的
        String gensalt = BCrypt.gensalt();
        System.out.println("盐："+gensalt);

        //基于当前的盐对密码进行加密
        String saltPassword = BCrypt.hashpw("123456", gensalt);
        System.out.println("加密后的密码："+saltPassword);

        //解密
        boolean checkpw = BCrypt.checkpw("123456", saltPassword);
        System.out.println("密码校对结果："+checkpw);
    }
}
