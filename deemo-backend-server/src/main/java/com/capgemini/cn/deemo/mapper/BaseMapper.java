package com.capgemini.cn.deemo.mapper;

import com.capgemini.cn.deemo.data.domain.Branch;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */

public interface BaseMapper<T> {
    /*传入实体保存*/
    Integer save(T t);

    /*传入map保存*/
    Integer save(Map<String, Object> map);

    //Integer saveBatch(List<T> list);

    /*传入实体更新*/
    Integer update(T t);

    /*根据条件更新*/
    int update(Map<String, Object> map);

    /*根据id删除*/
    int delete(Object id);

//    /*根据条件删除*/
//    int delete(Map<String, Object> map);

    /*批量删除*/
    int deleteBatch(Object[] id);

    /*根据id查询list*/
    T queryObject(Object id);

    //T queryObjectByOther(Map<String, Object> map);

    /*根据条件查询list*/
    List<T> queryList(Map<String, Object> map);

    /*根据查询总数*/
    int queryTotal(Map<String, Object> map);

    /*插叙总数*/
    //int queryTotal();
}
