package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/home")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    private String createFileView(Model model) {
        model.addAttribute("activeTab", "files");
        model.addAttribute("files", fileService.getAllFiles());
        return "home";
    }

    @GetMapping
    public String defaultHomeView(File file, Model model) {
        return createFileView(model);
    }

    @GetMapping("/files")
    public String fileView(File file, Model model) {
        return createFileView(model);
    }

    @PostMapping("/files")
    public String uploadFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile file, Model model) throws IOException {
        String userName = authentication.getName();
        Integer userId = userService.getUser(userName).getUserId();
        File newFile = new File(null, file.getName(), file.getContentType(), Long.toString(file.getSize()), userId, file.getBytes());
        fileService.createFile(newFile);
        return createFileView(model);
    }

}
