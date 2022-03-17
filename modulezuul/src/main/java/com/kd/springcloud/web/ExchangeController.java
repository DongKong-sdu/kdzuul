package com.kd.springcloud.web;

import com.kd.springcloud.entity.Gateway;
import com.kd.springcloud.params.GatewayParam;
import com.kd.springcloud.params.GatewayUParam;
import com.kd.springcloud.result.JsonResult;
import com.kd.springcloud.service.ExchangeService;
import com.kd.springcloud.service.impl.ExchangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeServiceImpl exchangeServiceImpl;
    @RequestMapping("/update/{serviceid}/{cloudserviceid}")
    public JsonResult updateGateway(@PathVariable String serviceid,@PathVariable String cloudserviceid){
        return exchangeServiceImpl.updateGateway(serviceid,cloudserviceid);
    }
    @GetMapping("/getList")
    public JsonResult getList(){
        return exchangeServiceImpl.getList();
    }
    @PostMapping("/create")
    public JsonResult<Gateway> createGateway(@RequestBody GatewayParam gatewayParam) {
        return exchangeServiceImpl.createGateway(gatewayParam);
    }
    @PostMapping("/updateGate")
    public JsonResult<Gateway> updateGateway(@RequestBody GatewayUParam gatewayUParam) {
        return exchangeServiceImpl.updateGate(gatewayUParam);
    }
    @DeleteMapping("/delete")
    public JsonResult deleteGateway(@RequestParam("id") String id) {
        return exchangeServiceImpl.deleteGatewayById(id);
    }
    @GetMapping("/getOne")
    public JsonResult getSeleteOne(@RequestParam("id") String id){
        return exchangeServiceImpl.getSeleteOne(id);
    }
    @GetMapping("/getupdateinfo")
    public JsonResult getUpdateInfo(){
        return exchangeServiceImpl.getUpdateInfo();
    }
}
