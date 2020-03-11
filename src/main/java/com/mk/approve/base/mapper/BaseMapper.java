package com.mk.approve.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 张庆贺
 * @since 2019/6/13 16:16
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
