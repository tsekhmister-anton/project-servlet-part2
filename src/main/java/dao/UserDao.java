package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

public class UserDao {
    private ObjectMapper objectMapper;
    private File file;

    @SneakyThrows
    public void save(User user) {
        objectMapper.writeValue(file, user);


    }
}
