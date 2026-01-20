package com.marianadev.task_manager.service.implementation;
import org.springframework.stereotype.Service;
import com.marianadev.task_manager.service.TaskService;
import com.marianadev.task_manager.repository.TaskRepository;
import com.marianadev.task_manager.dto.taskDto;

import java.time.LocalDateTime;
import java.util.List;
import com.marianadev.task_manager.entity.task;
import com.marianadev.task_manager.mapper.taskMapper;

@Service
public class TaskServiceImplementation implements TaskService {
    
    private final TaskRepository taskRepository;

    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    @Override
    public taskDto createTask(taskDto taskDto) {
        taskDto.setCreatedAt(LocalDateTime.now());
        task task = taskMapper.maptoEntity(taskDto);
        task = taskRepository.save(task);
        return taskMapper.maptoDto(task);
    }

    @Override
    public taskDto getTaskById(Long id) {
        task task = taskRepository
                    .findById(id)
                    .orElseThrow(() ->  new RuntimeException("Task not found"));
        return taskMapper.maptoDto(task);
    }

    @Override
    public taskDto updateTask(taskDto taskDto) {
        task existingTask = taskRepository
                            .findById(taskDto.getId())
                            .orElseThrow(() ->  new RuntimeException("Task not found"));
        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setCompleted(taskDto.isCompleted());
        task updatedTask = taskRepository.save(existingTask);
        return taskMapper.maptoDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {   
        taskRepository.deleteById(id);
    }

    @Override
    public List<taskDto> getAllTasks() {
        List<task> tasks = taskRepository.findAll();
        return taskMapper.mapToDtoList(tasks);
    }

    @Override
    public taskDto updateStatus(Long id, boolean completed) {
        task existingTask = taskRepository
                            .findById(id)
                            .orElseThrow(() ->  new RuntimeException("Task not found"));                    
        existingTask.setCompleted(completed);
        task updatedTask = taskRepository.save(existingTask);
        return taskMapper.maptoDto(updatedTask);
    }


}
