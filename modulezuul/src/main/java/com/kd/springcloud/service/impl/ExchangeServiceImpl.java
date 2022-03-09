package com.kd.springcloud.service.impl;

import com.kd.springcloud.entity.Gateway;
import com.kd.springcloud.mapper.ExchangeMapper;
import com.kd.springcloud.params.GatewayParam;
import com.kd.springcloud.result.JsonResult;
import com.kd.springcloud.result.ResultCode;
import com.kd.springcloud.result.ResultTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ExchangeServiceImpl  {
    @Autowired
    private ExchangeMapper exchangeMapper;
    public JsonResult updateGateway(String servicename,String cloudservicename){
        int res=exchangeMapper.updateGateway(servicename);
        int resCloud=exchangeMapper.updateCloudGateway(cloudservicename);
        if (res > 0&&resCloud>0) {
            return ResultTool.success();
        } else {
            return ResultTool.fail();
        }
    }
    public JsonResult getList(){
        List<Gateway> data= exchangeMapper.getList();
        if(data!=null){
            return ResultTool.success(data);
        }
        else {
            return ResultTool.fail();
        }
    }

    public JsonResult<Gateway> createGateway(GatewayParam gatewayParam) {
        Gateway gateway = new Gateway();
        Integer selectCount = exchangeMapper.selectCount(new LambdaQueryWrapper<Gateway>()
                .eq(Gateway::getId, gatewayParam.getId()));
        Integer selectCountS = exchangeMapper.selectCount(new LambdaQueryWrapper<Gateway>()
                .eq(Gateway::getServiceId, gatewayParam.getServiceId()));
        if (selectCount > 0||selectCountS>0) {
            return ResultTool.fail(ResultCode.SERVICE_CONSIST);
        }
        BeanUtils.copyProperties(gatewayParam, gateway);
        gateway.setRetryable(false);
        gateway.setStripPrefix(true);
        int res=exchangeMapper.insert(gateway);
        if(res>0){
            return ResultTool.success(gateway);
        }
        else {
            return ResultTool.fail();
        }
    }

    public JsonResult deleteGatewayById(String id) {
        int delete=exchangeMapper.deleteById(id);
        if(delete>0){
            return ResultTool.success();
        }
        else {
            return ResultTool.fail();
        }
    }

    public JsonResult getSeleteOne(String id) {
        Gateway gateway=exchangeMapper.selectById(id);
        if(gateway!=null){
            return ResultTool.success(gateway);
        }
        else {
            return ResultTool.fail();
        }
    }
}
