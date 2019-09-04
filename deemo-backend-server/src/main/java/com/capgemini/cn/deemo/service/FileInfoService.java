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

    Boolean uploadFile(MultipartFile multipartFile);

    Integer insertFile(FileInfoEditVo fileInfoEditVo);

    Integer updateFile(FileInfoEditVo fileInfoEditVo);

    Integer putFilesToTrash(List<Long> fileIds);

    Integer restoreFilesFromTrash(List<Long> fileIds);

    Integer deleteFilesFromTrash(List<Long> fileIds);
}