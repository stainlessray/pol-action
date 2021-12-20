package dev.raycool.polaction.user.api.service;

import dev.raycool.polaction.user.api.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);
}
