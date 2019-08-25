package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.data.domain.FileInfo;
import com.capgemini.cn.deemo.mapper.FileInfoMapper;
import com.capgemini.cn.deemo.service.FileInfoService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 15:43
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件操作")
public class FileInfoController {

    private FileInfoService fileInfoService;

    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @ApiOperation(value = "获取文件列表")
    @GetMapping("/")
    public RespBean getFiles() {
        List<FileInfo> fileInfos = fileInfoService.getFiles(new FileInfoSearchVo());

        return RespBean.success(fileInfos);
    }

    @ApiOperation(value = "获取一个文件的详尽信息")
    @GetMapping("/{fileId}")
    public RespBean getFile(@PathVariable Long fileId) {
        FileInfo fileInfo = fileInfoService.getFile(fileId);

        return RespBean.success("success", fileInfo);
    }

//    @ApiOperation(value = "上传一个文件")
//    @PostMapping("/")
//    public RespBean uploadFile(@RequestParam )
}
