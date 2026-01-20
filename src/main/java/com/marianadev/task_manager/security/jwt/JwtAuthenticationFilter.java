package com.marianadev.task_manager.security.jwt;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;


/**
 * JwtAuthenticationFilter intercepts incoming HTTP requests to secure endpoints.
 * Responsibilities:
 * Extract JWT from Authorization header
 * Validate the token
 * Set authentication in SecurityContext if token is valid
 * Pass request along the filter chain
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    /**
     * Intercepts each HTTP request to a secured endpoint.
     *  Steps:
     * 1. Extract JWT from the Authorization header
     * 2. Validate the token using JwtService
     * 3. If valid, create an Authentication object and store it in SecurityContext
     * 4. Continue the filter chain (pass the request to controller/service)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        final String username;
        if(token == null)  {
            filterChain.doFilter(request, response);
            return;
        }
        username= jwtService.getUserNameFromToken(token);
        if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= userDetailsService.loadUserByUsername(username);
            if(jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Verify if Authorization is used and Get token from request 
     * Expected header format: "Authorization: Bearer <token>"
     * @param request HttpServletRequest
     * @return Token removing Bearer word
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
