package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/24 14:39
 */
@Data
@ApiModel(value = "Note更新请求Vo")
public class NoteEditVo {

    @ApiModelProperty(value = "便签ID")
    private Long noteId;

    @ApiModelProperty(value = "便签标题")
    private String noteTitle;

    @ApiModelProperty(value = "便签内容")
    private String noteContent;

    @ApiModelProperty(value = "便签创建者")
    private String createTime;

    @ApiModelProperty(value = "便签创建时间")
    private Long createUserId;
}
