package com.marianadev.task_manager.repository;
import org.springframework.stereotype.Repository;
import com.marianadev.task_manager.entity.user;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthRepository extends JpaRepository<user, Long> {
    Optional<user> findByUsername(String username);
}
