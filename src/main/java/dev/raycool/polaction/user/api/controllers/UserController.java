package dev.raycool.polaction.user.api.controllers;

import dev.raycool.polaction.PolActionApplication;
import dev.raycool.polaction.user.api.service.UserService;
import dev.raycool.polaction.user.api.shared.dto.UserDto;
import dev.raycool.polaction.user.api.ui.model.request.UserDetailsRequestModel;
import dev.raycool.polaction.user.api.ui.model.response.UserRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);
    @Autowired
    UserService userService;

    @GetMapping(value="users")
    public String getUser() {
        return "getUser method called";
    }

    @PostMapping(value="users/add")
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        logger.info("createUser method called");
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        return "updateUser method called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser method called";
    }
}
