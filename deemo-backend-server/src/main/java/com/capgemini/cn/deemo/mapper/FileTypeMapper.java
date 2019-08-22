package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.FileType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-21 15:05
 */
public interface FileTypeMapper {
    List<FileType> getAllFileType();

    Integer insertFileType(@Param("fileType") FileType fileType);

    Integer deleteFileType(@Param("fileTypeId") Long fileTypeId);
}
