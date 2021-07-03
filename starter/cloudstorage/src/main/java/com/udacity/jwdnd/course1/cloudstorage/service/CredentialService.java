package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    private void encryptCredentialPassword(Credential credential) {
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(encryptedPassword);
    }

    private void decryptCredentialPassword(Credential credential) {
        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(decryptedPassword);
        // After decrypting password, set key as null
        credential.setKey(null);
    }

    public List<Credential> getAllCredentials() {
        List<Credential> credentials = credentialMapper.getAllCredentials();
        for (Credential credential: credentials) {
            decryptCredentialPassword(credential);
        }
         return credentials;
    }

    public Credential getCredential(int credentialId) {
        Credential credential = credentialMapper.getCredential(credentialId);
        decryptCredentialPassword(credential);
        return credential;
    }

    public int createCredential(Credential credential) {
        // Generate a random encoded key
        String encodedKey = encryptionService.generateRandomKey();
        credential.setKey(encodedKey);
        // Encrypt password
        encryptCredentialPassword(credential);
        return credentialMapper.insertCredential(credential);
    }

    public boolean updateCredential(Credential credential) {
        // Get encoded key
        String encodedKey = credentialMapper.getCredential(credential.getCredentialId()).getKey();
        credential.setKey(encodedKey);
        // Encrypt password
        encryptCredentialPassword(credential);
        return credentialMapper.updateCredential(credential.getCredentialId(),
                credential.getUrl(),
                credential.getUsername(),
                credential.getPassword());
    }

    public boolean deleteCredential(int credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }
}
