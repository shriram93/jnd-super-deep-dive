package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/credentials")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @GetMapping
    public String credentialView(Authentication authentication, Credential credential, Model model) {
        model.addAttribute("activeTab", "credentials");
        model.addAttribute("credentials", credentialService.getAllCredentials(userService.getUserId(authentication)));
        return "home";
    }

    @PostMapping
    public String createCredential(Authentication authentication, Credential credential) {
        Integer credentialId = credential.getCredentialId();
        // If credentialId present, then update credential
        if (credentialId != null) {
            credentialService.updateCredential(credential);
        } else {// If not, then insert as new credential
            credential.setUserId(userService.getUserId(authentication));
            credentialService.createCredential(credential);
        }
        return "redirect:/home/credentials" ;
    }

    @DeleteMapping("/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") int credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/home/credentials";
    }
}
