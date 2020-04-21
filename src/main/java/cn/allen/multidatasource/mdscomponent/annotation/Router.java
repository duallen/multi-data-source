package cn.allen.multidatasource.mdscomponent.annotation;

import cn.allen.multidatasource.mdscomponent.constant.RoutingConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 路由注解
 * @author allen
 * @date 2020-04-21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Router {
    String routingField() default RoutingConstant.DEFAULT_ROUTING_FIELD;
}
