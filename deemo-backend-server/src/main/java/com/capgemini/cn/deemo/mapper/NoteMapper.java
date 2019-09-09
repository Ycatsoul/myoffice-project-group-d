package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Note;

import com.capgemini.cn.deemo.vo.request.NoteEditVo;
import com.capgemini.cn.deemo.vo.request.NoteSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/21 16:32
 */
@Service
public interface NoteMapper {

    /**
     * 根据noteId搜索和根据userId搜素
     */
    List<Note> listNoteForSearch(@Param("note") NoteSearchVo noteSearchVo);

    /**
     * 遍历note表信息
     */
    List<Note> listNotes();

    Integer insertNote(@Param("noteEditVo") NoteEditVo noteEditVo);

    Integer updateNote(@Param("noteEditVo") NoteEditVo noteEditVo);

    Integer deleteNote(@Param("noteId") Long noteId);

}
