package service;

import dao.ProductDao;
import entity.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public void save(Product product) {
        productDao.save(product);
    }
}
