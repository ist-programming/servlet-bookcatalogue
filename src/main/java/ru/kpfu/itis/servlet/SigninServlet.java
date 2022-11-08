package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.BookDao;
import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class SigninServlet  extends HttpServlet {
    private UserDao userDao;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/security/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && password != null){
            try {
                // Single resp?
                User user = userDao.getUserByUsernameAndPassword(username, password);
                if(user == null){
                    req.setAttribute("message", "Wrong pair username-password.");
                } else {
                    userService.auth(user, req, resp);
//                    resp.sendRedirect(getServletContext().getContextPath() + "/");
                }
            } catch (DbException e) {
                throw new ServletException(e);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/security/signin.jsp").forward(req, resp);
    }
}
