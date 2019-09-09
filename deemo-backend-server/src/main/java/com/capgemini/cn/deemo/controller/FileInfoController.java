package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.FileInfoService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.DeleteVo;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import com.capgemini.cn.deemo.vo.response.FileInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hasaker
 * @since 2019-08-22 15:43
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件操作")
public class FileInfoController {

    private final FileInfoService fileInfoService;

    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @ApiOperation(value = "获取一个文件的详尽信息")
    @GetMapping("/{fileId}")
    public RespBean getFile(@PathVariable Long fileId) {
        RespVos<FileInfoVo> respVos = fileInfoService.getFile(fileId);

        if (respVos != null && respVos.getVos().size() > 0) {
            return RespBean.ok("成功", respVos);
        }

        return RespBean.error("未找到文件");
    }

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public RespBean uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        boolean res = fileInfoService.uploadFile(multipartFile);

        return res ? RespBean.ok("上传成功") : RespBean.error("上传失败!");
    }

    @ApiOperation(value = "插入文件上传记录")
    @PostMapping("/insert")
    public RespBean insertFile(@RequestBody FileInfoEditVo fileInfoEditVo) {
        int res = fileInfoService.insertFile(fileInfoEditVo);

        return res > 0 ? RespBean.ok("插入成功!") : RespBean.error("插入失败!");
    }

    @ApiOperation(value = "更新一个文件的信息")
    @PutMapping("/update")
    public RespBean updateFile(@RequestBody FileInfoEditVo fileInfoEditVo) {
        int res = fileInfoService.updateFile(fileInfoEditVo);

        return res > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

    @ApiOperation(value = "获取文件列表")
    @PostMapping("/list")
    public RespBean listFiles(@RequestBody FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoVo> respVos = fileInfoService.listFiles(fileInfoSearchVo);

        if (respVos != null && respVos.getVos().size() > 0) {
            return RespBean.ok("成功", respVos);
        }

        return RespBean.error("未找到文件");
    }

    @ApiOperation(value = "获取回收站文件列表")
    @PostMapping("/trash")
    public RespBean listFilesInTrash(@RequestBody FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoVo> respVos = fileInfoService.listFilesInTrash(fileInfoSearchVo);

        if (respVos != null && respVos.getVos().size() > 0) {
            return RespBean.ok("成功", respVos);
        }

        return RespBean.error("未找到文件");
    }

    @ApiOperation(value = "将文件放入回收站")
    @PostMapping("/delete")
    public RespBean putFileToTrash(@RequestBody DeleteVo deleteVo) {
        int res = fileInfoService.putFilesToTrash(deleteVo);

        return res > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败!");
    }

    @ApiOperation(value = "从回收站取回文件")
    @PutMapping("/restore")
    public RespBean restoreFileFromTrash(@RequestBody DeleteVo deleteVo) {
        int res = fileInfoService.restoreFilesFromTrash(deleteVo);

        return res > 0 ? RespBean.ok("还原成功!") : RespBean.error("还原失败!");
    }

    @ApiOperation(value = "将文件从回收站中彻底删除")
    @PostMapping("/destroy")
    public RespBean deleteFilesInTrash(@RequestBody DeleteVo deleteVo) {
        int res = fileInfoService.deleteFilesFromTrash(deleteVo);

        return res > 0 ? RespBean.ok("彻底删除成功!") : RespBean.error("彻底删除失败!");
    }
}
