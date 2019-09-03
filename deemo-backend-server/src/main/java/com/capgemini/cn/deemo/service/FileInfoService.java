package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import com.capgemini.cn.deemo.vo.response.FileInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 15:52
 */
public interface FileInfoService {
    RespVos<FileInfoVo> getFile(Long fileId);

    RespVos<FileInfoVo> listFiles(FileInfoSearchVo fileInfoSearchVo);

    RespVos<FileInfoVo> listFilesInTrash(FileInfoSearchVo fileInfoSearchVo);

    boolean uploadFile(MultipartFile multipartFile);

    int insertFile(FileInfoEditVo fileInfoEditVo);

    int updateFile(FileInfoEditVo fileInfoEditVo);

    int putFilesToTrash(List<Long> fileIds);

    int restoreFilesFromTrash(List<Long> fileIds);

    int deleteFilesFromTrash(List<Long> fileIds);
}