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

    public List<MinimalFile> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    public MinimalFile getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public File getFileWithData(Integer fileId) {
        return fileMapper.getFileWithData(fileId);
    }

    public int createFile(File file) {
        return fileMapper.insertFile(file);
    }

    public boolean deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }
}
