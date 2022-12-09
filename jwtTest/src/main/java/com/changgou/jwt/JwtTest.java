package com.changgou.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTest {

    public static void main(String[] args) {

        long currentTimeMillis = System.currentTimeMillis()+3000;
        Date date = new Date(currentTimeMillis);

        // 生成JWT令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("66")//设置jwt唯一编号
                .setSubject("安徽大学")//设置jwt主题
                .setIssuedAt(new Date())// 设置签发日期
                .setExpiration(date)// 设置过期时间
                .claim("roles","admin")//添加自定义内容
                .signWith(SignatureAlgorithm.HS256, "ahu university");

        //生成jwt
        String jwtToken = jwtBuilder.compact();
        System.out.println(jwtToken);

        //解析jwt，得到内部数据
        Claims claims = Jwts.parser().setSigningKey("ahu university").parseClaimsJws(jwtToken).getBody();
        System.out.println(claims);
    }
}
