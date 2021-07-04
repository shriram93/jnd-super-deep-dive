package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.MinimalFile;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.SQLException;
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

    public MinimalFile getFile(Integer userId, Integer fileId) {
        return fileMapper.getFile(userId, fileId);
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
