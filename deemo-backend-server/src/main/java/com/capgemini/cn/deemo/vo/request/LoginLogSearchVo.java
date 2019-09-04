package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.vo.base.BaseSearchVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019/9/4 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class LoginLogSearchVo extends BaseSearchVo {

    private Long loginUserId;

    private Date startTime;

    private Date endTime;
}
