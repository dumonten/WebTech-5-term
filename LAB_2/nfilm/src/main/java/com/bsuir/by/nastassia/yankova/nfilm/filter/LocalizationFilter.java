package com.bsuir.by.nastassia.yankova.nfilm.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The LocalizationFilter class is a filter that handles localization functionality for the application.
 * It extends the HttpFilter class and implements the Filter interface.
 */
public class LocalizationFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = -6920189449807154L;
    private static final String LOCALE_RUSSIAN = "ru_RU";
    private static final String LOCALE_ENGLISH = "en_US";

    /**
     * Filters the request and response for localization purposes.
     *
     * @param request  the ServletRequest object
     * @param response the ServletResponse object
     * @param chain    the FilterChain object
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet exception occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        httpRequest.setCharacterEncoding("UTF-8");

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");

        if (httpRequest.getParameter("changeLocale") != null) {
            String languagePreference = (String) httpRequest.getSession().getAttribute("lang");
            languagePreference = (languagePreference == LOCALE_RUSSIAN) ? LOCALE_ENGLISH : LOCALE_RUSSIAN;
            httpRequest.getSession().setAttribute("lang", languagePreference);
        }
        chain.doFilter(request, response);
    }
}