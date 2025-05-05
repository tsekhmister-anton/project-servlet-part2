package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;
import entity.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductDao {
    private final ObjectMapper objectMapper;
    private final File file;

    @SneakyThrows
    public void save(Product product) {
        List<Product> list = findAll();
        list.add(product);
        objectMapper.writeValue(file, list);
    }

    @SneakyThrows
    public void saveAll(List<Product> products) {
        objectMapper.writeValue(file, products);
    }

    @SneakyThrows
    public List<Product> findAll() {
        return objectMapper.readValue(file, new TypeReference<List<Product>>() {
        });
    }
}
