package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.UserService;

import java.io.File;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Users\\BTC\\IdeaProjects\\project-servlet-part2\\src\\main\\resources\\users.json");

        UserDao userDao = new UserDao(objectMapper,file);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserService userService = new UserService(userDao, passwordEncoder);

        servletContext.setAttribute("userService",userService);
        servletContext.setAttribute("passwordEncoder",passwordEncoder);
    }
}
