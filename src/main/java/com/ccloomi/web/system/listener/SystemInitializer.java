package com.ccloomi.web.system.listener;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemInitializer
 * 类 描 述：系统应用启动注册类，此类功能和SystemInitialListener基本相同。无需加注解即可被调用
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:38:25
 */
public class SystemInitializer implements WebApplicationInitializer{
	private final Logger log=LoggerFactory.getLogger(SystemInitializer.class);
	@Override
	public void onStartup(ServletContext sc)throws ServletException {
		sc.setAttribute("logbackConfigLocation", "classpath:logback.xml");
	    sc.setInitParameter("contextConfigLocation", "classpath:spring/spring.xml");
	    sc.setInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	    
		log.debug("注册logbak，配置文件地址：[{}]", sc.getAttribute("logbackConfigLocation"));
		sc.addListener(LogbackConfigListener.class);
	    log.debug("注册logbak完成。");

	    sc.addListener(IntrospectorCleanupListener.class);
	    log.debug("注册SpringContextLoaderListener，配置文件地址：[{}]",sc.getInitParameter("contextConfigLocation"));
	    sc.addListener(ContextLoaderListener.class);
	    log.debug("注册SpringContextLoaderListener");
	    
		log.debug("注册编码过滤器，设置编码：[UTF-8]");
		CharacterEncodingFilter cef=sc.createFilter(CharacterEncodingFilter.class);
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		FilterRegistration.Dynamic dynamic=sc.addFilter("encodingFilter", cef);
		dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
		log.debug("添加编码过滤器完成，过滤地址：{}",dynamic.getUrlPatternMappings());
		
		log.debug("添加SHIRO过滤器");
		DelegatingFilterProxy shiroFilter=sc.createFilter(DelegatingFilterProxy.class);
		shiroFilter.setTargetFilterLifecycle(true);
		dynamic=sc.addFilter("shiroFilter", shiroFilter);
		dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
		log.debug("添加SHIRO过滤器完成，过滤地址：{}",dynamic.getUrlPatternMappings());
		
		log.debug("添加Druid监听器");
		WebStatFilter druidWebStatFilter=sc.createFilter(WebStatFilter.class);
		dynamic=sc.addFilter("druidWebStatFilter", druidWebStatFilter);
		dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
		log.debug("添加Druid监听器完成，过滤地址：{}",dynamic.getUrlPatternMappings());

		log.debug("注册DruidStatViewServlet");
		StatViewServlet druidStatViewServlet=sc.createServlet(StatViewServlet.class);
		ServletRegistration.Dynamic servletDynamic=sc.addServlet("druidStatViewServlet", druidStatViewServlet);
		servletDynamic.setLoadOnStartup(1);
		servletDynamic.addMapping("/druid/*");
		log.debug("注册DruidStatViewServlet完成，处理地址：{}",servletDynamic.getMappings());
		
		log.debug("注册SpringDispatcherServlet");
		DispatcherServlet springDispatcherServlet=sc.createServlet(DispatcherServlet.class);
		springDispatcherServlet.setContextConfigLocation("classpath:spring/spring.xml");
		servletDynamic=sc.addServlet("spring3", springDispatcherServlet);
		servletDynamic.setLoadOnStartup(1);
		servletDynamic.addMapping("*.xhtml","*.json");
		log.debug("注册SpringDispatcherServlet完成，处理地址：{}",servletDynamic.getMappings());
		
		log.debug("已注册servlet：{}",sc.getServletRegistrations().keySet());
	}
}
