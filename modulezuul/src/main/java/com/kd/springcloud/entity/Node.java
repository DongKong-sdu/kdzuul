package com.kd.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("m_node")
public class Node implements Serializable {
    private String id;
    private String hostname;
    private String os;
    private String architecture;
    private Date created_time;
    private Date update_time;
    private String ip;
    private String role;
    private String label;


}
