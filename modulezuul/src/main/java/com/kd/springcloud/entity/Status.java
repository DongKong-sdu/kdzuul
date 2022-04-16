package com.kd.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("gateway_api_define")
public class Status implements Serializable {
    private String fromservice;


    private String toservice;
    private Date insertdate;

}
