package org.example.shoppinglist.repository;

import jakarta.validation.constraints.Size;
import org.example.shoppinglist.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(@Size(min = 3, max = 20) String username);
    Optional<UserEntity> findByUsernameAndPassword(@Size(min = 3, max = 20) String username, @Size(min = 3, max = 20) String password);
}
