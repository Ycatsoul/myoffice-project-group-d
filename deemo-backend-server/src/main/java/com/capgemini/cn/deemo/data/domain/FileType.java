package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019-08-21 15:18
 * 文件类型类
 */
@Data
public class FileType {
    /**
     * ID
     */
    private Long fileTypeId;

    /**
     * 名称
     */
    private String fileTypeName;

    /**
     * 图标
     */
    private String fileTypeImage;

    /**
     * 后缀
     */
    private String fileTypeSuffix;
}
