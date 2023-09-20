package com.innoventes.test.app.intercepter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.innoventes.test.app.dto.CompanyDTO;

@Component
public class WebsiteURLValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Get the CompanyDTO object from the request attributes
        CompanyDTO companyDTO = (CompanyDTO) request.getAttribute("companyDTO");

        // Apply the pattern matcher regex
        if (companyDTO != null && companyDTO.getWebSiteURL() != null) {
            String webSiteURL = companyDTO.getWebSiteURL();
            if (!webSiteURL.matches("^https://www\\.[a-zA-Z]+\\.(com|in)$")) {
                // Set webSiteURL to null if the URL is not valid
                companyDTO.setWebSiteURL(null);
            }
        }

        return true;
    }
}


