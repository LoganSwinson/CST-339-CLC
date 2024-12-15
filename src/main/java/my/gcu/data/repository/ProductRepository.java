package my.gcu.data.repository;

import org.springframework.data.repository.CrudRepository;

import my.gcu.data.entity.ProductEntity;

/**
 * Repository interface for performing CRUD operations on ProductEntity.
 */
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    /**
     * Default constructor for ProductRepository.
     * This interface provides built-in CRUD methods.
     */
    // No methods needed as CrudRepository provides default implementations.
}
