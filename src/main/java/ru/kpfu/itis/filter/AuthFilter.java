package ru.kpfu.itis.filter;

import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    private static final String[] securedPaths = new String[]{"/book/create"};
    private UserService userService;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean prot = false;
        for (String path : securedPaths) {
            if (path.equals(req.getRequestURI().substring(req.getContextPath().length()))) {
                prot = true;
                break;
            }
        }
        if (prot && !userService.isNonAnonymous(req, res)) {
            res.sendRedirect(req.getContextPath() + "/signin");
            return;
        } else {
            if (userService.isNonAnonymous(req, res)) {
                req.setAttribute("user", userService.getUser(req, res));
            }
            chain.doFilter(req, res);
        }
    }
}
