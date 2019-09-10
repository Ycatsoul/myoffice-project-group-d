package com.capgemini.cn.deemo.config;

import com.capgemini.cn.deemo.component.AuthenticationAccessDeniedHandler;
import com.capgemini.cn.deemo.component.DeemoMetadataSource;
import com.capgemini.cn.deemo.component.UrlAccessDecisionManager;
import com.capgemini.cn.deemo.data.domain.LoginLog;
import com.capgemini.cn.deemo.data.domain.User;
import com.capgemini.cn.deemo.service.LoginLogService;
import com.capgemini.cn.deemo.service.UserService;
import com.capgemini.cn.deemo.utils.IdWorker;
import com.capgemini.cn.deemo.utils.UserUtils;
import com.capgemini.cn.deemo.vo.base.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author hasaker
 * @since 2019/9/8 00:08
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * TokenBasedRememberMeServices的生成密钥
     */
    private final String SECRET_KEY = "123456";

    private final UserService userService;
    private final LoginLogService loginLogService;
    private final DeemoMetadataSource deemoMetadataSource;
    private final UrlAccessDecisionManager urlAccessDecisionManager;
    private final AuthenticationAccessDeniedHandler deniedHandler;

    public WebSecurityConfig(UserService userService,
                             LoginLogService loginLogService,
                             DeemoMetadataSource deemoMetadataSource,
                             UrlAccessDecisionManager urlAccessDecisionManager,
                             AuthenticationAccessDeniedHandler deniedHandler) {
        this.userService = userService;
        this.loginLogService = loginLogService;
        this.deemoMetadataSource = deemoMetadataSource;
        this.urlAccessDecisionManager = urlAccessDecisionManager;
        this.deniedHandler = deniedHandler;
    }

    /**
     * 身份验证管理生成器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Web安全
     * 配置不需要校验的url
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
            "/index.html",
            "/static/**",
            "/login_page",
            "/register",
            "/favicon.ico",
            "/swagger-ui.html",
            "/swagger-ui.html#",
            "/swagger-ui.html#/",
            "/swagger-resources/**",
            "/v2/**"
        );
    }

    /**
     * Http请求安全处理
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            /**
             * 在configure(HttpSecurity http)方法中，
             * 通过withObjectPostProcessor将刚刚创建的UrlFilterInvocationSecurityMetadataSource和UrlAccessDecisionManager注入进来
             * 到时候所有请求都会经过刚才的过滤器(除了configure(WebSecurity web)方法忽略的请求)
             */
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(deemoMetadataSource);
                o.setAccessDecisionManager(urlAccessDecisionManager);
                return o;
            }
        }).and().formLogin().loginPage("/login_page").loginProcessingUrl("/login").failureHandler((req, resp, e) -> {
            // 登录失败
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            RespBean respBean;
            LoginLog loginLog = new LoginLog();

            if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                respBean = RespBean.error("账户名或者密码输入错误!");
                loginLog.setLoginDesc(respBean.getMsg());
            } else if (e instanceof LockedException) {
                respBean = RespBean.error("账户被锁定，请联系管理员!");
                loginLog.setLoginDesc(respBean.getMsg());
            } else if (e instanceof CredentialsExpiredException) {
                respBean = RespBean.error("密码过期，请联系管理员!");
                loginLog.setLoginDesc(respBean.getMsg());
            } else if (e instanceof AccountExpiredException) {
                respBean = RespBean.error("账户过期，请联系管理员!");
                loginLog.setLoginDesc(respBean.getMsg());
            } else if (e instanceof DisabledException) {
                respBean = RespBean.error("账户被禁用，请联系管理员!");
                loginLog.setLoginDesc(respBean.getMsg());
            } else {
                respBean = RespBean.error("登录失败!");
                loginLog.setLoginDesc(respBean.getMsg());
            }

            // 添加登录日志
            loginLog.setLoginId(IdWorker.get().nextId());
            loginLog.setIsSuccess(false);
            loginLog.setLoginIp(req.getRemoteAddr());

            loginLogService.insertLoginLog(loginLog);

            ObjectMapper om = new ObjectMapper();
            PrintWriter out = resp.getWriter();

            out.write(om.writeValueAsString(respBean));
            out.flush();
            out.close();
        }).successHandler((req, resp, auth) -> {
            // 登录成功
            resp.setContentType("application/json;charset=utf-8");

            User user = UserUtils.getCurrentUser();
            HttpSession session = req.getSession();
            ObjectMapper om = new ObjectMapper();
            PrintWriter out = resp.getWriter();
            RespBean respBean = RespBean.ok("登录成功!", user);

            // 添加登录日志
            loginLogService.insertLoginLog(new LoginLog(){{
                setLoginId(IdWorker.get().nextId());
                setIsSuccess(true);
                setLoginUserId(user.getUserId());
                setLoginIp(req.getRemoteAddr());
                setLoginDesc("登录成功!");
            }});

            session.setAttribute("currentUserId", user.getUserId());
            out.write(om.writeValueAsString(respBean));
            out.flush();
            out.close();
        }).permitAll().and().logout().logoutUrl("/logout").logoutSuccessHandler((req, resp, authentication) -> {
            // 登出
            resp.setContentType("application/json;charset=utf-8");

            RespBean respBean = RespBean.ok("注销成功!");
            ObjectMapper om = new ObjectMapper();
            PrintWriter out = resp.getWriter();

            out.write(om.writeValueAsString(respBean));
            out.flush();
            out.close();
        }).permitAll().and().csrf().disable().exceptionHandling().accessDeniedHandler(deniedHandler);
    }
}
