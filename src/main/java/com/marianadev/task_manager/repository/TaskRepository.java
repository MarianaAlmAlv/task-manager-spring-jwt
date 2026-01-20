package com.marianadev.task_manager.repository;
import com.marianadev.task_manager.entity.task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<task, Long> {
}
