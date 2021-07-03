package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    private String createFileView(Model model) {
        model.addAttribute("activeTab", "files");
        model.addAttribute("files", fileService.getAllFiles());
        return "home";
    }

    @GetMapping
    public String defaultHomeView(Model model) {
        return createFileView(model);
    }

    @GetMapping("/files")
    public String fileView(Model model) {
        return createFileView(model);
    }

}
