package com.marianadev.task_manager.service;
import com.marianadev.task_manager.dto.userDto;
import com.marianadev.task_manager.security.model.AuthResponse;


public interface AuthService {

    /**
     * Authenticates a user with username and password.
     * Steps:
     * 1. Load user details from the database
     * 2. Verify password using PasswordEncoder
     * 3. Generate JWT token if credentials are valid
     * Returns:
     * - String : JWT token for authentication
     * Exceptions:
     * - Throws RuntimeException or custom exception if credentials are invalid
     * Notes:
     * - This method does not store session; stateless authentication is handled via JWT.
     * @param userDto (username, password)
     * @return AuthResponse with JWT token for authentication
     */
    AuthResponse login(userDto userDto);

    /**
     * Registers a new user in the system.
     * Steps:
     * 1. Build the user entity for database from userDto
     *     -Encode the password securely using PasswordEncoder
     *     -Set Rol from Enum Role
     * 2. Save the new User entity in the database
     * 4. Generate a JWT token for the user
     * Notes:
     * - Database not allow duplicate usernames.
     * - Password is stored securely in encoded form.
     * @param userDto (username, password, name, lastname)
     * @return AuthResponse with JWT token for immediate authentication
     */
    AuthResponse register(userDto userDto);
}
