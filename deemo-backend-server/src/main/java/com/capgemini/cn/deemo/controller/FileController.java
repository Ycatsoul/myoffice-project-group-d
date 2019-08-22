package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.deemo.data.domain.File;
import com.capgemini.cn.deemo.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hasaker
 * @since 2019-08-22 15:43
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件操作")
public class FileController {

    @ApiOperation(value = "获取文件列表")
    @GetMapping("/")
    public RespBean getFiles() {
        return RespBean.ok(new File());
    }

    @ApiOperation(value = "根据fileId获取文件")
    @GetMapping("/{fileId}")
    public RespBean getFile(@PathVariable Long fileId) {
        return RespBean.ok("OK");
    }
}
