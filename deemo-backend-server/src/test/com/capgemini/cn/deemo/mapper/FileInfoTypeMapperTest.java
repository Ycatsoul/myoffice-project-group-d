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
        String[] fileTypeNames = new String[]{
                "Excel文档", "Excel文档", "其它", "图片", "PDF文档", "图片",
                "PPT文档", "纯文本文档", "Word文档", "Word文档",
                "压缩包", "压缩包", "压缩包", "文件夹"
        };
        String[] fileTypeImages = new String[]{
                "classpath:/file_type_icons/excel.png",
                "classpath:/file_type_icons/excel.png",
                "classpath:/file_type_icons/file.png",
                "classpath:/file_type_icons/jpg.png",
                "classpath:/file_type_icons/pdf.png",
                "classpath:/file_type_icons/png.png",
                "classpath:/file_type_icons/ppt.png",
                "classpath:/file_type_icons/txt.png",
                "classpath:/file_type_icons/word.png",
                "classpath:/file_type_icons/word.png",
                "classpath:/file_type_icons/zip.png",
                "classpath:/file_type_icons/zip.png",
                "classpath:/file_type_icons/zip.png",
                "classpath:/file_type_icons/folder.png"
        };
        String[] fileTypeSuffixes = new String[]{
                "xls", "xlsx", "*", "jpg", "pdf", "png", "ppt", "txt",
                "doc", "docx", "zip", "rar", "7z", ""
        };

        for (int i = 0; i < fileTypeNames.length; i++) {
            FileType fileType = new FileType();
            fileType.setFileTypeId(IdWorker.get().nextId());
            fileType.setFileTypeName(fileTypeNames[i]);
            fileType.setFileTypeImage(fileTypeImages[i]);
            fileType.setFileTypeSuffix(fileTypeSuffixes[i]);

            assert fileTypeMapper.insertFileType(fileType) == 1;
        }
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