package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.MinimalCredential;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    private MinimalCredential decryptCredentialPassword(Credential credential) {
        return new MinimalCredential(
                credential.getCredentialId(),
                credential.getUrl(),
                credential.getUsername(),
                credential.getPassword(),
                encryptionService.decryptValue(credential.getPassword(), credential.getKey()),
                credential.getUserId());
    }

    public List<MinimalCredential> getAllCredentials(Integer userId) {
        List<Credential> credentials = credentialMapper.getAllCredentials(userId);
        List<MinimalCredential> minimalCredentials = new ArrayList<>();
        for (Credential credential: credentials) {
            minimalCredentials.add(decryptCredentialPassword(credential));
        }
        return minimalCredentials;
    }

    public Credential getCredential(int credentialId) {
        return credentialMapper.getCredential(credentialId);
    }

    public int createCredential(Credential credential) {
        // Generate a random encoded key
        String encodedKey = encryptionService.generateRandomKey();
        credential.setKey(encodedKey);
        // Encrypt password
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        return credentialMapper.insertCredential(credential);
    }

    public boolean updateCredential(Credential credential) {
        // Get encoded key
        String encodedKey = credentialMapper.getCredential(credential.getCredentialId()).getKey();
        credential.setKey(encodedKey);
        // Encrypt password
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        return credentialMapper.updateCredential(credential.getCredentialId(),
                credential.getUrl(),
                credential.getUsername(),
                credential.getPassword());
    }

    public boolean deleteCredential(int credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }
}
