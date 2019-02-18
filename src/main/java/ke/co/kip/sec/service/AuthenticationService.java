package ke.co.kip.sec.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class AuthenticationService {

    static final long EXPIRATION_TIME = 864_000_00; //1 day ni milli
    static final String SIGNING_KEY = "MySecretKey";
    static final String PREFIX = "Bearer";

    static public void addToken(HttpServletResponse res, String username){
        String jwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SIGNING_KEY).compact();
        res.addHeader("Authorization",PREFIX + " " + jwtToken);
        res.addHeader("Access-Control-Expose-Headers","Authorization");
    }

    // Get token from Authorization Header
    static public Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            String user = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace(PREFIX,""))
                    .getBody()
                    .getSubject();

            if (user!=null){
                return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
            }
        }
        return null;
    }

}
