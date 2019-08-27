package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.FileInfo;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 10:15
 */
public interface FileInfoMapper {
    FileInfo getFile(@Param("fileId") Long fileId);

    List<FileInfo> listFiles(@Param("fileInfoSearchVo") FileInfoSearchVo fileInfoSearchVo);

    Integer countFiles(@Param("fileInfoSearchVo") FileInfoSearchVo fileInfoSearchVo);

    List<Long> listFileIdsInCurrentDir(@Param("parentId") Long parentId);

    List<FileInfo> listFilesInTrash(@Param("fileInfoSearchVo") FileInfoSearchVo fileInfoSearchVo);

    Integer countFilesInTrash(@Param("fileInfoSearchVo") FileInfoSearchVo fileInfoSearchVo);

    Integer insertFile(@Param("fileInfo") FileInfo fileInfo);

    Integer updateFile(@Param("fileInfoEditVo") FileInfoEditVo fileInfoEditVo);

    Integer putFilesToTrash(@Param("fileIds") List<Long> fileIds);

    Integer restoreFilesFromTrash(@Param("fileIds") List<Long> fileIds);

    Integer deleteFilesFromTrash(@Param("fileIds") List<Long> fileIds);
}
