package cn.allen.multidatasource.mdscomponent.exception;

import cn.allen.multidatasource.mdscomponent.enumuration.MultiDsExceptionEnum;

/**
 * @author allen
 * @date 2020-04-21
 */
public class RoutingFieldArgsIsNullException extends MultiDsException {
    public RoutingFieldArgsIsNullException(){
        super();
        setExceptionCode(MultiDsExceptionEnum.ROUTING_FIELD_ARGS_ISNULL.getCode());
        setMsg(MultiDsExceptionEnum.ROUTING_FIELD_ARGS_ISNULL.getMsg());
    }
}
