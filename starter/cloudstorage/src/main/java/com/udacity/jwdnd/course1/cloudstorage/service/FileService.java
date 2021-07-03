package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public int createFile(File file) {
        return fileMapper.insertFile(file);
    }

    public boolean deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }
}
