package com.mk.approve.base.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 张庆贺
 * @since 2020/2/22 14:01
 */
public interface IBaseService<T> {

    /**
     * 根据Map参数查询一个。
     *
     * @param exampleMap 对应实体的Map集合
     * @return 查询到的实体
     */
    T selectOne(Map<String, Object> exampleMap);

    /**
     * 根据实体查询一个
     *
     * @param entity 实体
     * @return 查询到的实体
     */
    T selectOne(T entity);

    /**
     * 根据Map参数查询集合。
     *
     * @param exampleMap 对应实体的Map集合
     * @return 查询到的实体
     */
    List<T> selectList(Map<String, Object> exampleMap);

    /**
     * 根据实体查询集合
     *
     * @param entity 实体
     * @return 查询到的实体
     */
    List<T> selectList(T entity);

    /**
     * 根据Map查询分页集合
     *
     * @param exampleMap 查询条件
     * @param pageNum    页数
     * @param pageSize   页大小
     * @return 分页信息
     */
    PageInfo<T> selectListPage(Map<String, Object> exampleMap, int pageNum, int pageSize);

    /**
     * 根据实体查询分页集合
     *
     * @param entity   实体
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 分页信息
     */
    PageInfo<T> selectListPage(T entity, int pageNum, int pageSize);

    /**
     * 查询所有分页
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 分页信息
     */
    PageInfo<T> pageAll(int pageNum, int pageSize);

    /**
     * 插入Map中对应的字段，过滤其中的null值
     *
     * @param paramsMap 和实体对应的Map信息
     * @return 影响到的行数
     */
    Integer insertSelective(Map<String, Object> paramsMap);

    /**
     * 插入实体中对应的字段，过滤其中的null值
     *
     * @param entity 实体
     * @return 影响到的行数
     */
    Integer insertSelective(T entity);

    /**
     * 插入Map中对应的字段
     *
     * @param paramsMap 和实体对应的Map信息
     * @return 影响到的行数
     */
    Integer insert(Map<String, Object> paramsMap);

    /**
     * 插入实体中对应的字段
     *
     * @param entity 实体
     * @return 影响到的行数
     */
    Integer insert(T entity);

    /**
     * 根据参数删除对应的记录
     *
     * @param paramsMap 和实体对应的Map信息
     * @return 影响行数
     */
    Integer delete(Map<String, Object> paramsMap);

    /**
     * 根据实体删除对应的记录
     *
     * @param entity 实体
     * @return 影响行数
     */
    Integer delete(T entity);

    /**
     * 根据实体删除对应的记录
     *
     * @param primaryKey 主键
     * @return 影响行数
     */
    Integer deleteByPrimaryKey(T primaryKey);

    /**
     * 根据 参数更新实体，过滤null值
     *
     * @param exampleMap 筛选条件
     * @param paramsMap  更新对应的实体Map
     * @return 影响行数
     */
    Integer updateByExampleSelective(Map<String, Object> exampleMap, Map<String, Object> paramsMap);

    /**
     * 根据 参数更新实体，过滤null值
     *
     * @param exampleMap 筛选条件
     * @param entity     实体
     * @return 影响行数
     */
    Integer updateByExampleSelective(Map<String, Object> exampleMap, T entity);

    /**
     * 根据主键参数更新实体，过滤null值
     *
     * @param entity 更新的实体
     * @return 影响行数
     */
    Integer updateByPrimaryKeySelective(T entity);
}
