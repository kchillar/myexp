package com.alacriti.saml.idp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter extends GenericFilterBean {

    static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static Map<String, String> noSessionCheckUrls = new HashMap<>();

    static
    {
        final String val = "SomeValue";
        noSessionCheckUrls.put("/login", val);
        noSessionCheckUrls.put("/logout", val);
    }
    
    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI().substring(req.getContextPath().length());        
        logger.info("Requested url:{} ",url);
        if(noSessionCheckUrls.get(url) == null)
        {
            boolean valid = false;
            if(req.getCookies() !=null)
            for(Cookie ck: req.getCookies())
            {
                if(ck.getName().equals(Constants.CookieName))
                    if(ck.getValue().equals("CK123456789"))
                    {
                        valid = true;
                        break;
                    }
            }
            
            if(valid)
            {
                logger.info("Valid AUTH for accessing {}, allowing the request", url);
                chain.doFilter(request, response);
            }
            else
            {
                logger.info("Falied AUTH for accessing {}, redirecting to /login", url);
                String rUrl = req.getContextPath()+"/login";
                res.sendRedirect(rUrl);
                return;
            }                
        }
        else
        {
            chain.doFilter(request, response);
        }
    }
}