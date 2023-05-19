package net.somta.juggle.console.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
 
    private static final String SECRET = "somta";
 
    /**
     * 生成token
     * @param payload 载荷,需要在token中存放的数据
     * @return
     */
    public static String generateToken(Map<String, String> payload) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);
        JWTCreator.Builder builder = JWT.create();
        //载荷,生成token中保存的信息
        payload.forEach(builder::withClaim);
        return builder.
                //签发对象
                withAudience("admin")
                //发行时间
                .withIssuedAt(new Date())
                //过期时间
                .withExpiresAt(instance.getTime())
                //加密算法+盐
                .sign(Algorithm.HMAC256(SECRET));
    }
 
    /**
     * 校验token,有异常,即为校验失败
     * @param token token数据
     * @return
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
 
    /**
     * 根据token获取载荷信息
     * @param token token数据
     * @return
     */
    public static Map<String, Claim> getPayloadByToken(String token) {
        return verify(token).getClaims();
    }
}