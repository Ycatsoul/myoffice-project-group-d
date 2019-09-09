package com.capgemini.cn.deemo.vo.response;

import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-23 15:46
 */
@Data
public class FileInfoVo {

    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 文件名称
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
     * 文件类型名称
     */
    private String fileTypeName;

    /**
     * 文件类型图标
     */
    private String fileTypeImage;

    /**
     * 文件拥有者ID
     */
    private Long fileOwnerId;

    /**
     * 文件拥有者名字
     */
    private String fileOwnerName;

    /**
     * 父文件夹ID
     */
    private Long parentId;

    /**
     * 文件上传时间
     */
    private Date uploadTime;

    /**
     * 文件备注
     */
    private String remark;

    /**
     * 文件是否已放入回收站
     */
    private Boolean isDeleted;

    /**
     * 文件在回收站中的父文件夹
     */
    private Long parentIdInTrash;

    /**
     * 文件放入回收站的时间
     */
    private Date deleteTime;
}
