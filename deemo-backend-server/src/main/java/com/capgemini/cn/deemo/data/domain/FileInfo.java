package com.capgemini.cn.deemo.data.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-21 15:06
 */
@Data
@ApiModel
public class FileInfo {

    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型ID
     */
    private Long fileTypeId;

    /**
     * 文件所有者ID
     */
    private Long fileOwnerId;

    /**
     * 文件父目录ID
     */
    private Long parentId;

    /**
     * 文件上传时间
     */
    private Date uploadTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 文件是否被删除
     */
    private Boolean isDeleted;

    /**
     * 在回收站中的父文件夹ID
     */
    private Long parentIdInTrash;

    /**
     * 删除时间
     */
    private Date deleteTime;
}
