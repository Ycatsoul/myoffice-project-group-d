package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.FileInfo;
import com.capgemini.cn.deemo.mapper.FileInfoMapper;
import com.capgemini.cn.deemo.service.FileInfoService;
import com.capgemini.cn.deemo.vo.request.FileInfoEditVo;
import com.capgemini.cn.deemo.vo.request.FileInfoSearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019-08-22 15:53
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
    private FileInfoMapper fileInfoMapper;

    public FileInfoServiceImpl(FileInfoMapper fileInfoMapper) {
        this.fileInfoMapper = fileInfoMapper;
    }

    @Override
    public FileInfo getFile(Long fileId) {

        return fileInfoMapper.getFile(fileId);
    }

    @Override
    public List<FileInfo> getFiles(FileInfoSearchVo fileInfoSearchVo) {
        return fileInfoMapper.getFiles();
    }

    @Override
    public Integer insertFile(FileInfo fileInfo) {
        return fileInfoMapper.insertFile(fileInfo);
    }

    @Override
    public Integer updateFile(FileInfoEditVo fileInfoEditVo) {
        return fileInfoMapper.updateFile(fileInfoEditVo);
    }

    @Override
    public Integer putFilesToTrash(List<Long> fileIds) {
        return fileInfoMapper.putFilesToTrash(fileIds);
    }

    @Override
    public Integer restoreFilesFromTrash(List<Long> fileIds) {
        return fileInfoMapper.restoreFilesFromTrash(fileIds);
    }

    @Override
    public Integer deleteFiles(List<Long> fileIds) {
        return fileInfoMapper.deleteFilesFromTrash(fileIds);
    }
}
