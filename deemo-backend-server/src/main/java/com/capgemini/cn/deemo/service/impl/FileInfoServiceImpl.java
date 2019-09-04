package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.FileInfo;
import com.capgemini.cn.deemo.data.domain.FileType;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.mapper.FileInfoMapper;
import com.capgemini.cn.deemo.mapper.FileTypeMapper;
import com.capgemini.cn.deemo.mapper.UserMapper;
import com.capgemini.cn.deemo.service.FileInfoService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import com.capgemini.cn.deemo.vo.response.FileInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    private UserMapper userMapper;

    public FileInfoServiceImpl(FileInfoMapper fileInfoMapper, FileTypeMapper fileTypeMapper, UserMapper userMapper) {
        this.fileInfoMapper = fileInfoMapper;
        this.fileTypeMapper = fileTypeMapper;
        this.userMapper = userMapper;
    }

    /**
     * 获取一个文件的详尽信息
     */
    @Override
    public RespVos<FileInfoVo> getFile(Long fileId) {
        RespVos<FileInfoVo> respVos = new RespVos<>();
        FileInfo fileInfo = fileInfoMapper.getFile(fileId);

        if (fileInfo != null) {
            respVos.setSize(1);
            respVos.setVos(new ArrayList<FileInfoVo>(1){{
                add(convertToVo(fileInfo));
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
    public RespVos<FileInfoVo> listFiles(FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoVo> respVos = new RespVos<>();
        List<FileInfo> fileInfos = fileInfoMapper.listFiles(fileInfoSearchVo);

        if (fileInfos != null && fileInfos.size() > 0) {
            respVos.setSize(fileInfoMapper.countFiles(fileInfoSearchVo));
            respVos.setVos(fileInfos.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    /**
     * 获取回收站文件列表
     */
    @Override
    public RespVos<FileInfoVo> listFilesInTrash(FileInfoSearchVo fileInfoSearchVo) {
        RespVos<FileInfoVo> respVos = new RespVos<>();
        List<FileInfo> fileInfos = fileInfoMapper.listFilesInTrash(fileInfoSearchVo);

        if (fileInfos != null && fileInfos.size() > 0) {
            respVos.setSize(fileInfoMapper.countFilesInTrash(fileInfoSearchVo));
            respVos.setVos(fileInfos.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    /**
     * 上传文件
     */
    @Override
    public Boolean uploadFile(MultipartFile multipartFile) {
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
     * 添加文件记录
     */
    @Override
    public Integer insertFile(FileInfoEditVo fileInfoEditVo) {
        String fileName = fileInfoEditVo.getFileName();
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
        fileInfo.setFileOwnerId(fileInfoEditVo.getFileOwnerId());
        fileInfo.setParentId(fileInfoEditVo.getParentId());
        fileInfo.setRemark(fileInfoEditVo.getRemark());
        fileInfo.setParentIdInTrash(fileInfoEditVo.getParentId());

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
     * @return 受影响的行数
     */
    @Override
    public Integer putFilesToTrash(List<Long> fileIds) {
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
    public Integer restoreFilesFromTrash(List<Long> fileIds) {
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
    public Integer deleteFilesFromTrash(List<Long> fileIds) {
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
     * 将FileInfo转换为FileInfoVo
     */
    private FileInfoVo convertToVo(FileInfo fileInfo) {
        FileType fileType = fileTypeMapper.getFileTypeByFileTypeId(fileInfo.getFileTypeId());
        FileInfoVo fileInfoVo = new FileInfoVo();
        User user = userMapper.getUser(fileInfo.getFileOwnerId());

        fileInfoVo.setFileId(fileInfo.getFileId());
        fileInfoVo.setFileName(fileInfo.getFileName());
        fileInfoVo.setFilePath(fileInfo.getFilePath());
        fileInfoVo.setFileTypeId(fileInfo.getFileTypeId());
        fileInfoVo.setFileTypeName(fileType.getFileTypeName());
        fileInfoVo.setFileTypeImage(fileType.getFileTypeImage());
        fileInfoVo.setFileOwnerId(fileInfo.getFileOwnerId());
        fileInfoVo.setFileOwnerName(user.getName());
        fileInfoVo.setParentId(fileInfo.getParentId());
        fileInfoVo.setRemark(fileInfo.getRemark());
        fileInfoVo.setIsDeleted(fileInfo.getIsDeleted());
        fileInfoVo.setDeleteTime(fileInfo.getDeleteTime());

        return fileInfoVo;
    }

    /**
     * 将FileInfoEditVo装换为FileInfo
     */
    private FileInfo convertFromVo(FileInfoEditVo fileInfoEditVo) {
        FileInfo fileInfo = new FileInfo();

        fileInfo.setFileId(fileInfoEditVo.getFileId() == null ?
                IdWorker.get().nextId() : fileInfoEditVo.getFileId());
        fileInfo.setFileName(fileInfoEditVo.getFileName());
        fileInfo.setFilePath(fileInfoEditVo.getFilePath());
        fileInfo.setFileTypeId(fileInfoEditVo.getFileTypeId());
        fileInfo.setFileOwnerId(fileInfoEditVo.getFileOwnerId());
        fileInfo.setParentId(fileInfo.getParentId());
        fileInfo.setRemark(fileInfoEditVo.getRemark());

        return fileInfo;
    }
}
