package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.FileInfoAddVo;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import com.capgemini.cn.deemo.vo.response.FileInfoRespVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 15:52
 */
public interface FileInfoService {
    public RespVos<FileInfoRespVo> getFile(Long fileId);

    public RespVos<FileInfoRespVo> listFiles(FileInfoSearchVo fileInfoSearchVo);

    public RespVos<FileInfoRespVo> listFilesInTrash(FileInfoSearchVo fileInfoSearchVo);

    public Integer uploadFile(MultipartFile multipartFile, FileInfoAddVo fileInfoAddVo);

    public Integer updateFile(FileInfoEditVo fileInfoEditVo);

    public Integer putFilesToTrash(List<Long> fileIds);

    public Integer restoreFilesFromTrash(List<Long> fileIds);

    public Integer deleteFilesFromTrash(List<Long> fileIds);
}