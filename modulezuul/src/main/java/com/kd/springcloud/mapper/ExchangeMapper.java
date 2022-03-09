package com.kd.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kd.springcloud.entity.Gateway;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExchangeMapper  extends BaseMapper<Gateway>{
    Integer updateGateway(String servicename);
    Integer updateCloudGateway(String cloudservicename);
    List<Gateway> getList();
}
