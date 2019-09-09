package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.PreContract;
import com.capgemini.cn.deemo.vo.request.PreContractEditVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 14:05
 */
@Service
public interface PreContractMapper {

    PreContract listPreContract(@Param("preContractId") Long preContractId);

    List<PreContract> listPreContracts();

    Integer insertPreContract(@Param("preContractEditVo") PreContractEditVo preContractEditVo);

    Integer updatePreContract(@Param("preContractEditVo") PreContractEditVo preContractEditVo);

    Integer deletePreContract(@Param("userId") Long userId);
}
