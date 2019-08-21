package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import io.swagger.annotations.ApiOperation;
import com.capgemini.cn.deemo.data.domain.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hasaker
 * @since  2019-08-19 23:35
 *
 */
@Slf4j
@RestController
@RequestMapping("/notes")
public class NoteController extends BaseController {
    private static Map<Long, Note> notes = new ConcurrentHashMap<>();

    @ApiOperation(value = "获取Note列表")
    @GetMapping("/{start}/to/{end}")
    public List<Note> getNoteList(@PathVariable Integer start, @PathVariable Integer end) {

        return new ArrayList<>(notes.values());
    }

    @ApiOperation(value = "新建一条Note")
    @PostMapping("/")
    public String postNote(@RequestParam Note note) {
        notes.put(note.getNoteId(), note);

        return "success";
    }

    @ApiOperation(value = "根据noteId获取Note详尽信息")
    @GetMapping("/{noteId}")
    public Note getNote(@PathVariable Long noteId) {

        return notes.get(noteId);
    }

    @ApiOperation(value = "根据noteId更新一条Note")
    @PutMapping("/{noteId}")
    public String updateNote(@PathVariable Long noteId, @RequestParam Note note) {
        Note updateNote = notes.get(noteId);
        updateNote.setNoteTitle(note.getNoteTitle());
        updateNote.setNoteContent(note.getNoteContent());

        notes.put(noteId, updateNote);

        return "success";
    }

    @ApiOperation(value = "更具noteId删除一条Note")
    @DeleteMapping("/{noteId}")
    public String deleteNote(@PathVariable Long noteId) {
        notes.remove(noteId);

        return "success";
    }
}
