package dev.raycool.polaction.controllers;

import dev.raycool.polaction.PolActionApplication;
import dev.raycool.polaction.ws.service.UserService;
import dev.raycool.polaction.ws.shared.dto.UserDto;
import dev.raycool.polaction.ws.ui.model.request.UserDetailsRequestModel;
import dev.raycool.polaction.ws.ui.model.response.UserRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // http:localhost:8080/users
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(PolActionApplication.class);

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "getUser method called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        logger.info("createUser method called");
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
