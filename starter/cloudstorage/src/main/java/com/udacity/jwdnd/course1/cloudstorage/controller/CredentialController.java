package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/credentials")
public class CredentialController {
    @GetMapping
    public String noteView(Model model) {
        model.addAttribute("activeTab", "credentials");
        return "home";
    }
}
