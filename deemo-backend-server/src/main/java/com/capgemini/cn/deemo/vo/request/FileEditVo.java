package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author hasaker
 * @since 2019-08-22 11:02
 */
@Data
@ApiModel(value = "File更新请求Vo")
public class FileEditVo {

    private Long fileId;

    private String fileName;

    private String remark;
}
