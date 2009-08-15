package shm.controller.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

public class LoginCheckFilter implements Filter {

    private static final String MEMBER_ID_KEY = "memberId";
    private String loginUrl;

    @Override
    public void destroy() {
    }

    @Override
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

        if (isLoggedIn(request)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginUrl);
        }
    }

    private boolean isLoggedIn(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String memberId = (String) session.getAttribute(MEMBER_ID_KEY);
        return memberId != null;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        loginUrl = config.getInitParameter("loginUrl");
    }
}
