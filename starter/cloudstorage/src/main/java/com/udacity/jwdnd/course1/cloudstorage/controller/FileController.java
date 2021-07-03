package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FileController {

    private String createHomeView(Model model) {
        model.addAttribute("activeTab", "files");
        return "home";
    }

    @GetMapping("/home/files")
    public String fileView(Model model) {
        return createHomeView(model);
    }

    @GetMapping("/home")
    public String defaultHomeView(Model model) {
        return createHomeView(model);
    }

}
