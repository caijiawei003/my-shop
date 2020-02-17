//package com.my.shop.commons.context;
//
//import org.apache.commons.lang3.Validate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// * @program:my-shop
// * @description:
// * @author:xxs_cjw
// * @create:2019-12-30 16:54
// **/
//@Component
//public final class SpringContext implements ApplicationContextAware, DisposableBean {
//
//    private Logger logger = LoggerFactory.getLogger(SpringContext.class);
//    private static ApplicationContext applicationContext;
//
//    /**
//     * 获取存储在静态变量中的 ApplicationContext
//     * @return
//     */
//    public static ApplicationContext getApplicationContext() {
//        assertContextInjected();
//        return applicationContext;
//    }
//
//    /**
//     * @Description: 使用ApplicationContext，根据beanId获取实例
//     * @Author: 蔡嘉伟 on 2019/12/31 15:13
//     * @param:
//     * @return:
//    */
//    public static <T>T getBean(String beanId){
//        assertContextInjected();
//        return (T) applicationContext.getBean(beanId);
//    }
//
//    public static <T>T getBean(Class <T>clazz){
//        assertContextInjected();
//        return (T) applicationContext.getBean(clazz);
//    }
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        SpringContext.applicationContext = applicationContext;
//    }
//
//    public void destroy() throws Exception {
//        logger.debug("清空 ApplicationContext");
//        applicationContext = null;
//    }
//
//    private static void assertContextInjected(){
//        Validate.validState(applicationContext != null,"您还没有在spring-context.xml中配置SpringContext对象");
//    }
//}
