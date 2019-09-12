package com.capgemini.cn.deemo.data.domain;

import lombok.Data;
import org.springframework.context.annotation.Configuration;


/**
 * @author hasaker
 * @since 2019-08-19 23:36
 */
@Data
@Configuration
public class Note {
        private Long noteId;
        private String noteTitle;
        private String noteContent;
        private String createTime;
        private Long createUserId;
}
