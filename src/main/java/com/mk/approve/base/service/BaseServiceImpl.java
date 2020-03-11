package com.mk.approve.base.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mk.approve.base.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author 张庆贺
 * @since 2020/2/22 14:06
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> implements IBaseService<T> {
    @Autowired
    protected M baseMapper;
    private Class<T> clazz;

    public BaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[1];
    }

    @Override
    public T selectOne(Map<String, Object> paramsMap) {
        if (paramsMap.size() == 0) {
            return null;
        } else {
            T entity = this.mapToObject(paramsMap);
            return this.baseMapper.selectOne(entity);
        }
    }

    @Override
    public T selectOne(T entity) {
        return this.baseMapper.selectOne(entity);
    }

    @Override
    public List<T> selectList(Map<String, Object> exampleMap) {
        if (exampleMap.size() == 0) {
            return null;
        } else {
            Example example = new Example(this.clazz);
            Example.Criteria criteria = example.createCriteria();
            criteria.andAllEqualTo(exampleMap);
            return this.baseMapper.selectByExample(example);
        }
    }

    @Override
    public List<T> selectList(T entity) {
        return this.baseMapper.select(entity);
    }

    @Override
    public PageInfo<T> selectListPage(Map<String, Object> exampleMap, int pageNum, int pageSize) {
        List<T> docs = null;
        if (exampleMap.size() == 0) {
            return this.pageAll(pageNum, pageSize);
        } else {
            Example example = new Example(this.clazz);
            Example.Criteria criteria = example.createCriteria();
            criteria.andAllEqualTo(exampleMap);
            PageHelper.startPage(pageNum, pageSize);
            docs = this.baseMapper.selectByExample(example);
            return new PageInfo(docs);
        }
    }

    @Override
    public PageInfo<T> selectListPage(T entity, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> docs = this.baseMapper.select(entity);
        return new PageInfo(docs);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer updateByPrimaryKeySelective(T entity) {
        return this.baseMapper.updateByPrimaryKeySelective(entity);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer insertSelective(Map<String, Object> paramsMap) {
        T entity = this.mapToObject(paramsMap);
        return this.baseMapper.insertSelective(entity);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer insertSelective(T entity) {
        return this.baseMapper.insertSelective(entity);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer insert(Map<String, Object> paramsMap) {
        T entity = this.mapToObject(paramsMap);
        return this.baseMapper.insert(entity);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer insert(T entity) {
        return this.baseMapper.insert(entity);
    }

    @Override
    public PageInfo<T> pageAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> docs = this.baseMapper.selectAll();
        return new PageInfo(docs);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer delete(Map<String, Object> exampleMap) {
        Example example = new Example(this.clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andAllEqualTo(exampleMap);
        return this.baseMapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer delete(T entity) {
        return this.baseMapper.delete(entity);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer deleteByPrimaryKey(Object primaryKey) {
        return this.baseMapper.deleteByPrimaryKey(primaryKey);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer updateByExampleSelective(Map<String, Object> exampleMap, Map<String, Object> paramsMap) {
        Example example = new Example(this.clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andAllEqualTo(exampleMap);
        T entity = this.mapToObject(paramsMap);
        return this.baseMapper.updateByExampleSelective(entity, example);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer updateByExampleSelective(Map<String, Object> exampleMap, T entity) {
        Example example = new Example(this.clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andAllEqualTo(exampleMap);
        return this.baseMapper.updateByExampleSelective(entity, example);
    }

    private T mapToObject(Map<String, Object> map) {
        return map != null && !map.isEmpty() ? JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(map)), this.clazz) : null;
    }
}
