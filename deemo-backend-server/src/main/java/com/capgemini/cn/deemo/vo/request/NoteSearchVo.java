package com.capgemini.cn.deemo.vo.request;

import com.capgemini.cn.deemo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @author fuduwen
 * @version 1.0
 * @date 2019/8/24 14:40
 */
@Setter
@Getter
public class NoteSearchVo {

    private Long noteId;

    private Long createUserId;

}
