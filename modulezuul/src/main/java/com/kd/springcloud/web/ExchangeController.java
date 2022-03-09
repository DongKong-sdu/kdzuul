package com.kd.springcloud.web;

import com.kd.springcloud.entity.Gateway;
import com.kd.springcloud.params.GatewayParam;
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
    @RequestMapping("/update/{servicename}/{cloudservicename}")
    public JsonResult updateGateway(@PathVariable String servicename,@PathVariable String cloudservicename){
        return exchangeServiceImpl.updateGateway(servicename,cloudservicename);
    }
    @GetMapping("/getList")
    public JsonResult getList(){
        return exchangeServiceImpl.getList();
    }
    @PostMapping("/create")
    public JsonResult<Gateway> createGateway(@RequestBody GatewayParam gatewayParam) {
        return exchangeServiceImpl.createGateway(gatewayParam);
    }
    @DeleteMapping("/delete")
    public JsonResult deleteGateway(@RequestParam("id") String id) {
        return exchangeServiceImpl.deleteGatewayById(id);
    }
    @GetMapping("/getOne")
    public JsonResult getSeleteOne(@RequestParam("id") String id){
        return exchangeServiceImpl.getSeleteOne(id);
    }
}
