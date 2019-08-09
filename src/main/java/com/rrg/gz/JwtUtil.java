package com.rrg.gz;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author ====> huangsz
 * @date ====> 2019/7/15
 */
public class JwtUtil {
    /**
     * 过期时间 1 小时 ，单位是毫秒
     */
    private static final long EXPIRE_TIME = 60 * 60 * 1000;
    /**
     * 密钥
     */
    private static final String SECRET = "1234567890qwertyuiop";

    /**
     * 生成 token, 1h 后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            // 验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String token = createToken("admin5");
        System.out.println("token = " + token);
        boolean flag = verify(token, "admin5");
        System.out.println("flag = " + flag);
        String username = getUsername("2eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjMxODAzNjMsInVzZXJuYW1lIjoiYWRtaW41In0.4PAEf8aA20tRPab7GwiUhyrnAAMatPhRFW-FskLNvkU");
        System.out.println("username = " + username);
    }
}
