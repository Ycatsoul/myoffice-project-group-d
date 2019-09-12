package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.data.domain.Note;
import com.capgemini.cn.deemo.service.NoteService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.NoteEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 10:50
 */
@Slf4j
@Api(value = "便签管理")
@RestController
@RequestMapping("/notes")
public class NoteController extends BaseController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ApiOperation(value = "获取Note列表")
    @PostMapping("/get")
    public RespBean getNoteList() {

        List<Note> notes =noteService.listNotes();

        /**
         * 判断是否获取成功
         */
        if (notes != null) {
            return RespBean.ok("查询成功",notes);
        }
        else {
            return RespBean.error("查询失败！");
        }
    }

    @ControllerLog(name = "新建笔记")
    @ApiOperation(value = "新建一条Note")
    @PostMapping("/set")
    public RespBean insertNote(@RequestBody NoteEditVo noteEditVo) {

        boolean result = noteService.insertNote(noteEditVo);

        /**
         * 判断是否成功
         */

        if(result){
            return RespBean.ok("添加成功");
        }
        else {
            return RespBean.error("添加失败");
        }
    }

    @ApiOperation(value = "根据createUserId获取Note详尽信息")
    @GetMapping("/{createUserId}")
    public RespBean getNote(@PathVariable Long createUserId) {

        List<Note> note = noteService.listNoteForSearch(createUserId);

        /**
         * 判断是否成功
         */
        if(note != null){
            return RespBean.ok("获取成功", note);
        }
        else {
            return RespBean.error("获取失败");
        }
    }

    @ControllerLog(name = "修改笔记")
    @ApiOperation("更新Note")
    @PutMapping("/update")
    public RespBean updateNote(@RequestBody NoteEditVo note) {

        Integer res = noteService.updateNote(note);

        return res > 0 ? RespBean.ok("更新成功") : RespBean.error("更新失败");
    }

    @ControllerLog(name = "删除笔记")
    @ApiOperation(value = "根据noteId删除一条Note")
    @DeleteMapping("/{noteId}")
    public RespBean deleteNote(@PathVariable Long noteId) {

        boolean result = noteService.deleteNote(noteId);

        /**
         * 判断是否成功
         */
        if(result){
            return RespBean.ok("删除成功");
        }
        else {
            return RespBean.error("删除失败");
        }
    }
}
