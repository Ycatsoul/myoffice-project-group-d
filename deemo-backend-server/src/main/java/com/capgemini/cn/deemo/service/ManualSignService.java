package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.vo.base.RespVos;
import com.capgemini.cn.deemo.vo.request.ManualSignEditVo;
import com.capgemini.cn.deemo.vo.request.ManualSignSearchVo;
import com.capgemini.cn.deemo.vo.response.ManualSignVo;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
public interface ManualSignService {

    RespVos<ManualSignVo> getManualSign(Long manualSignId);

    RespVos<ManualSignVo> listManualSigns(ManualSignSearchVo manualSignSearchVo);

    Integer addManualSign(ManualSignEditVo manualSignEditVo);

    Integer updateManualSign(ManualSignEditVo manualSignEditVo);

    Integer deleteManualSigns(List<Long> manualSignIds);
}
