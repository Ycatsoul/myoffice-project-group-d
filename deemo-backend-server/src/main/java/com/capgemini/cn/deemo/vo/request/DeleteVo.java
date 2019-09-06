package com.capgemini.cn.deemo.vo.request;

import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/6 20:41
 */
@Data
public class DeleteVo {

    List<Long> ids;
}
