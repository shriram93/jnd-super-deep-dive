package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.MinimalFile;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.BlobInputStreamTypeHandler;

import java.sql.Blob;
import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT fileId, name, contentType, size, userId FROM FILES")
    List<MinimalFile> getAllFiles();

    @Select("SELECT fileId, name, contentType, size, userId FROM FILES WHERE fileId = #{fileId}")
    MinimalFile getFile(Integer fileId);

    @Select("SELECT fileId, name, contentType, size, userId, data FROM FILES WHERE fileId = #{fileId}")
    File getFileWithData(Integer fileId);

    @Insert("INSERT INTO FILES (name, contentType, size, userId, data) VALUES(#{name}, #{contentType}, #{size}, #{userId}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    boolean deleteFile(Integer fileId);
}
