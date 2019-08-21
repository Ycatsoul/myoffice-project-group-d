package com.capgemini.cn.deemo.data.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @author hasaker
 * @since 2019-08-19 23:36
 */
@Data
public class Note {
    private Long noteId;
    private String noteTitle;
    private String noteContent;
    private Date createTime;
    private String createUser;
}
