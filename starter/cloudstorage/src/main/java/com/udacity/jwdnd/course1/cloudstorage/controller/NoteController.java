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

    private String createNoteView(Model model) {
        model.addAttribute("activeTab", "notes");
        model.addAttribute("notes", noteService.getAllNotes());
        return "home";
    }

    @GetMapping
    public String noteView(Note note,Model model) {
        return createNoteView(model);
    }

    @PostMapping
    public String createNote(Authentication authentication, Note note, Model model) {
        Integer noteId = note.getNoteId();
        // If noteId present, then update note
        if (noteId != null) {
            noteService.updateNote(note);
        } else {// If not, then insert as new note
            // Get user id from user name
            String userName = authentication.getName();
            Integer userId = userService.getUser(userName).getUserId();
            note.setUserId(userId);
            noteService.createNote(note);
        }
        return createNoteView(model);
    }

    @DeleteMapping("/{noteId}")
    public String deleteNote(@PathVariable("noteId") int noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/home/notes";
    }
}
