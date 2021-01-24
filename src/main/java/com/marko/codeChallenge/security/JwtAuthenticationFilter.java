package com.marko.codeChallenge.security;

import com.marko.codeChallenge.model.User;
import com.marko.codeChallenge.service.CustomUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwt = getJWTFromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt)&&tokenProvider.validateToken(jwt)){
                Long userId = tokenProvider.getUserIdFromJWT(jwt);
                User userDetails = customUserDetailService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,null, Collections.emptyList()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception ex){
            logger.error("Could not set user authentication in security context",ex);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String getJWTFromRequest(HttpServletRequest request){
        String coreToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if(StringUtils.hasText(coreToken)&&coreToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            return coreToken.substring(5,coreToken.length());
        }
        return null;
    }
}
