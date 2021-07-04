package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(Integer userId) {
        return noteMapper.getAllNotes(userId);
    }

    public Note getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public int createNote(Note note) {
        return noteMapper.insertNote(note);
    }

    public boolean updateNote(Note note) {
        return noteMapper.updateNote(note.getNoteId(), note.getTitle(), note.getDescription());
    }

    public boolean deleteNote(int noteId) {
        return noteMapper.deleteNote(noteId);
    }
}
