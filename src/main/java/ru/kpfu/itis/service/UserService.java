package ru.kpfu.itis.service;

import ru.kpfu.itis.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {
    public void auth(User user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
    }
    public boolean isNonAnonymous(HttpServletRequest req, HttpServletResponse resp) {
        return req.getSession().getAttribute("user") != null;
    }

    public User getUser(HttpServletRequest req, HttpServletResponse res) {
        // ToDo: check for existence
        return (User) req.getSession().getAttribute("user");
    }
}
