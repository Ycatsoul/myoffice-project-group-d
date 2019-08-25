package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.FileInfo;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 15:52
 */
public interface FileInfoService {
    public FileInfo getFile(Long fileId);

    public List<FileInfo> searchFiles(FileInfoSearchVo fileInfoSearchVo);

    public List<FileInfo> listFiles();

    public List<FileInfo> listDeletedFiles();

    public Integer insertFile(FileInfo fileInfo);

    public Integer updateFile(FileInfoEditVo fileInfoEditVo);

    public Integer putFilesToTrash(List<Long> fileIds);

    public Integer restoreFilesFromTrash(List<Long> fileIds);

    public Integer deleteFilesFromTrash(List<Long> fileIds);
}