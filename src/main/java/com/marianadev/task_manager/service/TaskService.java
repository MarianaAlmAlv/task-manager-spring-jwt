package com.marianadev.task_manager.service;
import java.util.List;

import com.marianadev.task_manager.dto.taskDto;

public interface TaskService{

    /**
     * Create a new task in the database.
     * - Converts TaskDTO to Task entity
     * - Saves entity using TaskRepository
     * - Converts saved entity back to DTO
     * - Returns the created TaskDTO
     * @param taskDto
     * @return TaskDTO with Id
     */
    taskDto createTask(taskDto taskDto);

    /**
     * Retrieve a single task by ID.
     * - Throws exception if task not found
     * - Returns TaskDTO
     * @param id
     * @return TaskDTO
     */
    taskDto getTaskById(Long id);

    /**
     * Update an existing task.
     * - Fetches task by ID
     * - Updates fields from TaskDTO
     * - Saves updated task
     * - Returns updated TaskDTO
     * @param taskDto
     * @return TaskDTO
     */
    taskDto updateTask(taskDto taskDto);

    /**
     * Delete a task by ID.
     * - Checks if task exists
     * - Deletes the task from the database
     * @param id
     */
    void deleteTask(Long id);

    /**
     * Retrieve all tasks from the database.
     * - Converts entities to DTOs
     * - Returns a list of TaskDTOs
     * @return List<taskDto>
     */
    List<taskDto> getAllTasks();

    /**
    * Update the status of an existing task.
     * - Fetches task by ID
     * - Updates completed status from TaskDTO
     * - Saves updated task
     * - Returns updated TaskDTO
     * @param id
     * @param completed true or false
     * @return taskDto
     */
    taskDto updateStatus(Long id, boolean completed);

}
