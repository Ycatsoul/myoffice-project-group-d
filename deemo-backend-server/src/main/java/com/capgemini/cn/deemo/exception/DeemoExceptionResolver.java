package com.capgemini.cn.deemo.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hasaker
 * @since 2019/9/9 11:21
 */
@Component
public class DeemoExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse response,
                                         Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        Map<String, Object> map = new HashMap<>(2);
        map.put("status", 500);
        map.put("msg", "操作失败!");
        modelAndView.addAllObjects(map);

        return modelAndView;
    }
}
