package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.ManualSigin;
import com.capgemini.cn.deemo.mapper.ManualSiginMapper;
import com.capgemini.cn.deemo.service.ManualSiginService;
import com.capgemini.cn.deemo.vo.request.ManualSiginSearchVo;
import com.capgemini.cn.deemo.vo.request.ManualSiginVo;
import com.capgemini.cn.deemo.vo.response.ManualSiginResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
//@Service
//public class ManualSiginServiceImpl implements ManualSiginService {
//
//    @Autowired
//    private ManualSiginMapper manualSiginMapper;
//
//    @Override
//    public ManualSigin queryObject(Integer id) {
//        return manualSiginMapper.queryObject(id);
//    }
//
//    @Override
//    public boolean save(ManualSigin manualSigin) {
//        return manualSiginMapper.save(manualSigin) > 0;
//    }
//
//    @Override
//    public boolean update(ManualSigin manualSigin) {
//        return manualSiginMapper.update(manualSigin) > 0;
//    }
//
//    @Override
//    public boolean delete(Integer id) {
//        return manualSiginMapper.delete(id) > 0;
//    }
//
//    @Override
//    public boolean deleteBatch(Integer[] ids) {
//        return manualSiginMapper.deleteBatch(ids) > 0;
//    }
//
////    @Override
////    public ManualSiginResponseVo listManualSigin(ManualSiginSearchVo manualSiginSearchVo) {
////        ManualSiginResponseVo manualSiginResponseVo = new ManualSiginResponseVo();
////        List<ManualSigin> manualSiginList = manualSiginMapper.listManualSigin(manualSiginSearchVo);
////        List<ManualSiginVo> manualSiginVos = new ArrayList<ManualSiginVo>();
////        for (ManualSigin manualSigin : manualSiginList) {
////            ManualSiginVo manualSiginVo = new ManualSiginVo();
//////            manualSiginVos.add(ConvertUtils.convertDepartmentToVo(manualSigin));
//////        }
//////        manualSiginResponseVo.setManualSiginVo(manualSiginVos);
//////        manualSiginResponseVo.setListCount(manualSiginMapper.countManualSigin(manualSiginSearchVo));
//////        return departmentResponseVo;
////    }
//}
