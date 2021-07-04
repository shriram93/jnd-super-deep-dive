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
    @Select("SELECT fileId, name, contentType, size, userId FROM FILES WHERE userId=#{userId}")
    List<MinimalFile> getAllFiles(Integer userId);

    @Select("SELECT fileId, name, contentType, size, userId FROM FILES WHERE fileId = #{fileId}")
    MinimalFile getFileById(Integer fileId);

    @Select("SELECT fileId, name, contentType, size, userId FROM FILES WHERE name = #{name}")
    MinimalFile getFileByName(String name);

    @Select("SELECT fileId, name, contentType, size, userId, data FROM FILES WHERE userId=#{userId} AND fileId = #{fileId}")
    File getFileWithData(Integer userId, Integer fileId);

    @Insert("INSERT INTO FILES (name, contentType, size, userId, data) VALUES(#{name}, #{contentType}, #{size}, #{userId}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    boolean deleteFile(Integer fileId);
}
