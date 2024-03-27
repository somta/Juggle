package net.somta.juggle.common.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import net.somta.juggle.common.identity.IdentityVO;
import org.apache.commons.lang3.StringUtils;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author husong
 */
public class JwtUtil {

    public static final String TOKEN_HEADER_KEY = "Authorization";
    public static final String OPEN_API_HEADER_KEY = "Juggle-Token";
    public static final String OPEN_API_PARAM_KEY = "juggleToken";
    private static final Key SIGN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
 
    /**
     * 生成token
     * @param payload 载荷,需要在token中存放的数据
     * @return
     */
    public static String generateToken(Map<String, Object> payload) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 2);
        Date expireDate = instance.getTime();
        return Jwts.builder()
                .setClaims(payload)
                .setIssuer("juggle")
                //发行时间
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(expireDate)
                .signWith(SIGN_KEY)
                .compact();
    }
 
    /**
     * 校验token是否过期
     * @param token token数据
     * @return ture:过期  false：未过期
     */
    public static Boolean verifyExpired(String token) {
        if(StringUtils.isEmpty(token)){
            return true;
        }
        try {
            Claims claimsJws = getClaimsJws(token);
            return claimsJws.getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 解析token
     * @param token token数据
     * @return
     */
    public static IdentityVO parseToken(String token) {
        Claims claimsJws = getClaimsJws(token);
        Long userId = Long.valueOf((String) claimsJws.get(IdentityVO.USER_ID));
        IdentityVO userTokenVO = new IdentityVO(userId);
        return userTokenVO;
    }

    /**
     * 获取ClaimsJws
     * @param token token数据
     * @return
     */
    private static Claims getClaimsJws(String token) {
        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder();
        //设置签名的密钥
        jwtParserBuilder.setSigningKey(SIGN_KEY);
        //解析内容,获得payload
        return jwtParserBuilder.build().parseClaimsJws(token).getBody();
    }

}