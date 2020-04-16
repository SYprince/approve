package com.mk.approve.mapper;
import com.mk.approve.base.mapper.BaseMapper;
import com.mk.approve.entity.SteelDamageInfo;
import com.mk.approve.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @version:1.0.0
 * @Author: prince
 * @email: wangzixian@aoji.cn
 * @DateTime: 14:35 2020/3/12
 */
public interface SteelDamageInfoMapper extends BaseMapper<SteelDamageInfo> {

    List<SteelDamageInfo> getSteelListByPromoterId(@Param("account")String account);

    List<SteelDamageInfo> getSteelListByFirstApproverId(@Param("account")String account);

    List<SteelDamageInfo> getSteelListBySecondaryApproverId(@Param("account")String account);

    List<SteelDamageInfo> getAllSteelList();
}
