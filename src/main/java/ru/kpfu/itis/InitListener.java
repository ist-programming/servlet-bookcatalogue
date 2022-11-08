package ru.kpfu.itis;

import ru.kpfu.itis.dao.BookDao;
import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            sce.getServletContext().setAttribute("bookDao", new BookDao(connectionProvider));
            sce.getServletContext().setAttribute("userDao", new UserDao(connectionProvider));
            sce.getServletContext().setAttribute("userService", new UserService());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
