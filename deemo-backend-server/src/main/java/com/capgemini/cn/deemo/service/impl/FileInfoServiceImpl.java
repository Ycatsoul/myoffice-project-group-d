package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.FileInfo;
import com.capgemini.cn.deemo.data.domain.FileType;
import com.capgemini.cn.deemo.mapper.FileInfoMapper;
import com.capgemini.cn.deemo.mapper.FileTypeMapper;
import com.capgemini.cn.deemo.service.FileInfoService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.FileInfoAddVo;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import com.capgemini.cn.deemo.vo.response.FileInfoRespVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019-08-22 15:53
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
    private FileInfoMapper fileInfoMapper;
    private FileTypeMapper fileTypeMapper;

    public FileInfoServiceImpl(FileInfoMapper fileInfoMapper, FileTypeMapper fileTypeMapper) {
        this.fileInfoMapper = fileInfoMapper;
        this.fileTypeMapper = fileTypeMapper;
    }

    /**
     * 获取一个文件的详尽信息
     */
    @Override
    public RespVos<FileInfoRespVo> getFile(Long fileId) {
        RespVos<FileInfoRespVo> respVos = new RespVos<>();
        FileInfo fileInfo = fileInfoMapper.getFile(fileId);

        if (fileInfo != null) {
            respVos.setSize(1);
            respVos.setVos(new ArrayList<FileInfoRespVo>(1){{
                add(convertFileInfoToFileInfoRespVo(fileInfo));
            }});

            return respVos;
        }

        return null;
    }

    /**
     * 获取文件列表
     * 根据 @param fileInfoSearchVo 里属性的值可用于搜索文件
     * @param fileInfoSearchVo 查询信息
     * @return respVos<FileInfoRespVo>
     */
    @Override
    public RespVos<FileInfoRespVo> listFiles(FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoRespVo> respVos = new RespVos<>();
        List<FileInfo> fileInfos = fileInfoMapper.listFiles(fileInfoSearchVo);

        if (fileInfos != null && fileInfos.size() > 0) {
            respVos.setSize(fileInfoMapper.countFiles(fileInfoSearchVo));
            respVos.setVos(fileInfos.stream().map(this::convertFileInfoToFileInfoRespVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    /**
     * 获取
     */
    @Override
    public RespVos<FileInfoRespVo> listFilesInTrash(FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoRespVo> respVos = new RespVos<>();
        List<FileInfo> fileInfos = fileInfoMapper.listFilesInTrash(fileInfoSearchVo);

        if (fileInfos != null && fileInfos.size() > 0) {
            respVos.setSize(fileInfoMapper.countFilesInTrash(fileInfoSearchVo));
            respVos.setVos(fileInfos.stream().map(this::convertFileInfoToFileInfoRespVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    /**
     * TODO 添加文件拥有者ID
     * 插入一条文件记录
     */
    @Override
    public Integer uploadFile(MultipartFile multipartFile, FileInfoAddVo fileInfoAddVo) {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = System.getProperties().getProperty("user.home") + "/Desktop/Deemo/";
        String fileSuffix = Objects.requireNonNull(fileName).split(".")[fileName.split(".").length - 1];
        Long fileTypeId = fileTypeMapper.getFileTypeIdByFileTypeSuffix(fileSuffix.toLowerCase());

        try {
            multipartFile.transferTo(new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(IdWorker.get().nextId());
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(filePath + fileName);
        fileInfo.setFileTypeId(fileTypeId);
        fileInfo.setFileOwnerId(0L);
        fileInfo.setParentId(fileInfoAddVo.getParentId());
        fileInfo.setRemark(fileInfoAddVo.getRemark());

        return fileInfoMapper.insertFile(fileInfo);
    }

    /**
     * 更新一条文件记录
     */
    @Override
    public Integer updateFile(FileInfoEditVo fileInfoEditVo) {
        return fileInfoMapper.updateFile(fileInfoEditVo);
    }

    /**
     * 将文件放入回收站
     * 如果参数是一个文件夹, 将当前文件夹及其子文件和文件夹放入回收站
     */
    @Override
    public Integer putFilesToTrash(List<Long> fileIds) {
        return fileInfoMapper.putFilesToTrash(includeChildren(fileIds));
    }

    /**
     * 从回收站中取回文件
     * 如果参数是一个文件夹, 将当前文件夹及其子文件和文件夹从回收站中取回
     */
    @Override
    public Integer restoreFilesFromTrash(List<Long> fileIds) {
        return fileInfoMapper.restoreFilesFromTrash(includeChildren(fileIds));
    }

    /**
     * 从回收站中彻底删除文件
     * 如果参数是一个文件夹, 将当前文件夹及其子文件和文件夹彻底删除
     */
    @Override
    public Integer deleteFilesFromTrash(List<Long> fileIds) {
        return fileInfoMapper.deleteFilesFromTrash(includeChildren(fileIds));
    }

    /**
     * 获取文件夹ID及其儿子们的ID
     */
    private List<Long> includeChildren(List<Long> fileIds) {
        List<Long> allFileIds = new ArrayList<>();
        LinkedList<Long> queue = new LinkedList<>(fileIds);

        while (queue.size() > 0) {
            Long fileId = queue.pop();
            allFileIds.add(fileId);
            queue.addAll(fileInfoMapper.listFileIdsInCurrentDir(fileId));
        }

        return allFileIds;
    }

    /**
     * TODO 获取文件拥有者的姓名
     * 将FileInfo转换为FileInfoRespVo
     */
    private FileInfoRespVo convertFileInfoToFileInfoRespVo(FileInfo fileInfo) {
        FileType fileType = fileTypeMapper.getFileTypeByFileTypeId(fileInfo.getFileTypeId());
        FileInfoRespVo fileInfoRespVo = new FileInfoRespVo();

        fileInfoRespVo.setFileId(fileInfo.getFileId());
        fileInfoRespVo.setFileName(fileInfo.getFileName());
        fileInfoRespVo.setFilePath(fileInfo.getFilePath());
        fileInfoRespVo.setFileTypeId(fileInfo.getFileTypeId());
        fileInfoRespVo.setFileTypeName(fileType.getFileTypeName());
        fileInfoRespVo.setFileTypeImage(fileType.getFileTypeImage());
        fileInfoRespVo.setFileOwnerId(fileInfo.getFileOwnerId());
        //  fileInfoRespVo.setFileOwnerName();
        fileInfoRespVo.setParentId(fileInfo.getParentId());
        fileInfoRespVo.setRemark(fileInfo.getRemark());
        fileInfoRespVo.setIsDeleted(fileInfo.getIsDeleted());
        fileInfoRespVo.setDeleteTime(fileInfo.getDeleteTime());

        return fileInfoRespVo;
    }
}
