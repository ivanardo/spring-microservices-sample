package user.ivanardo.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import user.ivanardo.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
