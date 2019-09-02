package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.FileInfoAddVo;
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
    public RespVos<FileInfoVo> getFile(Long fileId);

    public RespVos<FileInfoVo> listFiles(FileInfoSearchVo fileInfoSearchVo);

    public RespVos<FileInfoVo> listFilesInTrash(FileInfoSearchVo fileInfoSearchVo);

    public boolean uploadFile(MultipartFile multipartFile);

    public int insertFile(FileInfoAddVo fileInfoAddVo);

    public int updateFile(FileInfoEditVo fileInfoEditVo);

    public int putFilesToTrash(List<Long> fileIds);

    public int restoreFilesFromTrash(List<Long> fileIds);

    public int deleteFilesFromTrash(List<Long> fileIds);
}