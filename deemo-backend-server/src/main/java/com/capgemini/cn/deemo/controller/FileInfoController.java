package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.service.FileInfoService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.FileInfoAddVo;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import com.capgemini.cn.deemo.vo.response.FileInfoRespVo;
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

    @ApiOperation(value = "获取文件列表")
    @PostMapping("/list")
    public RespBean listFiles(@RequestBody FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoRespVo> respVos = fileInfoService.listFiles(fileInfoSearchVo);

        if (respVos != null && respVos.getVos().size() > 0) {
            return RespBean.ok("ok", respVos);
        }

        return RespBean.error("未找到文件");
    }

    @ApiOperation(value = "上传文件")
    @PostMapping("/")
    public RespBean uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestBody FileInfoAddVo fileInfoAddVo) {
        Integer res = fileInfoService.uploadFile(multipartFile, fileInfoAddVo);

        return res > 0 ? RespBean.ok("上传成功") : RespBean.error("上传失败!");
    }

    @ApiOperation(value = "更新一个文件的信息")
    @PutMapping("/")
    public RespBean updateFile(@RequestBody FileInfoEditVo fileInfoEditVo) {
        Integer res = fileInfoService.updateFile(fileInfoEditVo);

        return res > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

    @ApiOperation(value = "获取一个文件的详尽信息")
    @GetMapping("/{fileId}")
    public RespBean getFile(@PathVariable Long fileId) {
        RespVos<FileInfoRespVo> respVos = fileInfoService.getFile(fileId);

        if (respVos != null && respVos.getVos().size() > 0) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("未找到文件");
    }

    @ApiOperation(value = "获取回收站文件列表")
    @GetMapping("/trash")
    public RespBean listFilesInTrash(@RequestBody FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoRespVo> respVos = fileInfoService.listFilesInTrash(fileInfoSearchVo);

        if (respVos != null && respVos.getVos().size() > 0) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("未找到文件");
    }
}
