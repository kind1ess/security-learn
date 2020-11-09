package top.kindless.securitylearn.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JwtUtil {

    private static String secretString;
    // 单位：小时
    private static int expiration;

    @Autowired
    public JwtUtil(@Value("${jwt.secret-string}") String secretString, @Value("${jwt.expiration}") int expiration) {
        JwtUtil.secretString = secretString;
        JwtUtil.expiration = expiration;
    }

    public static String createJwt(int userId) {
        SecretKey key = generateKey();
        Date exp = new Date(System.currentTimeMillis() + 1000 * 3600 * expiration);
        String jwt = Jwts.builder().claim("id", userId).setExpiration(exp).signWith(key).compact();
        return jwt;
    }

    public static SecretKey generateKey() {
        // 最少43个字符
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
        return key;
    }

    public static Claims verifyJwt(String token) {
        Claims claims;
        SecretKey key = generateKey();
        try {
            claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }

}
