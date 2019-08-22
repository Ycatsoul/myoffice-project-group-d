package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.File;
import com.capgemini.cn.deemo.utils.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hasaker
 * @since 2019-08-21 16:07
 */
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileMapper.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class FileMapperTest {

    @Autowired
    private FileMapper fileMapper;

    @Test
    public void insertFileTest() {
        File file = new File();
        file.setFileId(IdWorker.get().nextId());
        file.setFileName("测试文件1");
        file.setFileOwnerId(IdWorker.get().nextId());
        file.setFilePath("/user/local/");
        file.setFileTypeId(IdWorker.get().nextId());
        file.setParentId(IdWorker.get().nextId());
        file.setRemark("备注");
        file.setIsDeleted(Boolean.FALSE);

        assert fileMapper.insertFile(file) == 1;
    }

}