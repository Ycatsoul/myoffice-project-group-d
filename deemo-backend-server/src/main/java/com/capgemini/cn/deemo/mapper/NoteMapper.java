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
    List<Note> listNoteForSearch(@Param("createUserId") Long createUserId);

    /**
     * 遍历note表信息
     */
    List<Note> listNotes();

    /**
     * 新增
     */
    Integer insertNote(@Param("noteEditVo") NoteEditVo noteEditVo);

    /**
     * 更新
     */
    Integer updateNote(@Param("noteEditVo") NoteEditVo noteEditVo);

    /**
     * 删除
     */
    Integer deleteNote(@Param("noteId") Long noteId);

}
