package com.udacity.jwdnd.course1.cloudstorage.model;

public class MinimalFile {
    private Integer fileId;
    private String name;
    private String contentType;
    private String size;
    private Integer userId;
    public MinimalFile(Integer fileId, String name, String contentType, String size, Integer userId) {
        this.fileId = fileId;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
