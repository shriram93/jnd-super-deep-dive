package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getAllCredentials() {
        return credentialMapper.getAllCredentials();
    }

    public Credential getCredential(int credentialId) {
        return credentialMapper.getCredential(credentialId);
    }

    public int createCredential(Credential credential) {
        return credentialMapper.insertCredential(credential);
    }

    public boolean updateCredential(Credential credential) {
        return credentialMapper.updateCredential(credential.getCredentialId(),
                credential.getUrl(),
                credential.getUsername(),
                credential.getKey(),
                credential.getPassword());
    }

    public boolean deleteCredential(int credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }
}
