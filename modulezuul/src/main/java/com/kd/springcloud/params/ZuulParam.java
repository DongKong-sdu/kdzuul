package com.kd.springcloud.params;

import lombok.Data;

@Data
public class ZuulParam {
    /**
     * 代转发地址
     */
    private String serviceId;
    /**
     * 转发地址
     */
    private String cloudServiceId;
}
