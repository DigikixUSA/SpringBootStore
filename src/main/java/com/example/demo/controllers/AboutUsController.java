package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *  This is a controller for the About Us(about.html) page, with a link back to the main page
 *
 *
 *
 */

@Controller
public class AboutUsController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
