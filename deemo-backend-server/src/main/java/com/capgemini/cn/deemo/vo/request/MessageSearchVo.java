package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.vo.base.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hasaker
 * @since 2019/9/5 10:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MessageSearchVo extends BaseSearchVo {

    private String query;

    private Long messageTypeId;
}
