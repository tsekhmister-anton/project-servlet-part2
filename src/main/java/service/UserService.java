package service;

import dao.UserDao;
import entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;



    public void save(User user) {
        userDao.save(user);


    }
}
