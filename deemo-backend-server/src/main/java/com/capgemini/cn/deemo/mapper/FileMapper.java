package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.File;
import com.capgemini.cn.deemo.vo.request.FileEditVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 10:15
 */
public interface FileMapper {
    File getFile(@Param("fileId") Long fileId);

    List<File> getFiles();

    Integer insertFile(@Param("file") File file);

    Integer updateFile(@Param("fileEditVo") FileEditVo fileEditVo);

    Integer putFileToTrash(@Param("fileId") Long fileId);

    Integer restoreFileFromTrash(@Param("fileId") Long fileId);

    Integer deleteFile(@Param("fileId") Long fileId);
}
