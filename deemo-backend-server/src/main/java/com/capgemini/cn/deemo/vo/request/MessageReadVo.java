package com.capgemini.cn.deemo.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/6 21:03
 */
@Data
@ApiModel
public class MessageReadVo {

    Long currentUserId;

    List<Long> messageIds;
}
