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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019-08-22 15:53
 */
@Slf4j
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
     * 上传文件
     */
    @Override
    public boolean uploadFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = System.getProperties().getProperty("user.home") + "/Desktop/Deemo/Files/";

        try {
            multipartFile.transferTo(new File(filePath + fileName));
            log.info("上传成功！");

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * TODO 添加文件拥有者ID
     * 添加文件记录
     */
    @Override
    public int insertFile(FileInfoAddVo fileInfoAddVo) {
        String fileName = fileInfoAddVo.getFileName();
        String filePath = System.getProperties().getProperty("user.home") + "/Desktop/Deemo/Files/";
        String fileSuffix = "";
        if (fileName.contains(".")) {
            fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        List<String> fileTypeSuffixes = fileTypeMapper.getAllFileTypeSuffixes();
        Long fileTypeId;

        if (fileTypeSuffixes.contains(fileSuffix)) {
            fileTypeId = fileTypeMapper.getFileTypeIdByFileTypeSuffix(fileSuffix.toLowerCase());
        } else {
            fileTypeId = fileTypeMapper.getFileTypeIdByFileTypeSuffix("*");
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(IdWorker.get().nextId());
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(filePath + fileName);
        fileInfo.setFileTypeId(fileTypeId);
        fileInfo.setFileOwnerId(0L);
        fileInfo.setParentId(fileInfoAddVo.getParentId());
        fileInfo.setRemark(fileInfoAddVo.getRemark());
        fileInfo.setParentIdInTrash(fileInfoAddVo.getParentId());

        return fileInfoMapper.insertFile(fileInfo);
    }

    /**
     * 更新一条文件记录
     */
    @Override
    public int updateFile(FileInfoEditVo fileInfoEditVo) {
        return fileInfoMapper.updateFile(fileInfoEditVo);
    }

    /**
     * 将文件放入回收站
     * 如果参数是一个文件夹, 将当前文件夹及其子文件和文件夹放入回收站
     * @return 受影响的行数
     */
    @Override
    public int putFilesToTrash(List<Long> fileIds) {
        int a = fileInfoMapper.putFilesToTrash(fileIds);
        int b = 0;
        List<Long> childIds = getChildIds(fileIds);

        if (childIds.size() > 0) {
            b = fileInfoMapper.putChildrenToTrash(childIds);
        }

        return a + b;
    }

    /**
     * 从回收站中取回文件
     * 如果参数是一个文件夹, 将当前文件夹及其子文件和文件夹从回收站中取回
     */
    @Override
    public int restoreFilesFromTrash(List<Long> fileIds) {
        List<Long> childIds = getChildIds(fileIds);
        int a = 0;
        int b = 0;

        for (Long fileId : fileIds) {
            a += fileInfoMapper.restoreFileFromTrash(fileId);
        }

        if (childIds.size() > 0) {
            b = fileInfoMapper.restoreChildrenFromTrash(childIds);
        }

        return a + b;
    }

    /**
     * 从回收站中彻底删除文件
     * 如果参数是一个文件夹, 将当前文件夹及其子文件和文件夹彻底删除
     */
    @Override
    public int deleteFilesFromTrash(List<Long> fileIds) {
        fileIds.addAll(getChildIds(fileIds));

        return fileInfoMapper.deleteFilesFromTrash(fileIds);
    }

    /**
     * 获取文件夹ID及其儿子们的ID
     */
    private List<Long> getChildIds(List<Long> fileIds) {
        List<Long> childIds = new ArrayList<>();
        LinkedList<Long> queue = new LinkedList<>(fileIds);
        int fileIdsSize = fileIds.size();
        int totalSize = 0;

        while (queue.size() > 0) {
            totalSize++;
            Long fileId = queue.pop();
            if (totalSize > fileIdsSize) {
                childIds.add(fileId);
            }
            queue.addAll(fileInfoMapper.getChildIds(fileId));
        }

        return childIds;
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
