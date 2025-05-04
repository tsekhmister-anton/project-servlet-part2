package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;

@RequiredArgsConstructor
public class UserDao {

    private final ObjectMapper objectMapper;

    private final File file;



    @SneakyThrows
    public void save(User user) {
        objectMapper.writeValue(file, user);
    }
}
