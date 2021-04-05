package dev.raycool.polaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestThController {

    /**
     * @param name a name
     * Performing a GET request against "host:port/testth" will provide an html response with a default name
     *  Testing the parameter passing requires the GET request be formatted as follows
     *             "host:port/testth?name={anynameyouwant}" (without the brackets)
     */
    @GetMapping(value = "/testth")
    public String showTestPage(@RequestParam(
            name="name", required=false, defaultValue="Ray")
            String name, Model model)
    {
            model.addAttribute("name", name);
            return "testth";
    }
}
