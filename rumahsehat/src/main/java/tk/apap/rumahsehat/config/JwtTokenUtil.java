package tk.apap.rumahsehat.config;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JwtTokenUtil implements Serializable {
	
	private String key = "YdTbooqDbh5Q9fI3H9df2SCOBfKXPn1GS/5/NPKwPA78r5FrMLhgtpP81vKRekkXoy1VDJOtn5ncFqjkQpNUE95GshXRCruDgYo5CriK+hagYd65fBJcNHgT8uIO5GHNH9a97fvAa+Wy0LeVQK5Ub9Q/UhKT3F00iPO/5YnxIZK40ntoeCs1mjTH1jEZy5B5F3RhHNDG6S5fIBrfkEbAHKlsYHAH092T3JONlw==";

	public static final long JWT_TOKEN_VALIDITY = 5*60*60;

	// @Value("${jwt.secret}")
	private SecretKey secretKey;

    @PostConstruct
    public void setUpSecretKey() {
        try {
            secretKey = Keys.hmacShaKeyFor(key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("Error generating JWT Secret Key : {}", e.getMessage());
            throw new RuntimeException("Error generating JWT Secret Key", e);
        }
    }

    public String generateToken(Authentication authentication) {
        String token = null;
        try {
            LocalDate now = LocalDate.now();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Claims clms = Jwts.claims().setSubject(authentication.getName());
            clms.put("data", new ObjectMapper().writeValueAsString(additionalInfo())); // additional info in JWT
            return Jwts.builder()
                    .setId(UUID.randomUUID().toString())
                    .setClaims(clms)
                    .setSubject(authentication.getName())
                    .setIssuer(authentication.getName())
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(now.plusDays(1).atStartOfDay(defaultZoneId).toInstant()))
                    .signWith(secretKey)
                    .compact();
        } catch (Exception e) {
            log.error("error get token" + e.getMessage());
        }
        return token;
    }

    public String getUsername(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private String getDataFromToken(String token) {
        return getClaimFromToken(token, claims -> (String) claims.get("data"));
    }

    private HashMap<String, Object> additionalInfo() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", 1);
        return data;
    }
}
