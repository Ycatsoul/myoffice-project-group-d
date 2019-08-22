package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

/**
 * @author hasaker
 * @since 2019-08-21 15:06
 */
@Data
public class File {
    private Integer id;
    private Long fileId;
    private String fileName;
    private String filePath;
    private Long fileTypeId;
    private Long fileOwnerId;
    private Long parentId;
    private String remark;
    private Boolean isDeleted;
}
