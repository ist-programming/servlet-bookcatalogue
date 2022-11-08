package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.BookDao;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/list")
public class BookListServlet extends HttpServlet {
    private BookDao bookDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookDao = (BookDao) getServletContext().getAttribute("bookDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("bookCount", bookDao.getCount());
            req.setAttribute("books", bookDao.getPage(1, 10));
        } catch (DbException e) {
            throw new ServletException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/books/list.jsp").forward(req, resp);
    }
}
