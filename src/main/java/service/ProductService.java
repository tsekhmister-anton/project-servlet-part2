package service;

import dao.ProductDao;
import entity.Product;
import entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public void save(Product product) {
        productDao.save(product);
    }

    public List<Product> findAllByUserId(UUID userId) {
        return productDao.findAll().stream()
                .filter(product -> product.getUserId().equals(userId))
                .toList();
    }

    public void removeById(UUID productId) {
        List<Product> products = productDao.findAll();
        List<Product> productsWithoutDeletedElement = products.stream().filter(product -> !product.getId().equals(productId)).toList();
        productDao.saveAll(productsWithoutDeletedElement);
    }
}
