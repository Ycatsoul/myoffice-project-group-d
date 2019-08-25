package com.capgemini.cn.deemo.vo.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-23 15:46
 */
@ApiModel
@Data
public class FileInfoRespVo {

    private Long fileId;

    private String fileName;

    private String filePath;

    private Long fileTypeId;

    private String fileType;

    private Long fileOwnerId;

    private String fileOwner;

    private Long parentId;

    private Date uploadTime;

    private String remark;
}
