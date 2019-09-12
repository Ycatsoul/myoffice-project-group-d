package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Note;
import com.capgemini.cn.deemo.vo.request.NoteEditVo;
import com.capgemini.cn.deemo.vo.request.NoteSearchVo;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 20:05
 */
public interface NoteService {

    public List<Note> listNotes();

    public List<Note> listNoteForSearch(Long createUserId);

    public boolean insertNote(NoteEditVo noteEditVo);

    public Integer updateNote(NoteEditVo noteEditVo);

    public boolean deleteNote(Long noteId);

}
