package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Note;
import com.capgemini.cn.deemo.mapper.NoteMapper;
import com.capgemini.cn.deemo.service.NoteService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.UserUtils;
import com.capgemini.cn.deemo.vo.request.NoteEditVo;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.capgemini.cn.deemo.utils.UserUtils;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 14:25
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    /**
     * 获取标签信息
     */
    @Override
    public List<Note> listNotes() {
        return noteMapper.listNotes();
    }

    /**
     * 根据noteId,UserId获取便签信息
     */
    @Override
    public List<Note> listNoteForSearch(Long createUserId){
        return noteMapper.listNoteForSearch(createUserId);
    }

    /**
     * 更新
     */
    @Override
    public Integer updateNote(NoteEditVo note){
        return noteMapper.updateNote(note);
    }

    /**
     * 增加
     */
    @Override
    public boolean insertNote(NoteEditVo noteEditVo) {
        noteEditVo.setNoteId(IdWorker.get().nextId());
        noteEditVo.setCreateUserId(UserUtils.getCurrentUser().getUserId());
//UserUtils.getCurrentUser().getUserId()
        return noteMapper.insertNote(noteEditVo) > 0;
    }

    /**
     * 删除
     */
    @Override
    public boolean deleteNote(Long noteId) {
        return noteMapper.deleteNote(noteId) > 0;
    }
}
