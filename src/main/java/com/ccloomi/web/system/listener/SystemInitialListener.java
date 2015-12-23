//package com.idccapp.core.listener;
//
//import java.util.EnumSet;
//
//import javax.servlet.DispatcherType;
//import javax.servlet.FilterRegistration;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//import javax.servlet.annotation.WebListener;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import ch.qos.logback.ext.spring.web.WebLogbackConfigurer;
///**
// * 
// * 类    名：SystemInitialListener
// * 类描述：系统应用启动关闭处理监听器
// * 作    者：Chenxj
// * 日    期：2015年6月17日-上午9:55:00
// */
//@WebListener
//public class SystemInitialListener implements ServletContextListener{
//	private final Logger log=LoggerFactory.getLogger(SystemInitialListener.class);
//
//	@Override
//	public void contextInitialized(ServletContextEvent paramServletContextEvent) {
//		ServletContext sc=paramServletContextEvent.getServletContext();
//		sc.setAttribute("logbackConfigLocation", "classpath:logback.xml");
//		log.debug("注册logbak，配置文件地址：[{}]", sc.getAttribute("logbackConfigLocation"));
//	    WebLogbackConfigurer.initLogging(sc);
//	    log.debug("注册logbak完成。");
//
//		CharacterEncodingFilter cef;
//		try {
//			log.debug("注册编码过滤器，设置编码：[UTF-8]");
//			cef = sc.createFilter(CharacterEncodingFilter.class);
//			cef.setEncoding("UTF-8");
//			cef.setForceEncoding(true);
//			FilterRegistration.Dynamic filterDynamic=sc.addFilter("encodingFilter", cef);
//			filterDynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/");
//			log.debug("添加编码过滤器完成，过滤地址：[/]");
//			log.debug("注册SpringDispatcherServlet");
//			DispatcherServlet springDispatcherServlet=sc.createServlet(DispatcherServlet.class);
//			springDispatcherServlet.setContextConfigLocation("classpath:spring/spring.xml");
//			ServletRegistration.Dynamic servletDynamic=sc.addServlet("spring3", springDispatcherServlet);
//			servletDynamic.setLoadOnStartup(1);
//			servletDynamic.addMapping("*.do");
//			log.debug("注册SpringDispatcherServlet完成，处理地址：[*.do]");
//		} catch (ServletException e) {
//			log.error("SystemInitial失败！", e);
//		}finally{
//			cef=null;
//		}
//	    
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
//		ServletContext sc=paramServletContextEvent.getServletContext();
//	    WebLogbackConfigurer.shutdownLogging(sc);
//	    log.debug("销毁logbak完成。");
//	}
//
//}
