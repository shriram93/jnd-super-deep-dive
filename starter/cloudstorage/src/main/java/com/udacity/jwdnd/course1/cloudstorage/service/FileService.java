package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.MinimalFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<MinimalFile> getAllFiles(Integer userId) {
        return fileMapper.getAllFiles(userId);
    }

    public MinimalFile getFile(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    public Boolean checkFileNameAlreadyExists(String name) {
        MinimalFile file = fileMapper.getFileByName(name);
        return file != null;
    }

    public File getFileWithData(Integer userId, Integer fileId) {
        return fileMapper.getFileWithData(userId, fileId);
    }

    public int createFile(File file) {
        return fileMapper.insertFile(file);
    }

    public boolean deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }
}
