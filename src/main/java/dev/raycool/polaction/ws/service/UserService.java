package dev.raycool.polaction.ws.service;

import dev.raycool.polaction.ws.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);
}
