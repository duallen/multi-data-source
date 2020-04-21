package cn.allen.multidatasource.mdscomponent.aspect;

import cn.allen.multidatasource.mdscomponent.annotation.Router;
import cn.allen.multidatasource.mdscomponent.core.Routing;
import cn.allen.multidatasource.mdscomponent.dynamicdatasource.MultiDataSourceHolder;
import cn.allen.multidatasource.mdscomponent.exception.LoadRoutingStrategyUnMatchException;
import cn.allen.multidatasource.mdscomponent.exception.ParamsNotContainsRoutingFieldException;
import cn.allen.multidatasource.mdscomponent.exception.RoutingFieldArgsIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author allen
 * @date 2020-04-21
 */
@Component
@Slf4j
@Aspect
public class RoutingAspect {

    @Autowired
    private Routing routing;

    @Pointcut("@annotation(cn.allen.multidatasource.mdscomponent.annotation.Router)")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws ParamsNotContainsRoutingFieldException,LoadRoutingStrategyUnMatchException, RoutingFieldArgsIsNullException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 获取方法调用名称
        Method method = getInvokeMethod(joinPoint);

        // 获取方法指定的注解
        Router router = method.getAnnotation(Router.class);
        // 获取指定的路由key
        String routingField = router.routingField();

        // 获取方法入参
        Object[] args = joinPoint.getArgs();

        boolean havingRoutingField = false;

        if(args!=null && args.length>0){
            for (Object arg : args) {
                String routingFieldValue = BeanUtils.getProperty(arg, routingField);
                if (!StringUtils.isEmpty(routingFieldValue)) {
                    String dbKey = routing.calDataSourceKey(routingFieldValue);
                    String tableIndex = routing.calTableKey(routingFieldValue);
                    log.info("选择的dbKey是:{},tableKey是:{}", dbKey, tableIndex);
                    havingRoutingField = true;
                    break;
                }
            }

            if (!havingRoutingField){
                log.warn("入参{}中没有包含路由字段:{}",args,routingField);
                throw new ParamsNotContainsRoutingFieldException();
            }
        }
    }

    private Method getInvokeMethod(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    /**
     * 清除线程缓存
     * @param joinPoint
     */
    @After("pointCut()")
    public void methodAfter(JoinPoint joinPoint){
        MultiDataSourceHolder.clearDataSourceKey();
        MultiDataSourceHolder.clearTableIndex();
    }
}
