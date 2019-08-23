package com.capgemini.cn.deemo.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Name:           Query
 * @Description:   查询参数
 * @Author:         johson
 * @CreateDate:     2019/7/20 0020
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int page;
    //每页条数
    private int limit;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        //start
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        //size
        this.put("limit", limit);

    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

