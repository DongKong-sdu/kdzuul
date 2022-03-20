package com.kd.springcloud.service.impl;

import com.kd.springcloud.entity.Gateway;
import com.kd.springcloud.entity.Status;
import com.kd.springcloud.mapper.ExchangeMapper;
import com.kd.springcloud.params.GatewayParam;
import com.kd.springcloud.params.GatewayUParam;
import com.kd.springcloud.params.Host;
import com.kd.springcloud.result.JsonResult;
import com.kd.springcloud.result.ResultCode;
import com.kd.springcloud.result.ResultTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeServiceImpl  {
    @Autowired
    private ExchangeMapper exchangeMapper;
    public JsonResult updateGateway(String serviceid,String cloudserviceid){
        //获取待转发主机名
        String listHostCloud=exchangeMapper.selectHostCloud(serviceid);
        //获取云端主机名和服务名
        List<Host> listHost=exchangeMapper.selectHost(cloudserviceid);
        //云端主机名暂存
        String hn=listHost.get(0).getHostname();
        //代转发主机的服务状态改为不可用（0）
        for (int i = 0; i < listHost.size(); i++) {
            String h=listHostCloud+"-"+listHost.get(i).getImage();
            int res=exchangeMapper.updateGateway(h);
        }
        //云端主机的服务状态改为在用（1）
        for (int i = 0; i < listHost.size(); i++) {
            String hCloud=listHost.get(i).getHostname()+"-"+listHost.get(i).getImage();
            int res=exchangeMapper.updateCloudGateway(hCloud);
        }
        //对转发做备份，以便追溯
        Date date = new Date(System.currentTimeMillis());
        int status=exchangeMapper.insertStatus(listHostCloud,hn,date);
        //结果处理
        if (status>0) {
            return ResultTool.success();
        }
        return ResultTool.fail();
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
//        Integer selectCountS = exchangeMapper.selectCount(new LambdaQueryWrapper<Gateway>()
//                .eq(Gateway::getServiceId, gatewayParam.getServiceId()));
        if (selectCount > 0) {
            return ResultTool.fail(ResultCode.SERVICE_CONSIST);
        }
        BeanUtils.copyProperties(gatewayParam, gateway);
//        gateway.setRetryable(false);
//        gateway.setStripPrefix(true);

        gateway.setEnabled(true);
        String serviceName=gateway.getId();
        String[] offerCodeString = serviceName.split("-");
//        System.out.println(offerCodeString[0]+"-"+offerCodeString[1]+"-"+offerCodeString[2]);
//        String serviceFirstName=serviceName.replace("-"+offerCodeString[offerCodeString.length-1], "");
        Gateway gateway1 = new Gateway();
        gateway1.setId("center-node-"+offerCodeString[offerCodeString.length-1]);
        gateway1.setPath(gateway.getPath());
        String url=gateway.getUrl();
        String[] offerCodeStringUrl = url.split(":");
        String ip=exchangeMapper.selectIp();
        gateway1.setUrl("http://"+ip+":"+offerCodeStringUrl[offerCodeStringUrl.length-1]);
        gateway1.setEnabled(false);
        int res=exchangeMapper.insert(gateway);
        int res1=exchangeMapper.insert(gateway1);
        if(res>0&&res1>0){
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

    public JsonResult<Gateway> updateGate(GatewayUParam gatewayUParam) {
        Gateway gateway = new Gateway();
//        Integer selectCountS = exchangeMapper.selectCount(new LambdaQueryWrapper<Gateway>()
//                .eq(Gateway::getServiceId, gateway.getServiceId()));
//        if (selectCountS>0) {
//            return ResultTool.fail(ResultCode.SERVICE_CONSIST);
//        }
        BeanUtils.copyProperties(gatewayUParam, gateway);

        int res=exchangeMapper.updateById(gateway);
        if(res>0){
            return ResultTool.success(gateway);
        }
        else {
            return ResultTool.fail();
        }
    }

    public JsonResult getUpdateInfo() {
        List<Status> data= exchangeMapper.getStatusList();
        if (data!=null) {
            return ResultTool.success(data);
        } else {
            return ResultTool.fail();
        }
    }
}
