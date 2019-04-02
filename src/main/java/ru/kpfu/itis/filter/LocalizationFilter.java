package ru.kpfu.itis.filter;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@WebFilter("*")
public class LocalizationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String lang = request.getParameter("lang");

        if(lang != null){
            Cookie cookie = new Cookie("locale", lang);
            cookie.setMaxAge(60 * 60 * 60);
            response.addCookie(cookie);
        }else{
            lang = "En";
            if (request.getCookies()!=null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("locale")) {
                        lang = cookie.getValue();
                    }
                }
            }
        }


        Map<String, String> locale = (Map<String, String>) request.getServletContext().getAttribute("localeRu"/* + lang*/);
        request.setAttribute("locale", locale);
        new ModelMap().addAttribute("locale", locale);
//        System.out.println(locale.get("quote.author"));

        System.out.println("FILTER FROM LOCALIZATION!!! UUUH");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
