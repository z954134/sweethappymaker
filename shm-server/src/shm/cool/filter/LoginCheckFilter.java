package shm.cool.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

    private static final String MEMBER_ID_KEY = "memberId";
    private String loginUrl;
    private String[] includes;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        doFilter(
            (HttpServletRequest) request,
            (HttpServletResponse) response,
            chain);
    }

    private void doFilter(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        checkLogin(request, response, chain);

    }
    
    private boolean isTarget(String uri) {
        
        for (String include : includes) {
            if (uri.matches(include)) {
                return true;
            }
        }
        return false;
    }

    private void checkLogin(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        String uri = request.getRequestURI();
        if (isTarget(uri)) {
            if (!isLoggedIn(request)) {
                response.sendRedirect(loginUrl);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isLoggedIn(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String memberId = (String) session.getAttribute(MEMBER_ID_KEY);
        return memberId != null;
    }

    public void init(FilterConfig config) throws ServletException {
        loginUrl = config.getInitParameter("loginUrl");
        includes = config.getInitParameter("includes").split(",");
        
    }
}
