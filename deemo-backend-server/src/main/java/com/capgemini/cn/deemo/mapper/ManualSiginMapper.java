package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.ManualSigin;
import com.capgemini.cn.deemo.vo.request.ManualSiginSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
public interface ManualSiginMapper extends BaseMapper<ManualSigin> {
    List<ManualSigin> listManualSigin(@Param("ManualSiginSearch") ManualSiginSearchVo manualSiginSearchVo);
    Long countManualSigin(@Param("ManualSiginSearch") ManualSiginSearchVo manualSiginSearchVo);
}
