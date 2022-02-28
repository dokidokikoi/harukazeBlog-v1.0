package com.harukaze.front.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class JwtUtils {

    //失效时间单位秒
    private static long expire = 60 * 24 * 60 * 7;
    private static String secret = "hgtrtsex%2gdfg*@sdas";
    private static String header = "Authorization";

    public static long getExpire() {
        return expire;
    }

    public static String getHeader() {
        return header;
    }

    // 生成jwt
    public static String generateToken(String username) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)// 7天過期
                .signWith(SignatureAlgorithm.HS512, "hgtrtsex%2gdfg*@sdas")
                .compact();
    }

    // 解析jwt
    public static Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // jwt是否过期
    public static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}
