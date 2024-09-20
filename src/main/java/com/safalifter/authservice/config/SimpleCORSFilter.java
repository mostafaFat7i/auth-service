//package com.safalifter.authservice.config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class SimpleCORSFilter implements Filter {
//
//    @Value("${cors.allowed.origins}")
//    private String allowedOrigins;
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization if needed
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//        // Allow specific origins, you can also allow all origins by replacing "*" with the desired domain
//        httpResponse.setHeader("Access-Control-Allow-Origin", "http://your-frontend-domain.com");
//        // Allow all headers
//        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
//        // Allow specific HTTP methods
//        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        // Expose specific headers
//        httpResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
//        // Allow credentials if needed (if you're using cookies or HTTP Auth)
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//
//        // For preflight requests (OPTIONS), handle them and return immediately
//        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
//            httpResponse.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup if needed
//    }
//}
