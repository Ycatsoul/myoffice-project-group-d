package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.FileInfo;
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
@SpringBootTest(classes = FileInfoMapper.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class FileInfoMapperTest {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Test
    public void insertFileTest() {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(IdWorker.get().nextId());
        fileInfo.setFileName("测试文件1");
        fileInfo.setFileOwnerId(IdWorker.get().nextId());
        fileInfo.setFilePath("/user/local/");
        fileInfo.setFileTypeId(IdWorker.get().nextId());
        fileInfo.setParentId(IdWorker.get().nextId());
        fileInfo.setRemark("备注");
        fileInfo.setIsDeleted(Boolean.FALSE);

        assert fileInfoMapper.insertFile(fileInfo) == 1;
    }

}