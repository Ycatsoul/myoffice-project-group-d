package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.ManualSigin;
import com.capgemini.cn.deemo.vo.request.DepartmentSearchVo;
import com.capgemini.cn.deemo.vo.request.ManualSiginSearchVo;
import com.capgemini.cn.deemo.vo.response.DepartmentResponseVo;
import com.capgemini.cn.deemo.vo.response.ManualSiginResponseVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public interface ManualSiginService {

    ManualSigin queryObject(Integer id);

    boolean save(ManualSigin manualSigin);

    boolean update(ManualSigin manualSigin);

    boolean delete(Integer id);

    boolean deleteBatch(Integer[] ids);

    ManualSiginResponseVo listManualSigin(ManualSiginSearchVo manualSiginSearchVo);
}
