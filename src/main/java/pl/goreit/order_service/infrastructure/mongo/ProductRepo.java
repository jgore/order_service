package pl.goreit.order_service.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.goreit.order_service.CategoryName;
import pl.goreit.order_service.domain.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
//TODO refactor to have order service
public interface ProductRepo extends MongoRepository<Product, String> {

    Optional<Product> findByTitle(String title);

    List<Product> findByCategoryName(CategoryName categoryName);
}
