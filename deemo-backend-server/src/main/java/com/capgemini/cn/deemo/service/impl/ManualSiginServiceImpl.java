package com.capgemini.cn.deemo.service.impl;

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
