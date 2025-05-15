package servlet;

import entity.LoginAttempt;
import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static servlet.ProductsServlet.onlineUsersSet;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private final Map<String, LoginAttempt> loginAttempts = new ConcurrentHashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // не удалять
        userService = (UserService) config.getServletContext().getAttribute("userService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        loginAttempts.putIfAbsent(login, new LoginAttempt());
        LoginAttempt attempt = loginAttempts.get(login);

        if (attempt.isBlockedExpired()) {
            attempt.setCountOfAttempts(new AtomicInteger(0));

        }

        if (attempt.isBlocked()) {
            resp.sendError(429);
            return;
        }


        Optional<User> optionalUser = userService.findUserByCredentials(login, password);


        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            req.getSession().setAttribute("user", optionalUser.get());
            onlineUsersSet.add(user.getLogin());
            resp.sendRedirect("/secure/products");
        } else {
            attempt.incrementAttempts();
            resp.sendRedirect("/login");
        }
    }
}
