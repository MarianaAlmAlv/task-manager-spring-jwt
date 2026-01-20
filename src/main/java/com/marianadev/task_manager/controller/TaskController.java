package com.marianadev.task_manager.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.marianadev.task_manager.service.TaskService;
import com.marianadev.task_manager.dto.taskDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/tasks")
public class TaskController  {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService=taskService;
    }

    /**
     * Create a new task.
     * Auth required: Bearer JWT
     * @param taskDto (title, description, completed, createdAt)
     * @return TaskDTO with ID and creation date
     */
    @PostMapping()
    public ResponseEntity<taskDto> createTask(@RequestBody taskDto taskDto){
        taskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.ok(createdTask);  
    }

    /**
     * Get a task by Id.
     * Auth required: Bearer JWT
     * @param id
     * @return TaskDTO
     * if tasks not found "Task not found" 
     */
    @GetMapping("/{id}")
    public ResponseEntity<taskDto> getTaskById(@PathVariable Long id){
        taskDto taskDto = taskService.getTaskById(id);
        return ResponseEntity.ok(taskDto);
    }

    /**
     * Get all tasks.
     * Auth required: Bearer JWT
     * @return List of TaskDTO
     */
    @GetMapping()
     public ResponseEntity<List<taskDto>> getTasks(){
        List<taskDto> allTask = taskService.getAllTasks();
        return ResponseEntity.ok(allTask);
    }

    /**
     * Update an existing task.
     * Auth required: Bearer JWT,
     * @param taskDto TaskDTO with modifications
     * @return Updated TaskDTO
     * if tasks not found "Task not found"
     */
    @PutMapping()
    public ResponseEntity<taskDto> updateTask( @RequestBody taskDto taskDto){
        taskDto updatedTask = taskService.updateTask(taskDto);
        return ResponseEntity.ok(updatedTask);
    }


   /**
    * Update the status of a Task
    * Auth required: Bearer JWT
    * @param id
    * @param completed boolean status
    * @return Updated TaskDTO
    * if tasks not found "Task not found"
    */
    @PutMapping("/{id}")
    public ResponseEntity<taskDto> updateTaskStatus(@PathVariable Long id, @RequestParam boolean completed){
        taskDto updatedTask = taskService.updateStatus(id, completed);
        return ResponseEntity.ok(updatedTask);
    }
    
    /**
     * Delete a task.
     * Auth required: Bearer JWT
     * @param id
     * @return No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


}
