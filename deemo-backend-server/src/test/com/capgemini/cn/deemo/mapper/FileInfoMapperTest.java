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

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-21 16:07
 */
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileInfoMapper.class)
@MapperScan("com.capgemini.cn.deemo.mapper")
public class FileInfoMapperTest {
    private static final Long FILE_MANAGER_ROOT_ID = 273734225100800L;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private FileTypeMapper fileTypeMapper;

    @Test
    public void insertFileTest() {
        List<String> fileTypeSuffixes = fileTypeMapper.getAllFileTypeSuffixes();

        // 插入文件夹
        for (int i = 0; i < 10; i++) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileId(IdWorker.get().nextId());
            fileInfo.setFileName("文件夹" + i);
            fileInfo.setFilePath("/Users/hasaker/Desktop/Deemo/Files/文件夹" + i);
            fileInfo.setFileTypeId(fileTypeMapper.getFileTypeIdByFileTypeSuffix(""));
            fileInfo.setFileOwnerId(1L);
            fileInfo.setParentId(FILE_MANAGER_ROOT_ID);
            fileInfo.setRemark("测试文件夹" + i);

            assert fileInfoMapper.insertFile(fileInfo) == 1;
        }
//        // 插入文件
//        String[] suffixes = new String[]{"xls", "xlsx", "jpg", "png", "ppt", "pdf",
//                "doc", "docx", "rar", "zip", "mp4", "mkv", "rmvb"};
//        for (int i = 0; i < suffixes.length; i++) {
//            Long fileTypeId;
//
//            if (fileTypeSuffixes.contains(suffixes[i])) {
//                fileTypeId = fileTypeMapper.getFileTypeIdByFileTypeSuffix(suffixes[i].toLowerCase());
//            } else {
//                fileTypeId = fileTypeMapper.getFileTypeIdByFileTypeSuffix("*");
//            }
//
//            FileInfo fileInfo = new FileInfo();
//            fileInfo.setFileId(IdWorker.get().nextId());
//            fileInfo.setFileName("文件" + i + "." + suffixes[i]);
//            fileInfo.setFilePath("根目录/文件" + i + "." + suffixes[i]);
//            fileInfo.setFileTypeId(fileTypeId);
//            fileInfo.setFileOwnerId(1L);
//            fileInfo.setParentId(FILE_MANAGER_ROOT_ID);
//            fileInfo.setRemark("测试文件" + i + "." + suffixes[i]);
//
//            assert fileInfoMapper.insertFile(fileInfo) == 1;
//        }
    }

}