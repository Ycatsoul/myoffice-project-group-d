package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @create_date 2019-08-19 23:36
 * @description
 */
@Data
public class Note {
    /**
     * ID
     */
    private Long noteId;

    /**
     * 标题
     */
    private String noteTitle;

    /**
     * 内容
     */
    private String noteContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户ID
     */
    private String createUserId;
}
