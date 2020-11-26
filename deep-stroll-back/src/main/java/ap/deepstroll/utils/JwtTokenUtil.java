package ap.deepstroll.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author mxf
 */
@Component
public class JwtTokenUtil {
    
    private static final String CLAIM_KEY_ID = "id";
    private static final String CLAIM_KEY_ROLE = "role";
    
    @Value("${jwt.secret}")
    private String secret;

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getIdFromToken(String token) {
        String id;
        try {
            final Claims claims = getClaimsFromToken(token);
            id = claims.get(CLAIM_KEY_ID).toString();
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

    public String[] getIdAndRoleFromToken(String token) {
        String id, role;
        try {
            final Claims claims = getClaimsFromToken(token);
            id = claims.get(CLAIM_KEY_ID).toString();
            role = claims.get(CLAIM_KEY_ROLE).toString();
        } catch (Exception e) {
            id = null;
            role = null;
        }
        return new String[]{id, role};
    }

    public String generateToken(String id, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, id);
        claims.put(CLAIM_KEY_ROLE, role);
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
