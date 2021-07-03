package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/credentials")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    private String createCredentialView(Model model) {
        model.addAttribute("activeTab", "credentials");
        model.addAttribute("notes", credentialService.getAllCredentials());
        return "home";
    }

    @GetMapping
    public String credentialView(Credential credential, Model model) {
        return createCredentialView(model);
    }

    @PostMapping
    public String createCredential(Authentication authentication, Credential credential, Model model) {
        Integer credentialId = credential.getCredentialId();
        // If credentialId present, then update credential
        if (credentialId != null) {
            credentialService.updateCredential(credential);
        } else {// If not, then insert as new credential
            // Get user id from user name
            String userName = authentication.getName();
            Integer userId = userService.getUser(userName).getUserId();
            credential.setUserId(userId);
            credentialService.createCredential(credential);
        }
        return createCredentialView(model);
    }
}
