package cn.allen.multidatasource.mdscomponent.exception;

import cn.allen.multidatasource.mdscomponent.enumuration.MultiDsExceptionEnum;

/**
 * @author allen
 * @date 2020-04-21
 */
public class ParamsNotContainsRoutingFieldException extends MultiDsException {
    public ParamsNotContainsRoutingFieldException(){
        super();
        setExceptionCode(MultiDsExceptionEnum.PARAMS_NOT_CONTAINS_ROUTING_FIELD.getCode());
        setMsg(MultiDsExceptionEnum.PARAMS_NOT_CONTAINS_ROUTING_FIELD.getMsg());
    }
}
