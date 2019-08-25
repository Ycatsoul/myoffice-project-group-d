package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.FileType;
import com.capgemini.cn.deemo.utils.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-21 16:07
 */
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileInfoTypeMapperTest.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class FileInfoTypeMapperTest {

    @Autowired
    private FileTypeMapper fileTypeMapper;

    @Test
    public void insertFileTypeTest() {
        FileType fileType = new FileType();
        fileType.setFileTypeId(IdWorker.get().nextId());
        fileType.setFileTypeName("图片");
        fileType.setFileTypeImage("图片");
        fileType.setFileTypeSuffix(".jpg");

        assert fileTypeMapper.insertFileType(fileType) == 1;
    }

    @Test
    public void getAllFileTypeTest() {
        List<FileType> fileTypes = fileTypeMapper.getAllFileType();

        for (FileType fileType : fileTypes) {
            System.out.println(fileType);
        }

        assert fileTypes.size() > 0;
    }
}