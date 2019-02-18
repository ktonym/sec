package ke.co.kip.sec;

import com.fasterxml.jackson.databind.ObjectMapper;
import ke.co.kip.sec.entity.AccountCredentials;
import ke.co.kip.sec.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

  protected LoginFilter(String url, AuthenticationManager mgr) {
    super(url);
    setAuthenticationManager(mgr);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
          throws AuthenticationException, IOException, ServletException {

      AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(),AccountCredentials.class);
      return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
              creds.getUsername(),creds.getPassword(), Collections.emptyList()
      ));
  }


  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth)
          throws IOException, ServletException {
      AuthenticationService.addToken(res,auth.getName());
  }
}
