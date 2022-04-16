package com.kd.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kd.springcloud.entity.Gateway;
import com.kd.springcloud.entity.Status;
import com.kd.springcloud.params.Host;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ExchangeMapper  extends BaseMapper<Gateway>{
    Integer updateGateway(String servicename);
    Integer updateCloudGateway(String cloudservicename);
    List<Gateway> getList();

    int insertStatus(String servicename, String cloudservicename, Date insertdate);

    List<Status> getStatusList();

    String selectIp();

    List<Host> selectHost(String serviceid);

    String selectHostCloud(String cloudserviceid);
}
