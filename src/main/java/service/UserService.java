package service;

import dao.UserDao;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder;



    public void save(String login, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .id(UUID.randomUUID())
                .login(login)
                .password(encodedPassword)
                .build();
        userDao.save(user);
    }

    public Optional<User> findUserByCredentials(String login, String password) {
        List<User> users = userDao.findAll();

        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user->passwordEncoder.matches(password, user.getPassword()))
                .findFirst();
    }
}
