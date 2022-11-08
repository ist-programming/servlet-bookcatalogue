package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.BookDao;
import ru.kpfu.itis.entity.Book;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/detail")
public class BookDetailServlet extends HttpServlet {
    private BookDao bookDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookDao = (BookDao) getServletContext().getAttribute("bookDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            if(id == null){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Bad request. No id has been provided.");
            }
            Book book = bookDao.getDetail(Integer.parseInt(id));
            if(book == null){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notfound.jsp").forward(req, resp);
            }
            req.setAttribute("book", book);
            getServletContext().getRequestDispatcher("/WEB-INF/view/books/detail.jsp").forward(req, resp);
        } catch (DbException e) {
            throw new ServletException(e);
        }
    }
}
