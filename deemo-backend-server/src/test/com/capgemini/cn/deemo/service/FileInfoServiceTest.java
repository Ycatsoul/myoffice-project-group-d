package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.service.impl.FileInfoServiceImpl;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import com.capgemini.cn.deemo.vo.response.FileInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hasaker
 * @since 2019/8/28 14:28
 */
@Slf4j
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileInfoServiceImpl.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class FileInfoServiceTest {

    @Autowired
    private FileInfoServiceImpl fileInfoServiceImpl;

    @Test
    public void getFileTest() {
        RespVos<FileInfoVo> res = fileInfoServiceImpl.getFile(2500924329164800L);

        assert res.getSize() > 0;
        log.info(res.getSize().toString());
        log.info(res.getVos().toString());
    }

    @Test
    public void listFilesTest() {
        FileInfoSearchVo fileInfoSearchVo = new FileInfoSearchVo();
        fileInfoSearchVo.setFileName("rar");

        RespVos<FileInfoVo> res = fileInfoServiceImpl.listFiles(fileInfoSearchVo);
        log.info(fileInfoSearchVo.toString());
        log.info(res.toString());
    }
}
