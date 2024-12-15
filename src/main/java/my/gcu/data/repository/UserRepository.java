package my.gcu.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import my.gcu.data.entity.UserEntity;

/**
 * Repository interface for performing CRUD operations on UserEntity.
 * Provides a method to find a user by their username.
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    /**
     * Default constructor for UserRepository.
     * This interface provides built-in CRUD methods.
     */

    /**
     * Find a UserEntity by the given username.
     * 
     * @param username the username of the user to find
     * @return an Optional containing the UserEntity if found, or empty if not
     */
    Optional<UserEntity> findByUsername(String username);
}
