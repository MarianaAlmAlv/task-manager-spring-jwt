package com.marianadev.task_manager.dto;

import com.marianadev.task_manager.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDto {
    private String username;
    private String password;
    private Role rol;
    private String name;
    private String lastname;
}
