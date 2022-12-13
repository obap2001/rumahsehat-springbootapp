package tk.apap.rumahsehat.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    private UserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (request.getServletPath().contains("/api/pasien/")
                && !request.getServletPath().equals("/api/auth")
                && !request.getServletPath().equals("/api/pasien/register")) {
            final String requestTokenHeader = request.getHeader("Authorization");
            if (StringUtils.startsWithIgnoreCase(requestTokenHeader,"Bearer ")) {
                var jwtToken = requestTokenHeader.substring(7);
                try {
                    String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                    if (!StringUtils.isEmpty(username)
                            && null == SecurityContextHolder.getContext().getAuthentication()) {
                        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
                        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities());
                            usernamePasswordAuthenticationToken
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext()
                                    .setAuthentication(usernamePasswordAuthenticationToken);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    logger.error("Unable to fetch JWT Token");
                } catch (ExpiredJwtException e) {
                    logger.error("JWT Token is expired");
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }
        }

        chain.doFilter(request, response);
    }

}