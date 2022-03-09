package com.kd.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kd.springcloud.entity.Gateway;

public interface ExchangeService extends IService<Gateway> {
    Integer updateGateway(String servicename);
}
