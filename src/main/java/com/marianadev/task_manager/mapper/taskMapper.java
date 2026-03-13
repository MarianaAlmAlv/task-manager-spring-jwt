package com.marianadev.task_manager.mapper;
import com.marianadev.task_manager.dto.taskDto;
import com.marianadev.task_manager.entity.task;
import java.util.ArrayList;
import java.util.List;

public class taskMapper {

    public static taskDto maptoDto(task task) {
        taskDto dto = new taskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.isCompleted(),
            task.getCreated_At()
        );
        return dto;
    }

    public static task maptoEntity(taskDto dto) {
        task t = new task();
        if (dto.getId() != null && dto.getId() > 0) {
            t.setId(dto.getId());
        }
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setCompleted(dto.isCompleted());
        t.setCreated_At(dto.getCreatedAt());
        return t;
    }

    public static List<taskDto> mapToDtoList(List<task> tasks) {
        List<taskDto> dtoList = new ArrayList<>();
        for (task task : tasks) {
            dtoList.add(maptoDto(task));
        }
        return dtoList;
    }

}
