package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    private String createNoteView(Authentication authentication, Model model) {
        model.addAttribute("activeTab", "notes");
        model.addAttribute("notes", noteService.getAllNotes(userService.getUserId(authentication)));
        return "home";
    }

    @GetMapping
    public String noteView(Authentication authentication, Note note,Model model) {
       return createNoteView(authentication, model);
    }

    @PostMapping
    public String createNote(Authentication authentication, Note note, Model model) {
        Integer noteId = note.getNoteId();
        // If noteId present, then update note
        if (noteId != null) {
            noteService.updateNote(note);
            model.addAttribute("noteActionSuccess", "Note updated successfully.");
        } else {// If not, then insert as new note
            note.setUserId(userService.getUserId(authentication));
            noteService.createNote(note);
            model.addAttribute("noteActionSuccess", "Note added successfully.");
        }
        return createNoteView(authentication, model);
    }

    @DeleteMapping("/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable("noteId") int noteId, Model model) {
        noteService.deleteNote(noteId);
        model.addAttribute("noteActionSuccess", "Note deleted successfully.");
        return createNoteView(authentication, model);
    }
}
