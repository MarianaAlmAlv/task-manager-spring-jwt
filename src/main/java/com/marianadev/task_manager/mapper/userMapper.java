package com.marianadev.task_manager.mapper;

import com.marianadev.task_manager.dto.userDto;
import com.marianadev.task_manager.entity.user;

public class userMapper 
{
    /**
     * Maps an object user(entity for database) to userDto
     * @param user
     * @return userDto
     */
    public static userDto maptoDto(user user) {
        userDto userDto = new userDto(
            user.getUsername(),
            user.getPassword(),
            user.getRol(),
            user.getName(),
            user.getLastname()
        );

        return userDto;
    }

    /**
     * Map an object userDto to user(entity for database)
     * @param dto 
     * @return user
     */
    public static user maptoEntity(userDto dto) {
         user user = new  user();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setRol(dto.getRol());
        return user;
    }
    
}
