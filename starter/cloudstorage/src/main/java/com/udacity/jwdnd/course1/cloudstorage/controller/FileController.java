package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.MinimalFile;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
@RequestMapping("/home/files")
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
    public String defaultHomeView(Model model) {
        return createFileView(model);
    }

    @PostMapping
    public String uploadFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        if (fileUpload.isEmpty()) {
            return createFileView(model);
        }
        String userName = authentication.getName();
        Integer userId = userService.getUser(userName).getUserId();
        File newFile = new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), Long.toString(fileUpload.getSize()), userId, fileUpload.getBytes());
        fileService.createFile(newFile);
        return createFileView(model);
    }

    @DeleteMapping("/{fileId}")
    public String deleteNote(@PathVariable("fileId") int fileId) {
        fileService.deleteFile(fileId);
        return "redirect:/home/files";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fileId") int fileId) throws ResponseStatusException {
        File file = fileService.getFileWithData(fileId);
        if (file == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
            ByteArrayResource resource = new ByteArrayResource(file.getData());
            return ResponseEntity.ok()
                    .contentLength(Long.parseLong(file.getSize()))
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(resource);
    }
}
