package com.capgemini.cn.deemo.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.deemo.annotation.ControllerLog;
import com.capgemini.cn.deemo.data.domain.PreContract;
import com.capgemini.cn.deemo.service.PreContractService;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.capgemini.cn.deemo.vo.request.PreContractEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/23 10:50
 */
@Slf4j
@Api(value = "预约管理")
@RestController
@RequestMapping("/preContract")
public class PreContractController extends BaseController {

    private final PreContractService preContractService;

    public PreContractController(PreContractService preContractService) {
        this.preContractService = preContractService;
    }

    @ApiOperation(value = "获取PreContract列表")
    @GetMapping("/get")
    public RespBean getPreContractList(){

        List<PreContract> preContracts = preContractService.listPreContracts();

        /*
        判断是否获取列表成功*/
        if(preContracts != null){
            return RespBean.ok("获取成功",preContracts);
        }else{
            return RespBean.error("获取失败");
        }
    }

    @ControllerLog(name = "添加一条预约信息")
    @ApiOperation(value =  "新建一条PreContract")
    @PostMapping("/set")
    public RespBean postPreContract(@RequestBody PreContractEditVo preContractEditVo) {

        Integer res = preContractService.insertPreContract(preContractEditVo);

        /*
        判断是否新建成功*/
        if(res != null){
            return RespBean.ok("新增成功");
        }else{
            return RespBean.error("新增失败");
        }
    }

    @ApiOperation(value = "根据PreContractId获取PreContract详尽信息")
    @GetMapping("/{preContractId}")
    public RespBean getPreContract(@PathVariable Long preContractId) {

        PreContract preContract = preContractService.listPreContract(preContractId);

        /*
        判断是否获取成功*/
        if(preContract != null){
            return RespBean.ok("获取成功",preContract);
        }else{
            return RespBean.error("获取失败");
        }
    }

    @ControllerLog(name = "修改预约信息")
    @ApiOperation(value = "根据preContractId更新一条PreContract")
    @PutMapping("/update")
    public RespBean updatePreContract(@RequestBody PreContractEditVo preContract) {

        Integer res = preContractService.updatePreContract(preContract);

        /*
        判断是否更新成功*/
        if(res != null){
            return RespBean.ok("更新成功");
        }else{
            return RespBean.error("更新失败");
        }
    }

    @ControllerLog(name = "删除预约信息")
    @ApiOperation(value = "根据userId删除一条PreContract")
    @DeleteMapping("/{userId}")
    public RespBean deletePreContract(@PathVariable Long userId) {
        Integer res = preContractService.deletePreContract(userId);

        /*
        判断是否删除成功*/
        if(res != null){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }
    }
}
