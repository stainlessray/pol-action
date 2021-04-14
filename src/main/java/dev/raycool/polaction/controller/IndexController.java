package dev.raycool.polaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }
}
