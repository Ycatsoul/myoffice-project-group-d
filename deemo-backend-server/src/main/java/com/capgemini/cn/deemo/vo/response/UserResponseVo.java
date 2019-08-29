package com.capgemini.cn.deemo.vo.response;

import com.capgemini.cn.deemo.vo.request.UserVo;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Data
public class UserResponseVo {
    private Long listCount;

    private List<UserVo> userVos;
}
