package com.mk.approve.base.controller;

import com.mk.approve.base.service.IBaseService;
import com.mk.approve.base.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * 基础Controller
 * @author 张庆贺
 * @since 2020/2/22 19:37
 */
public class BaseController<S extends IBaseService<T>, T> {

    @Autowired
    protected S baseService;
    private Class<T> clazz;

    public BaseController() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[1];
    }

    /**
     * 获取当前的service
     *
     * @return 当前的service
     */
    public S getService() {
        return baseService;
    }

    /**
     * 描述:获取单个
     *
     * @param entity 过滤条件实体
     * @return 通用返回
     */
    @RequestMapping(value = "/getObject")
    public BaseResponse getObject(@RequestBody T entity) {
        return ResponseUtils.setResultSuccess(baseService.selectOne(entity));
    }

    /**
     * 描述:获取List
     *
     * @param entity 过滤条件实体
     * @return 通用返回
     */
    @RequestMapping(value = "/getList")
    public BaseResponse getList(@RequestBody T entity) {
        return ResponseUtils.setResultSuccess(baseService.selectList(entity));
    }

    /**
     * 描述:获取分页list
     *
     * @param paramsMap 过滤条件实体
     * @return 通用返回
     */
    @RequestMapping(value = "/getListPage")
    public BaseResponse getListPage(@RequestBody Map<String, Object> paramsMap) {
        int pageNum = (int) paramsMap.getOrDefault("pageNum", 1);
        int pageSize = (int) paramsMap.getOrDefault("pageSize", 10);
        paramsMap.remove(pageNum);
        paramsMap.remove(pageSize);
        return ResponseUtils.setResultSuccess(baseService.selectListPage(paramsMap, pageNum, pageSize));
    }

    /**
     * 描述:新增一个
     *
     * @param entity 实体
     * @return 通用返回
     */
    @RequestMapping(value = "/addObject")
    public BaseResponse addObject(@RequestBody T entity) {
        if (baseService.insertSelective(entity) > 0) {
            return ResponseUtils.setResultSuccess(entity);
        } else {
            return ResponseUtils.setResultError("新增失败");
        }
    }

    /**
     * 描述:根据id修改实体
     *
     * @param entity 实体
     * @return 通用返回
     */
    @RequestMapping(value = "/updateObject")
    public BaseResponse updateObject(@RequestBody T entity) {
        int result = baseService.updateByPrimaryKeySelective(entity);
        if (result > 0) {
            return ResponseUtils.setResultSuccess(entity);
        } else {
            return ResponseUtils.setResultSuccess("更新失败");
        }
    }

    /**
     * 描述:根据id逻辑删除
     *
     * @param entity 实体
     * @return 通用返回
     */
    @RequestMapping(value = "/deleteObject")
    public BaseResponse deleteObject(@RequestBody T entity) {
        int result = baseService.delete(entity);
        if (result > 0) {
            return ResponseUtils.setResultSuccess("删除成功");
        } else {
            return ResponseUtils.setResultSuccess("删除失败");
        }
    }

}
