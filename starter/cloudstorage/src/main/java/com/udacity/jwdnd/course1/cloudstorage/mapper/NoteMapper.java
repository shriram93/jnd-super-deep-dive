package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note getNote(Integer noteId);

    @Insert("INSERT INTO NOTES (title, description, userId) VALUES(#{title}, #{description}, ${userId)")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Update("UPDATE NOTES SET title = #{title}, description = #{description} WHERE noteId = ${noteId}")
    boolean updateNote(@Param("noteId") Integer noteId,@Param("title") String title,@Param("description") String description);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    boolean deleteNote(Integer noteId);
}
