package com.capgemini.cn.deemo.data.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author hasaker
 * @since 2019-08-21 15:18
 */
@Data
@ApiModel
public class FileType {
    private Integer id;
    private Long fileTypeId;
    private String fileTypeName;
    private String fileTypeImage;
    private String fileTypeSuffix;
}
