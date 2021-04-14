package dev.raycool.polaction.controller;

import dev.raycool.polaction.ws.ui.model.request.UserDetailsRequestModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUser() {
        return "getUser method called";
    }

    @PostMapping
    public String createUser(@RequestBody UserDetailsRequestModel userDetails) {
        return "createUser method called";
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
